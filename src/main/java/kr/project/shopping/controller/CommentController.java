package kr.project.shopping.controller;

import kr.project.shopping.dto.CommentSaveDto;
import kr.project.shopping.service.CommentServiceImpl;
import kr.project.shopping.vo.CommentListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @GetMapping("/list/{boardIdx}")
    public String getCommentList(@PathVariable Long boardIdx, Model model) {

        List<CommentListVo> comments = commentService.SELECT_COMMENT_LIST(boardIdx);
        Long count = commentService.COUNT_COMMENT_LIST(boardIdx);

        model.addAttribute("comments", comments);

        return "board/detail/{boardIdx}";


    }

    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveComment(CommentSaveDto dto, Principal principal) {
        Long commentIdx = commentService.INSERT_COMMENT(dto, principal);

        Map<String, Object> map = new HashMap<>();

        map.put("msg", "성공");

        return map;
    }


}
