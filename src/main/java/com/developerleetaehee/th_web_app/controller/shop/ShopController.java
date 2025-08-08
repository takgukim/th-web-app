package com.developerleetaehee.th_web_app.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @GetMapping
    public String findAllShop(Model model) {
        model.addAttribute("title", "상품목록");

        return "shop/product_list";
    }

}
