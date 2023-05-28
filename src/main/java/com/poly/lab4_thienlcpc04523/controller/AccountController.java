package com.poly.lab4_thienlcpc04523.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.poly.lab4_thienlcpc04523.service.CookieService;
import com.poly.lab4_thienlcpc04523.service.ParamService;
import com.poly.lab4_thienlcpc04523.service.SessionService;

import jakarta.servlet.http.Cookie;

@Controller
public class AccountController {
    @Autowired
    CookieService cookieService;

    @Autowired
    ParamService paramService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1() {
        return "/account/login";
    }

    @PostMapping("/account/login")
    public String login2(Model model) {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);

        if (un.equals("poly") && pw.equals("123")) {
            sessionService.set("username", un);

            if (rm) {
                cookieService.add("user", un, 10);
            } else {
                cookieService.remove("user");
            }
            return "/account/login";
        } else {
            model.addAttribute("error", "Đăng nhập thất bại");
            return "/account/login";
        }
    }

}
