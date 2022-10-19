package kr.project.shopping.service;


import kr.project.shopping.domain.board.BoardFile;
import kr.project.shopping.dto.BoardSaveDto;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.dto.BoardUpdateDto;
import kr.project.shopping.dto.CommentSaveDto;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import kr.project.shopping.vo.CommentDetailVo;
import kr.project.shopping.vo.CommentListVo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Component
public interface CommentService {

    public List<CommentListVo> SELECT_COMMENT_LIST(Long boardIdx);

    public Long INSERT_COMMENT(CommentSaveDto dto, Principal principal);

    Long COUNT_COMMENT_LIST(Long boardIdx);

    CommentDetailVo SELECT_COMMENT_DETAIL(Long commentIdx);
}
