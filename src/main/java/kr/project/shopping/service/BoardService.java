package kr.project.shopping.service;


import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public interface BoardService {

    public JSONObject SELECT_BOARD_DETAIL(Long boardIdx);

    public int COUNT_BOARD_LIST(JSONObject json);

    public List<JSONObject> LIST_BOARD(JSONObject json);

    public int INSERT_BOARD(HttpServletRequest request);

    public JSONObject UPDATE_BOARD(JSONObject json);

    public JSONObject DELETE_BOARD(JSONObject json);
}
