package kr.project.shopping.mapper;

import kr.project.shopping.domain.board.Board;
import kr.project.shopping.domain.board.BoardFile;
import kr.project.shopping.domain.item.RegItemFile;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.dto.BoardUpdateDto;
import kr.project.shopping.dto.RegItemSaveDto;
import kr.project.shopping.dto.RegItemThumbSaveDto;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import kr.project.shopping.vo.RegItemDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShopMapper {

    Long INSERT_REG_ITEM(RegItemSaveDto dto);

    RegItemDetailVo SELECT_REG_ITEM_DETAIL(Long regItemIdx);

    void INSERT_REG_ITEM_THUMBNAIL(RegItemFile regItemFile);


}