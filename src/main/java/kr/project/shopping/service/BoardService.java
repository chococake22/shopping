package kr.project.shopping.service;


import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public interface BoardService {

    public BoardDetailVo SELECT_BOARD_DETAIL(Long boardIdx);

    public int COUNT_BOARD_LIST(BoardSearchDto dto);

    public List<BoardListVo> SELECT_BOARD_LIST(BoardSearchDto dto);

    public int INSERT_BOARD(HttpServletRequest request);

    public JSONObject UPDATE_BOARD(JSONObject json);

    public JSONObject DELETE_BOARD(JSONObject json);
}
