package com.mfilaniu.web_project_backend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @RequestMapping(method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.removeAttribute("auth_user");
        session.invalidate();

        return "redirect:/";
    }

}
