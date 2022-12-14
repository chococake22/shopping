package kr.project.shopping.controller;


import com.google.gson.JsonObject;
import kr.project.shopping.domain.user.PrincipalDetails;
import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.shop.BuyNoteSaveDto;
import kr.project.shopping.dto.shop.RegItemSaveDto;
import kr.project.shopping.dto.user.KakaoUserSaveDto;
import kr.project.shopping.mapper.KakaoMapper;
import kr.project.shopping.service.shop.ShopServiceImpl;
import kr.project.shopping.service.user.UserOAuth2Service;
import kr.project.shopping.service.user.UserServiceImpl;
import kr.project.shopping.vo.shop.BuyNoteListVo;
import kr.project.shopping.vo.shop.RegItemDetailVo;
import kr.project.shopping.vo.shop.RegItemListVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final KakaoMapper kakaoMapper;

    @Value("${file.upload.path.shop}")
    private String fileDir;

    @Value("${file.upload.size}")
    private Long fileSize;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    @GetMapping("/list")
    public String getItemList(Model model, Principal principal) {

        List<RegItemListVo> list = shopService.SELECT_REG_ITEM_LIST();

        model.addAttribute("list", list);


        if (principal != null) {
            model.addAttribute("user", principal.getName());
        } else {
            model.addAttribute("user", null);
        }

        return "shop/list";
    }

    @GetMapping("/reg")
    public String getItemSave() {
        return "shop/registration";
    }

    @GetMapping("/detail/{regItemIdx}")
    public String getItemDetail(@PathVariable Long regItemIdx, Model model, Principal principal, @AuthenticationPrincipal PrincipalDetails pd) {

        RegItemDetailVo item = shopService.SELECT_REG_ITEM_DETAIL(regItemIdx);
        List<BuyNoteListVo> buyNotes = shopService.SELECT_BUY_NOTE_LIST(regItemIdx);
        Long count = shopService.COUNT_BUY_NOTE_LIST(regItemIdx);

        model.addAttribute("item", item);
        model.addAttribute("buyNotes", buyNotes);
        model.addAttribute("count", count);


        if (principal != null) {
            System.out.println("principal : " + principal.getName());
            String[] str = principal.getName().split("_");
            if (str[0].equals("kakao")) {
                User user = userService.getUserInfo(pd.getUser().getEmail(), pd.getUser().getProvider());
                model.addAttribute("user", user.getNickname());
            } else {
                User user = userService.getUserInfo(principal.getName());
                model.addAttribute("user", user.getNickname());
            }
        } else {
            model.addAttribute("user", null);
        }

        return "shop/detail";
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
                throw new RuntimeException("???????????? ?????? ??????????????????.");
            }

            if(file.getSize() > fileSize) {
                throw new RuntimeException("?????? ????????? ?????? 1MB?????????.");
            }

            User user = userService.getUserInfo(principal.getName());

            uuid = UUID.randomUUID();

            String savedFileName = uuid + "." + ext;

            // ??????????????? ?????? ?????? ??????
            saveFile = new File(getFullPath(savedFileName));

            // ?????? ??????
            InputStream fileStream = file.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, saveFile);

            // ????????? ????????? ?????????????????? ?????? url??? ????????????.
            json.addProperty("url", "/summernoteImage/"  + savedFileName);
            json.addProperty("filename", savedFileName);
            json.addProperty("responseCode", "success");


        } catch (Exception e) {
            FileUtils.deleteQuietly(saveFile); // ????????? ?????? ????????????
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

            // ?????? ??????
            Long regItemIdx = shopService.INSERT_REG_ITEM(request, dto, principal);

            // ?????? ?????? ????????????
            RegItemDetailVo regItemDetailVo = shopService.SELECT_REG_ITEM_DETAIL(dto.getRegItemIdx());

            // ????????? ?????? ??????
            shopService.INSERT_REG_ITEM_THUMBNAIL(regItemDetailVo.getRegItemIdx(), file, regItemDetailVo.getRegIdx());
            map.put("regItemIdx", regItemIdx);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // ???????????? ????????? ??????
    @GetMapping("/list/{regItemIdx}")
    public String getBuyNoteList(@PathVariable Long regItemIdx, Model model, Principal principal) {

        List<BuyNoteListVo> buyNotes = shopService.SELECT_BUY_NOTE_LIST(regItemIdx);
        Long count = shopService.COUNT_BUY_NOTE_LIST(regItemIdx);

        model.addAttribute("buyNotes", buyNotes);
        model.addAttribute("count", count);

        return "shop/detail/{regItemIdx}";
    }

    @PostMapping("/save/buynote")
    @ResponseBody
    public Map<String, Object> saveBuyNote(HttpServletRequest request, BuyNoteSaveDto dto,
                                           Principal principal, @AuthenticationPrincipal PrincipalDetails pd) {

        Map<String, Object> map = new HashMap<>();

        try {

            // ?????? ??????
            shopService.INSERT_BUY_NOTE(request, dto, principal, pd);
            shopService.SELECT_BUY_NOTE_DETAIL(dto.getRegItembuyNoteIdx());

            List<BuyNoteListVo> buyNotes = shopService.SELECT_BUY_NOTE_LIST(dto.getRegItemIdx());
            Long count = shopService.COUNT_BUY_NOTE_LIST(dto.getRegItemIdx());

            // ????????? ?????? ??????
//            shopService.INSERT_REG_ITEM_THUMBNAIL(regItemDetailVo.getRegItemIdx(), file, regItemDetailVo.getRegIdx());
            map.put("buyNoteIdx", dto.getRegItembuyNoteIdx());
            map.put("buyNotes", buyNotes);
            map.put("count", count);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/delete/buynote/{regItembuyNoteIdx}")
    @ResponseBody
    public Map<String, Object> deleteBuyNote(@PathVariable Long regItembuyNoteIdx) {

        Map<String, Object> map = new HashMap<String, Object>();

        try {
            shopService.DELETE_BUY_NOTE(regItembuyNoteIdx);
            map.put("msg", "??????????????? ?????????????????????.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
