package kr.project.shopping.service;

import kr.project.shopping.domain.board.Board;
import kr.project.shopping.domain.board.BoardFile;
import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.BoardSaveDto;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.dto.BoardUpdateDto;
import kr.project.shopping.mapper.BoardMapper;
import kr.project.shopping.mapper.UserMapper;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final UserMapper userMapper;

    @Value("${file.upload.path.board}")
    private String fileDir;

    @Value("${file.upload.size}")
    private Long fileSize;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    @Override
    public BoardDetailVo SELECT_BOARD_DETAIL(Long boardIdx) {

        BoardDetailVo boardDetailVo = boardMapper.SELECT_BOARD_DETAIL(boardIdx);
        boardDetailVo.getContent().replaceAll("<br>", "\r\n");

        return boardDetailVo;
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
    public Long INSERT_BOARD(HttpServletRequest request, BoardSaveDto dto, Principal principal) {

        Board board = null;

        try {
            User user = userMapper.SELECT_USER_BY_USERID(principal.getName());

            board = Board.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent().replace("\r\n","<br>"))   // 줄바꿈 처리
                    .boardType(dto.getBoardType())
                    .regIdx(user.getUserIdx())
                    .build();

            boardMapper.INSERT_BOARD(board);

            log.info("등록 번호 : {}",  board.getBoardIdx());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return board.getBoardIdx();
    }

    @Override
    public void UPDATE_BOARD(BoardUpdateDto dto) {

        dto.setContent(dto.getContent().replace("\r\n","<br>"));
        boardMapper.UPDATE_BOARD(dto);

    }

    @Override
    public void DELETE_BOARD(Long boardIdx) {
        boardMapper.DELETE_BOARD(boardIdx);
    }

    @Override
    public void INSERT_BOARD_FILE(Long boardIdx, List<MultipartFile> files, Long regIdx) {

        final String[] exts = {"jpg", "jpeg", "bmp", "pdf", "txt", "png", "hwp", "xls", "pptx", "doc", "gif"};
        ArrayList<String> extArr = new ArrayList<>(Arrays.asList(exts));

        try {
            if (files != null) {
                for (MultipartFile file : files) {

                    String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

                    String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

                    if(!extArr.contains(ext)) {
                        throw new RuntimeException("유효하지 않은 확장자입니다.");
                    }

                    if(file.getSize() > fileSize) {
                        throw new RuntimeException("파일 크기는 최대 1MB입니다.");
                    }

                    UUID uuid = UUID.randomUUID();

                    BoardFile boardFile = BoardFile.builder()
                            .fileName(file.getOriginalFilename())
                            .saveName(uuid.toString() + " " + file.getOriginalFilename())
                            .filePath(getFullPath(file.getOriginalFilename()))
                            .regIdx(regIdx)
                            .boardIdx(boardIdx)
                            .uuid(uuid.toString())
                            .build();

                    // 파일 전송
                    File saveFile = new File(getFullPath(uuid.toString() + file.getOriginalFilename()));
                    file.transferTo(saveFile);

                    // DB 저장
                    boardMapper.INSERT_BOARD_FILE(boardFile);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BoardFile> SELECT_BOARD_FILES(Long boardIdx) {
         return boardMapper.SELECT_BOARD_FILES(boardIdx);
    }

    @Override
    public BoardFile SELECT_BOARD_FILE(Long boardFileIdx) {
        return boardMapper.SELECT_BOARD_FILE(boardFileIdx);
    }

    @Override
    public void CLICK_COUNT(Long boardIdx) {
        boardMapper.CLICK_COUNT(boardIdx);
    }
}
