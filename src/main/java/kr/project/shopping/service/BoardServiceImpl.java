package kr.project.shopping.service;

import kr.project.shopping.domain.board.Board;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.mapper.BoardMapper;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public BoardDetailVo SELECT_BOARD_DETAIL(Long boardIdx) {
        return boardMapper.SELECT_BOARD_DETAIL(boardIdx);
    }

    @Override
    public int COUNT_BOARD_LIST(BoardSearchDto dto) {
        return boardMapper.COUNT_BOARD_LIST(dto);
    }

    @Override
    public List<BoardListVo> SELECT_BOARD_LIST(BoardSearchDto dto) {
        return boardMapper.SELECT_BOARD_LIST(dto);
    }

    @Override
    public int INSERT_BOARD(HttpServletRequest request) {

        String boardType = request.getParameter("boardType");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Board board = Board.builder()
                .title(title)
                .content(content)
                .boardType(boardType)
                .build();


        Long boardIdx = boardMapper.INSERT_BOARD(board);

        System.out.println("등록 번호 : " + board.getBoardIdx());

        return 13;
    }

    @Override
    public JSONObject UPDATE_BOARD(JSONObject json) {
        return null;
    }

    @Override
    public JSONObject DELETE_BOARD(JSONObject json) {
        return null;
    }
}
