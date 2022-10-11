package kr.project.shopping.service;

import kr.project.shopping.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public JSONObject SELECT_BOARD_DETAIL(Long boardIdx) {
        return null;
    }

    @Override
    public int COUNT_BOARD_LIST(JSONObject json) {
        return 0;
    }

    @Override
    public List<JSONObject> LIST_BOARD(JSONObject json) {
        return null;
    }

    @Override
    public int INSERT_BOARD(HttpServletRequest request) {

        String boardType = request.getParameter("boardType");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        JSONObject json = new JSONObject();
        json.put("boardType", boardType);
        json.put("title", title);
        json.put("content", content);

        System.out.println(boardType);
        System.out.println(title);
        System.out.println(content);

        int boardIdx = boardMapper.INSERT_BOARD(json);

        return boardIdx;
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
