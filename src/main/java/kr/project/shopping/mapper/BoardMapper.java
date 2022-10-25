package kr.project.shopping.mapper;

import kr.project.shopping.domain.board.Board;
import kr.project.shopping.domain.board.BoardFile;
import kr.project.shopping.dto.board.BoardSearchDto;
import kr.project.shopping.dto.board.BoardUpdateDto;
import kr.project.shopping.vo.board.BoardDetailVo;
import kr.project.shopping.vo.board.BoardListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    BoardDetailVo SELECT_BOARD_DETAIL(Long boardIdx);

    int COUNT_BOARD_LIST(BoardSearchDto dto);

    List<BoardListVo> SELECT_BOARD_LIST(BoardSearchDto dto);

    Long INSERT_BOARD(Board board);

    void UPDATE_BOARD(BoardUpdateDto board);

    void DELETE_BOARD(Long boardIdx);

    void INSERT_BOARD_FILE(BoardFile boardFile);

    List<BoardFile> SELECT_BOARD_FILES(Long boardIdx);

    BoardFile SELECT_BOARD_FILE(Long boardFileIdx);

    void CLICK_COUNT(Long boardIdx);
}