package kr.project.shopping.service;


import kr.project.shopping.domain.board.BoardFile;
import kr.project.shopping.dto.BoardSaveDto;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Component
public interface BoardService {

    public BoardDetailVo SELECT_BOARD_DETAIL(Long boardIdx);

    public int COUNT_BOARD_LIST(BoardSearchDto dto);

    public List<BoardListVo> SELECT_BOARD_LIST(BoardSearchDto dto);

    public Long INSERT_BOARD(HttpServletRequest request, BoardSaveDto dto, Principal principal);

    public JSONObject UPDATE_BOARD(JSONObject json);

    public void DELETE_BOARD(Long boardIdx);

    public void INSERT_BOARD_FILE(Long boardIdx, List<MultipartFile> files, Long regIdx);

    public List<BoardFile> SELECT_BOARD_FILES(Long boardIdx);

    public BoardFile SELECT_BOARD_FILE(Long boardFileIdx);

    public void CLICK_COUNT(Long boardIdx);
}
