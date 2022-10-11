package kr.project.shopping.controller;


import kr.project.shopping.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardServiceImpl boardService;

    @GetMapping("/list")
    public String getBoardList() {
        return "board/list";
    }

    @GetMapping("/list/{boardIdx}")
    public String getBoardDetail(@PathVariable Long boardIdx) {
        return "board/detail";
    }

    @PostMapping("/save")
    public String saveBoard(HttpServletRequest request, @RequestParam(required = false) List<MultipartFile> files) {


        int boardIdx = boardService.INSERT_BOARD(request);

        log.info("가입 완료 : " + boardIdx);

        return "redirect:/board/list";
    }


    @GetMapping("/reg")
    public String getRegBoard() {
        return "board/registration";
    }

    @PostMapping("/reg")
    public String saveBoard() {
        return "redirect:/board/list";
    }
}
