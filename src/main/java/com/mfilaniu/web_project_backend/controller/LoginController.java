package com.mfilaniu.web_project_backend.controller;

import com.mfilaniu.web_project_backend.dto.UserLoginDTO;
import com.mfilaniu.web_project_backend.dto.UserReadDTO;
import com.mfilaniu.web_project_backend.exceptions.FalseLoginOrPasswordException;
import com.mfilaniu.web_project_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String loginGet(HttpServletRequest request,
                           HttpSession session,
                           Model model) {

        if(session.getAttribute("auth_user") != null) {
            return "redirect:/profile";
        }

        model.addAttribute("loggingUser", UserLoginDTO.builder().build());
        return "authorization/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String loginPost(@ModelAttribute UserLoginDTO userLoginDTO,
                            Model model,
                            HttpSession session) {

        UserReadDTO userReadDTO;

        try {
            userReadDTO = userService.login(userLoginDTO);
        } catch (FalseLoginOrPasswordException e) {
            model.addAttribute("loggingUser", userLoginDTO);
            model.addAttribute("loggingError", true);
            return "authorization/login";
        }

        session.setAttribute("auth_user", userReadDTO);

        return "redirect:/profile";
    }


}
