package kr.project.shopping.controller;

import kr.project.shopping.dto.CommentSaveDto;
import kr.project.shopping.service.CommentServiceImpl;
import kr.project.shopping.vo.CommentDetailVo;
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
        model.addAttribute("count", count);

        return "board/detail/{boardIdx}";


    }

    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveComment(CommentSaveDto dto, Principal principal) {

        Map<String, Object> map = new HashMap<>();

        try {
            Long commentIdx = commentService.INSERT_COMMENT(dto, principal);
            CommentDetailVo comment = commentService.SELECT_COMMENT_DETAIL(dto.getCommentIdx());
            List<CommentListVo> comments =  commentService.SELECT_COMMENT_LIST(dto.getBoardIdx());
            Long count =  commentService.COUNT_COMMENT_LIST(dto.getBoardIdx());

            System.out.println(count);

            map.put("msg", "성공");
            map.put("comment", comment);
            map.put("comments", comments);
            map.put("count", count);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }


    @GetMapping("/list/load/{boardIdx}")
    public String getCommentListLoad(@PathVariable Long boardIdx, Model model) {

        Map<String, Object> map = new HashMap<>();

        String msg = "ok";

        System.out.println("되는건가요");

        try {
            List<CommentListVo> comments = commentService.SELECT_COMMENT_LIST(boardIdx);
            Long count = commentService.COUNT_COMMENT_LIST(boardIdx);

            map.put("comments", comments);
            map.put("count", count);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }
}
