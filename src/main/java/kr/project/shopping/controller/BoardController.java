package kr.project.shopping.controller;


import kr.project.shopping.domain.board.Board;
import kr.project.shopping.domain.common.Page;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.service.BoardServiceImpl;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardServiceImpl boardService;

    @GetMapping("/list")
    @ResponseBody
    public ModelAndView getBoardList(@RequestParam(required = false, value = "nowPage") Integer nowPage,
                               Model model,
                               HttpServletRequest request,
                               BoardSearchDto dto) {

        if (nowPage == null) {
            nowPage = 1;
        }

        System.out.println(dto);

        System.out.println("searchType : " + dto.getSearchType());
        System.out.println("boardType : " + dto.getBoardType());
        System.out.println("startDate : " + dto.getStartDate());
        System.out.println("endDate : " + dto.getEndDate());
        System.out.println("keyword : " + dto.getKeyword());

        int totalCount = boardService.COUNT_BOARD_LIST(dto);
        Page pagination = new Page(nowPage, totalCount);
        dto.setOffset(pagination.getOffset());

        System.out.println(totalCount);

        List<BoardListVo> boards = boardService.SELECT_BOARD_LIST(dto);

        model.addAttribute("searchDto", dto);
        model.addAttribute("boards", boards);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pagination", pagination);

        ModelAndView mv = new ModelAndView();
        mv.addObject("searchDto", dto);
        mv.addObject("boards", boards);
        mv.addObject("totalCount", totalCount);
        mv.addObject("pagination", pagination);
        mv.setViewName("board/list");

        return mv;
    }

    @GetMapping("/detail/{boardIdx}")
    public String getBoardDetail(@PathVariable Long boardIdx, Model model) {

        BoardDetailVo boardVo = boardService.SELECT_BOARD_DETAIL(boardIdx);

        model.addAttribute("boardVo", boardVo);

        return "board/detail";
    }

    @PostMapping("/save")
    public String saveBoard(HttpServletRequest request, @RequestParam(required = false) List<MultipartFile> files) {


        int boardIdx = boardService.INSERT_BOARD(request);

        log.info("가입 완료 : " + boardIdx);

        return "redirect:/board/list";
    }


    @GetMapping("/reg")
    public String getRegBoardPage() {
        return "board/registration";
    }

    @PostMapping("/reg")
    public String saveBoard() {
        return "redirect:/board/list";
    }
}
