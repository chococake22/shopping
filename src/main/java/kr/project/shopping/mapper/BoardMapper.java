package kr.project.shopping.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    JSONObject SELECT_BOARD_DETAIL(Long boardIdx);

    int COUNT_BOARD_LIST(JSONObject json);

    List<JSONObject> LIST_BOARD(JSONObject json);

    int INSERT_BOARD(JSONObject json);

    void UPDATE_BOARD(JSONObject json);

    void DELETE_BOARD(JSONObject json);
}