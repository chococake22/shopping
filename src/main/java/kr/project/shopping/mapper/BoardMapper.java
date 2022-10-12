package kr.project.shopping.mapper;

import kr.project.shopping.domain.board.Board;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import org.apache.ibatis.annotations.Mapper;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    BoardDetailVo SELECT_BOARD_DETAIL(Long boardIdx);

    int COUNT_BOARD_LIST(BoardSearchDto dto);

    List<BoardListVo> SELECT_BOARD_LIST(BoardSearchDto dto);

    Long INSERT_BOARD(Board board);

    void UPDATE_BOARD(JSONObject json);

    void DELETE_BOARD(JSONObject json);
}