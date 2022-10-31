package kr.project.shopping.service.comment;

import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.comment.CommentSaveDto;
import kr.project.shopping.mapper.CommentMapper;
import kr.project.shopping.service.user.UserServiceImpl;
import kr.project.shopping.vo.comment.CommentDetailVo;
import kr.project.shopping.vo.comment.CommentListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;
    private final UserServiceImpl userService;

    @Override
    public List<CommentListVo> SELECT_COMMENT_LIST(Long boardIdx) {
        return commentMapper.SELECT_COMMENT_LIST(boardIdx);
    }

    @Override
    public Long INSERT_COMMENT(CommentSaveDto dto, Principal principal) {

        User user = userService.SELECT_USER_BY_USERID(principal.getName());
        dto.setRegIdx(user.getUserIdx());
//        dto.setCommentContent(dto.getCommentContent().replace("<br>","\r\n"));
        return commentMapper.INSERT_COMMENT(dto);

    }

    @Override
    public Long COUNT_COMMENT_LIST(Long boardIdx) {
        return commentMapper.COUNT_COMMENT_LIST(boardIdx);
    }

    @Override
    public CommentDetailVo SELECT_COMMENT_DETAIL(Long commentIdx) {
        return commentMapper.SELECT_COMMENT_DETAIL(commentIdx);
    }

    @Override
    public void DELETE_COMMENT(Long commentIdx) {
        commentMapper.DELETE_COMMENT(commentIdx);
    }
}
