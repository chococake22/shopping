package kr.project.shopping.service.comment;


import kr.project.shopping.dto.comment.CommentSaveDto;
import kr.project.shopping.vo.comment.CommentDetailVo;
import kr.project.shopping.vo.comment.CommentListVo;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
public interface CommentService {

    public List<CommentListVo> SELECT_COMMENT_LIST(Long boardIdx);

    public Long INSERT_COMMENT(CommentSaveDto dto, Principal principal);

    Long COUNT_COMMENT_LIST(Long boardIdx);

    CommentDetailVo SELECT_COMMENT_DETAIL(Long commentIdx);
}
