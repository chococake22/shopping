package kr.project.shopping.service;

import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.dto.CommentSaveDto;
import kr.project.shopping.mapper.CommentMapper;
import kr.project.shopping.vo.CommentDetailVo;
import kr.project.shopping.vo.CommentListVo;
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
}
