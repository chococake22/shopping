package kr.project.shopping.controller;


import kr.project.shopping.domain.common.Page;
import kr.project.shopping.dto.BoardRegDto;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.service.BoardServiceImpl;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardServiceImpl boardService;

    @GetMapping("/list")
    public Map<String, Object> getBoardList(@RequestParam(required = false, value = "nowPage") Integer nowPage,
                                            Model model,
                                            HttpServletRequest request,
                                            BoardSearchDto dto) {

        if (nowPage == null) {
            nowPage = 1;
        }

        dto.setSearchType("");
        dto.setBoardType("");
        dto.setStartDate("");
        dto.setEndDate("");
        dto.setKeyword("");


        System.out.println("---------------------");
        System.out.println("searchType : " + dto.getSearchType());
        System.out.println("boardType : " + dto.getBoardType());
        System.out.println("startDate : " + dto.getStartDate());
        System.out.println("endDate : " + dto.getEndDate());
        System.out.println("keyword : " + dto.getKeyword());

        int totalCount = boardService.COUNT_BOARD_LIST(dto);
        Page pagination = new Page(nowPage, totalCount);
        dto.setOffset(pagination.getOffset());

        System.out.println("전체 개수 : " + totalCount);

        List<BoardListVo> boards = boardService.SELECT_BOARD_LIST(dto);

        Map<String, Object> map = new HashMap<>();

        map.put("searchDto", dto);
        map.put("boards", boards);
        map.put("totalCount", totalCount);
        map.put("pagination", pagination);

        return map;
    }

    @PostMapping("/list/search")
    public String searchBoardList(BoardSearchDto dto, Model model, @RequestParam(required = false) Integer nowPage) {

        System.out.println("---------------------");
        System.out.println("searchType : " + dto.getSearchType());
        System.out.println("boardType : " + dto.getBoardType());
        System.out.println("startDate : " + dto.getStartDate());
        System.out.println("endDate : " + dto.getEndDate());
        System.out.println("keyword : " + dto.getKeyword());

        int totalCount = boardService.COUNT_BOARD_LIST(dto);

        System.out.println("현재 페이지 : " + dto.getNowPage());


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

        return "board/list";
    }

    @GetMapping("/detail/{boardIdx}")
    public String getBoardDetail(@PathVariable Long boardIdx, Model model) {

        BoardDetailVo boardVo = boardService.SELECT_BOARD_DETAIL(boardIdx);

        model.addAttribute("boardVo", boardVo);

        return "board/detail";
    }

    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveBoard(HttpServletRequest request, @RequestBody BoardRegDto dto, @RequestParam(required = false) List<MultipartFile> files) {

        Long boardIdx = boardService.INSERT_BOARD(request, dto);
        Map<String, Object> map = new HashMap<>();
        map.put("boardIdx", boardIdx);

        return map;
    }


    @GetMapping("/reg")
    public String getRegBoardPage() {
        return "board/registration";
    }

    @PostMapping("/reg")
    public String saveBoard() {
        return "redirect:/board/list";
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
}
