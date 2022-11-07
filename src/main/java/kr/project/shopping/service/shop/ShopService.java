package kr.project.shopping.service.shop;


import kr.project.shopping.domain.user.PrincipalDetails;
import kr.project.shopping.dto.shop.BuyNoteSaveDto;
import kr.project.shopping.vo.shop.BuyNoteDetailVo;
import kr.project.shopping.vo.shop.BuyNoteListVo;
import kr.project.shopping.dto.shop.RegItemSaveDto;
import kr.project.shopping.vo.shop.RegItemDetailVo;
import kr.project.shopping.vo.shop.RegItemListVo;
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

    List<BuyNoteListVo> SELECT_BUY_NOTE_LIST(Long regItemIdx);

    Long COUNT_BUY_NOTE_LIST(Long regItemIdx);

    Long INSERT_BUY_NOTE(HttpServletRequest request, BuyNoteSaveDto dto, Principal principal, PrincipalDetails pd);

    BuyNoteDetailVo SELECT_BUY_NOTE_DETAIL(Long regItemBuyNoteIdx);

    void DELETE_BUY_NOTE(Long regItemBuyNoteIdx);
}
