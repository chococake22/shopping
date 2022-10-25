package kr.project.shopping.mapper;

import kr.project.shopping.dto.comment.CommentSaveDto;
import kr.project.shopping.vo.comment.CommentDetailVo;
import kr.project.shopping.vo.comment.CommentListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    List<CommentListVo> SELECT_COMMENT_LIST(Long boardIdx);

    Long INSERT_COMMENT(CommentSaveDto dto);

    Long COUNT_COMMENT_LIST(Long boardIdx);

    CommentDetailVo SELECT_COMMENT_DETAIL(Long commentIdx);

}