package kr.project.shopping.controller;


import kr.project.shopping.domain.board.BoardFile;
import kr.project.shopping.domain.common.Page;
import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.board.BoardSaveDto;
import kr.project.shopping.dto.board.BoardSearchDto;
import kr.project.shopping.dto.board.BoardUpdateDto;
import kr.project.shopping.service.board.BoardServiceImpl;
import kr.project.shopping.service.comment.CommentServiceImpl;
import kr.project.shopping.service.user.UserServiceImpl;
import kr.project.shopping.vo.board.BoardDetailVo;
import kr.project.shopping.vo.board.BoardListVo;
import kr.project.shopping.vo.comment.CommentListVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardServiceImpl boardService;
    private final UserServiceImpl userService;
    private final CommentServiceImpl commentService;

    @GetMapping("/list")
    public Map<String, Object> getBoardList(@RequestParam(required = false, value = "nowPage") Integer nowPage,
                                            Model model,
                                            BoardSearchDto dto,
                                            Principal principal) {

        if (nowPage == null) {
            nowPage = 1;
        }

        dto.setSearchType("");
        dto.setBoardType("");
        dto.setStartDate("");
        dto.setEndDate("");
        dto.setKeyword("");

        int totalCount = boardService.COUNT_BOARD_LIST(dto);
        Page pagination = new Page(nowPage, totalCount);
        dto.setOffset(pagination.getOffset());

        List<BoardListVo> boards = boardService.SELECT_BOARD_LIST(dto);

        Map<String, Object> map = new HashMap<>();

        map.put("searchDto", dto);
        map.put("boards", boards);
        map.put("totalCount", totalCount);
        map.put("pagination", pagination);

        User user = null;
        // 로그인이 되어있는 경우
        if (principal != null) {
            user = userService.SELECT_USER_BY_USERID(principal.getName());
            map.put("user", user.getName());
        } else {
            map.put("user", null);
        }

        return map;
    }

    @PostMapping("/list/search")
    public String searchBoardList(BoardSearchDto dto, Model model,
                                  @RequestParam(required = false) Integer nowPage,
                                  Principal principal) {

        int totalCount = boardService.COUNT_BOARD_LIST(dto);

        if (dto.getNowPage() == 0) {
            dto.setNowPage(1);
        }

        Page pagination = new Page(dto.getNowPage(), totalCount);
        dto.setOffset(pagination.getOffset());

        List<BoardListVo> boards = boardService.SELECT_BOARD_LIST(dto);

        model.addAttribute("searchDto", dto);
        model.addAttribute("boards", boards);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pagination", pagination);

        User user = null;
        // 로그인이 되어있는 경우
        if (principal != null) {
            user = userService.SELECT_USER_BY_USERID(principal.getName());
            model.addAttribute("user", user.getName());
        } else {
            model.addAttribute("user", null);
        }

        return "board/list";
    }

    @GetMapping("/detail/{boardIdx}")
    public String getBoardDetail(@PathVariable Long boardIdx, Model model, Principal principal) {

        BoardDetailVo boardVo = boardService.SELECT_BOARD_DETAIL(boardIdx);
        List<BoardFile> files = boardService.SELECT_BOARD_FILES(boardIdx);
        boardService.CLICK_COUNT(boardIdx);

        User user = null;
        // 로그인이 되어있는 경우
        if (principal != null) {
            user = userService.SELECT_USER_BY_USERID(principal.getName());
            model.addAttribute("user", user.getName());
        }

        List<CommentListVo> comments = commentService.SELECT_COMMENT_LIST(boardIdx);
        Long count = commentService.COUNT_COMMENT_LIST(boardIdx);

        model.addAttribute("boardVo", boardVo);
        model.addAttribute("files", files);
        model.addAttribute("comments", comments);
        model.addAttribute("count", count);

        return "board/detail";
    }

    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveBoard(HttpServletRequest request, BoardSaveDto dto,
                                         @RequestParam(value = "files", required = false) List<MultipartFile> files, Principal principal) {

        Map<String, Object> map = new HashMap<>();

        try {
            Long boardIdx = boardService.INSERT_BOARD(request, dto, principal);
            BoardDetailVo boardDetailVo = boardService.SELECT_BOARD_DETAIL(boardIdx);

            boardService.INSERT_BOARD_FILE(boardIdx, files, boardDetailVo.getRegIdx());
            map.put("boardIdx", boardIdx);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("/reg")
    public String getRegBoardPage() {
        return "board/registration";
    }

    @PostMapping("/delete/{boardIdx}")
    @ResponseBody
    public Map<String,Object> deleteBoard(@PathVariable Long boardIdx) {

        Map<String, Object> map = new HashMap<>();
        try {
            boardService.DELETE_BOARD(boardIdx);
            map.put("boardIdx", boardIdx);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return map;
    }

    @GetMapping("/update/{boardIdx}")
    public String updateBoard(@PathVariable Long boardIdx, Model model) {
        BoardDetailVo board = boardService.SELECT_BOARD_DETAIL(boardIdx);

        if (board.getBoardType().equals("공지사항")) {
            board.setBoardType("notice");
        } else if (board.getBoardType().equals("일반글")) {
            board.setBoardType("normal");
        } else {
            board.setBoardType("etc");
        }

        board.setContent(board.getContent().replace("<br>","\r\n"));

        model.addAttribute("boardVo", board);
        model.addAttribute("boardIdx", boardIdx);
        return "board/update";
    }

    @PostMapping("/update")
    public String updateBoardChk(BoardUpdateDto dto) {
        boardService.UPDATE_BOARD(dto);
        return "redirect:/board/list";
    }


    @GetMapping("/file/download")
    public void fileDown(@RequestParam Long boardFileIdx, HttpServletResponse response) throws IOException {

        BoardFile boardFile = boardService.SELECT_BOARD_FILE(boardFileIdx);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode((String) boardFile.getSaveName(), "UTF-8") + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", "application/download; utf-8");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(boardFile.getFilePath());
            FileCopyUtils.copy(fis, response.getOutputStream());
            response.getOutputStream().flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

        fis.close();

    }
}
