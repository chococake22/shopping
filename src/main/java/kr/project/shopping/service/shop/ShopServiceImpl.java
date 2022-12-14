package kr.project.shopping.service.shop;

import kr.project.shopping.domain.item.RegItemFile;
import kr.project.shopping.domain.user.PrincipalDetails;
import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.shop.BuyNoteSaveDto;
import kr.project.shopping.dto.user.KakaoUserSaveDto;
import kr.project.shopping.mapper.KakaoMapper;
import kr.project.shopping.service.user.UserServiceImpl;
import kr.project.shopping.vo.shop.BuyNoteDetailVo;
import kr.project.shopping.vo.shop.BuyNoteListVo;
import kr.project.shopping.dto.shop.RegItemSaveDto;
import kr.project.shopping.mapper.ShopMapper;
import kr.project.shopping.vo.shop.RegItemDetailVo;
import kr.project.shopping.vo.shop.RegItemListVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class ShopServiceImpl implements ShopService{

    private final ShopMapper shopMapper;
    private final UserServiceImpl userService;
    private final KakaoMapper kakaoMapper;

    @Value("${file.upload.path.shop}")
    private String fileDir;

    @Value("${file.upload.size}")
    private Long fileSize;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    // 상품 등록
    @Override
    @Transactional
    public Long INSERT_REG_ITEM(HttpServletRequest request, RegItemSaveDto dto, Principal principal) {

        User user = userService.getUserInfo(principal.getName());
        dto.setRegIdx(user.getUserIdx());
        return shopMapper.INSERT_REG_ITEM(dto);
    }

    // 상품 정보 불러오기
    @Override
    public RegItemDetailVo SELECT_REG_ITEM_DETAIL(Long regItemIdx) {

        RegItemDetailVo regItemDetailVo = shopMapper.SELECT_REG_ITEM_DETAIL(regItemIdx);

        return regItemDetailVo;
    }

    // 썸네일 저장
    @Override
    public void INSERT_REG_ITEM_THUMBNAIL(Long regItemIdx, MultipartFile file, Long regIdx) {

        final String[] exts = {"jpg", "jpeg", "bmp", "png", "gif"};
        ArrayList<String> extArr = new ArrayList<>(Arrays.asList(exts));

        try {
            if (file != null) {

                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

                String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

                if(!extArr.contains(ext)) {
                    throw new RuntimeException("유효하지 않은 확장자입니다.");
                }

                if(file.getSize() > fileSize) {
                    throw new RuntimeException("파일 크기는 최대 1MB입니다.");
                }

                UUID uuid = UUID.randomUUID();

                RegItemFile regItemFile = RegItemFile.builder()
                        .fileName(file.getOriginalFilename())
                        .saveName(uuid.toString() + " " + file.getOriginalFilename())
                        .filePath(getFullPath(file.getOriginalFilename()))
                        .regIdx(regIdx)
                        .regItemIdx(regItemIdx)
                        .uuid(uuid.toString())
                        .uuidExt(uuid.toString() + "." + ext)
                        .build();

                // 파일 전송
                File saveFile = new File(getFullPath(uuid.toString() + "." + ext));
                file.transferTo(saveFile);

                // DB 저장
                shopMapper.INSERT_REG_ITEM_THUMBNAIL(regItemFile);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<RegItemListVo> SELECT_REG_ITEM_LIST() {
        List<RegItemListVo> list = shopMapper.SELECT_REG_ITEM_LIST();

        return list;
    }

    @Override
    public List<BuyNoteListVo> SELECT_BUY_NOTE_LIST(Long regItemIdx) {
        return shopMapper.SELECT_BUY_NOTE_LIST(regItemIdx);
    }

    @Override
    public Long COUNT_BUY_NOTE_LIST(Long regItemIdx) {
        return shopMapper.COUNT_BUY_NOTE_LIST(regItemIdx);
    }

    public Long INSERT_BUY_NOTE(HttpServletRequest request, BuyNoteSaveDto dto, Principal principal, PrincipalDetails pd) {

        System.out.println("댓글 등록 아이디 : " + principal.getName());

        if (pd != null && !pd.getUser().getProvider().equals("normal")) {
            User user = userService.getUserInfo(pd.getUser().getUserId(), pd.getUser().getProvider());
            dto.setRegIdx(user.getUserIdx());
        } else {
            User user = userService.getUserInfo(principal.getName());
            dto.setRegIdx(user.getUserIdx());
        }

        return shopMapper.INSERT_BUY_NOTE(dto);
    }

    @Override
    public BuyNoteDetailVo SELECT_BUY_NOTE_DETAIL(Long regItemBuyNoteIdx) {
        return shopMapper.SELECT_BUY_NOTE_DETAIL(regItemBuyNoteIdx);
    }

    @Override
    public void DELETE_BUY_NOTE(Long regItemBuyNoteIdx) {
        shopMapper.DELETE_BUY_NOTE(regItemBuyNoteIdx);
    }
}
