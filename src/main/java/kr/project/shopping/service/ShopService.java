package kr.project.shopping.service;


import kr.project.shopping.domain.board.BoardFile;
import kr.project.shopping.dto.BoardSaveDto;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.dto.BoardUpdateDto;
import kr.project.shopping.dto.RegItemSaveDto;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import kr.project.shopping.vo.RegItemDetailVo;
import kr.project.shopping.vo.RegItemListVo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Component
public interface ShopService {

    Long INSERT_REG_ITEM(HttpServletRequest request, RegItemSaveDto dto, Principal principal);

    RegItemDetailVo SELECT_REG_ITEM_DETAIL(Long regItemIdx);

    void INSERT_REG_ITEM_THUMBNAIL(Long regItemIdx, MultipartFile file, Long regIdx);

    List<RegItemListVo> SELECT_REG_ITEM_LIST();

}
