package com.developerleetaehee.th_web_app.controller.shop;

import com.developerleetaehee.th_web_app.common.MenuCustomCode;
import com.developerleetaehee.th_web_app.common.MenuInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/shop")
public class ShopController {

    private final MenuCustomCode menuCustomCode;

    @GetMapping
    public String findAllShop(Model model) {

        MenuInfo menuInfo = menuCustomCode.getCode().get("product_list");

        if (menuInfo == null) {
            throw new IllegalArgumentException("존재하지 않는 게시판 유형입니다. 관리자에게 문의하세요.");
        }

        model.addAttribute("title", menuInfo.getTitle());
        model.addAttribute("menuCustomCode", menuInfo);

        return "shop/product_list";
    }

}
