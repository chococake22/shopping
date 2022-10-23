package kr.project.shopping.service;

import kr.project.shopping.domain.item.RegItemFile;
import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.RegItemSaveDto;
import kr.project.shopping.mapper.ShopMapper;
import kr.project.shopping.vo.RegItemDetailVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService{

    private final ShopMapper shopMapper;
    private final UserServiceImpl userService;

    @Value("${file.upload.path.shop}")
    private String fileDir;

    @Value("${file.upload.size}")
    private Long fileSize;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    // 상품 등록
    @Override
    public Long INSERT_REG_ITEM(HttpServletRequest request, RegItemSaveDto dto, Principal principal) {

        User user = userService.SELECT_USER_BY_USERID(principal.getName());
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

        System.out.println("file : " + file.getOriginalFilename());

        try {
            if (file != null) {

                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

                String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
                System.out.println(originalFilename);

                System.out.println(ext);

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
                        .build();

                // 파일 전송
                File saveFile = new File(getFullPath(uuid.toString() + file.getOriginalFilename()));
                file.transferTo(saveFile);

                // DB 저장
                shopMapper.INSERT_REG_ITEM_THUMBNAIL(regItemFile);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
