package com.mfilaniu.web_project_backend.controller;

import com.mfilaniu.web_project_backend.dto.UserReadDTO;
import com.mfilaniu.web_project_backend.exceptions.MovieNotFoundException;
import com.mfilaniu.web_project_backend.exceptions.UserNotFoundException;
import com.mfilaniu.web_project_backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @SneakyThrows
    @RequestMapping(method = RequestMethod.GET)
    public String profile(Model model, HttpSession session) {

        if(session.getAttribute("auth_user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("show_my_docs", false);

        return "user/user_profile";
    }

    @RequestMapping(value = "/my-docs", method = RequestMethod.POST)
    public String showMyData(Model model,
                             HttpSession session,
                             @RequestParam("show_my_docs") boolean showMyData) throws UserNotFoundException {
        UserReadDTO userReadDTO = (UserReadDTO) session.getAttribute("auth_user");

        if(userReadDTO == null) {
            return "redirect:/login";
        }

        boolean reversedValue = !showMyData;
        model.addAttribute("show_my_docs", reversedValue);

        if(reversedValue) {
            //updating user
            Long userId = ((UserReadDTO) session.getAttribute("auth_user")).getId();
            session.setAttribute("auth_user", userService.find(userId));
        }

        return "user/user_profile";
    }

    @SneakyThrows
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String profile(Model model,
                          @RequestParam("userId") Long userId,
                          @RequestParam("movieId") Long movieId,
                          HttpSession session) {
        UserReadDTO userReadDTO = (UserReadDTO) session.getAttribute("auth_user");

        if(userReadDTO == null) {
            return "redirect:/login";
        }
        model.addAttribute("show_my_docs", false);
        try {
            userService.deleteUserMovie(userId, movieId);
        } catch (MovieNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return "user/user_profile";
    }

}
