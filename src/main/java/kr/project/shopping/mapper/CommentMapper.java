package kr.project.shopping.mapper;

import kr.project.shopping.domain.board.Board;
import kr.project.shopping.domain.board.BoardFile;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.dto.BoardUpdateDto;
import kr.project.shopping.dto.CommentSaveDto;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import kr.project.shopping.vo.CommentDetailVo;
import kr.project.shopping.vo.CommentListVo;
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