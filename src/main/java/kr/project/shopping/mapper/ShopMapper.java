package kr.project.shopping.mapper;

import kr.project.shopping.domain.item.RegItemFile;
import kr.project.shopping.dto.shop.BuyNoteSaveDto;
import kr.project.shopping.vo.shop.BuyNoteDetailVo;
import kr.project.shopping.vo.shop.BuyNoteListVo;
import kr.project.shopping.dto.shop.RegItemSaveDto;
import kr.project.shopping.vo.shop.RegItemDetailVo;
import kr.project.shopping.vo.shop.RegItemListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Repository
@Mapper
public interface ShopMapper {

    Long INSERT_REG_ITEM(RegItemSaveDto dto);

    RegItemDetailVo SELECT_REG_ITEM_DETAIL(Long regItemIdx);

    void INSERT_REG_ITEM_THUMBNAIL(RegItemFile regItemFile);

    List<RegItemListVo> SELECT_REG_ITEM_LIST();

    List<BuyNoteListVo> SELECT_BUY_NOTE_LIST(Long regItemIdx);

    Long COUNT_BUY_NOTE_LIST(Long regItemIdx);

    Long INSERT_BUY_NOTE(BuyNoteSaveDto dto);

    BuyNoteDetailVo SELECT_BUY_NOTE_DETAIL(Long regItemBuyNoteIdx);

    void DELETE_BUY_NOTE(Long regItemBuyNoteIdx);
}