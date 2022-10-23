package kr.project.shopping.controller;


import com.google.gson.JsonObject;
import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.RegItemSaveDto;
import kr.project.shopping.service.ShopServiceImpl;
import kr.project.shopping.service.UserServiceImpl;
import kr.project.shopping.vo.RegItemDetailVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
@Slf4j
public class ShopController {

    private final UserServiceImpl userService;
    private final ShopServiceImpl shopService;

    @Value("${file.upload.path.shop}")
    private String fileDir;

    @Value("${file.upload.size}")
    private Long fileSize;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    @GetMapping("/list")
    public String getItemList() {
        return "shop/list";
    }

    @GetMapping("/reg")
    public String getItemSave() {
        return "shop/registration";
    }


    @PostMapping(value = "/summernote")
    @ResponseBody
    public JsonObject fileUpload(MultipartFile file, HttpServletResponse response, HttpServletRequest request, Principal principal) throws Exception {

        File saveFile = null;
        JsonObject json = new JsonObject();
        UUID uuid = null;

        try {
            final String[] exts = {"jpg", "jpeg", "bmp", "pdf", "txt", "png", "hwp", "xls", "pptx", "doc", "gif"};
            ArrayList<String> extArr = new ArrayList<>(Arrays.asList(exts));

            response.setContentType("application/json");

            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());


            if(!extArr.contains(ext)) {
                throw new RuntimeException("유효하지 않은 확장자입니다.");
            }

            if(file.getSize() > fileSize) {
                throw new RuntimeException("파일 크기는 최대 1MB입니다.");
            }

            User user = userService.SELECT_USER_BY_USERID(principal.getName());

            uuid = UUID.randomUUID();

            String savedFileName = uuid + "." + ext;

//            RegItemFile regItemFile = RegItemFile.builder()
//                    .fileName(file.getOriginalFilename())
//                    .saveName(uuid.toString() + " " + file.getOriginalFilename())
//                    .filePath(getFullPath(file.getOriginalFilename()))
//                    .regIdx(user.getUserIdx())
//                    .uuid(uuid.toString())
//                    .build();

            // 저장경로에 맞는 파일 생성
            saveFile = new File(getFullPath(savedFileName));

            // 파일 저장
            InputStream fileStream = file.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, saveFile);

            // 사진이 임시로 저장되어있는 곳을 url로 가져온다.
            json.addProperty("url", "/summernoteImage/"  + savedFileName);
            json.addProperty("filename", savedFileName);
            json.addProperty("responseCode", "success");


        } catch (Exception e) {
            FileUtils.deleteQuietly(saveFile); // 저장된 파일 삭제하기
            json.addProperty("responseCode", "error");
            e.printStackTrace();
        }
        return json;
    }

    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveRegItem(HttpServletRequest request, RegItemSaveDto dto,
                                         @RequestParam(value = "file", required = false) MultipartFile file, Principal principal) {

        Map<String, Object> map = new HashMap<>();

        try {

            // 상품 저장
            Long regItemIdx = shopService.INSERT_REG_ITEM(request, dto, principal);

            // 상품 정보 가져오기
            RegItemDetailVo regItemDetailVo = shopService.SELECT_REG_ITEM_DETAIL(regItemIdx);

            System.out.println(regItemDetailVo);
            System.out.println(file);
            System.out.println(regItemDetailVo.getRegIdx());

            // 썸네일 사진 저장
            shopService.INSERT_REG_ITEM_THUMBNAIL(regItemDetailVo.getRegIdx(), file, regItemDetailVo.getRegIdx());
            map.put("regItemIdx", regItemIdx);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
