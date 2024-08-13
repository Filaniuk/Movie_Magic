package com.mfilaniu.web_project_backend.controller;

import com.mfilaniu.web_project_backend.dto.UserCreateDTO;
import com.mfilaniu.web_project_backend.dto.UserReadDTO;
import com.mfilaniu.web_project_backend.entity.user.Citizenship;
import com.mfilaniu.web_project_backend.exceptions.RepeatedPasswordException;
import com.mfilaniu.web_project_backend.exceptions.UserAlreadyExistsException;
import com.mfilaniu.web_project_backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    private final UserService userService;
    private final Citizenship[] citizenships = Citizenship.values();

    @RequestMapping(method = RequestMethod.GET)
    public String registerGet(Model model, HttpSession session) {

        if (session.getAttribute("auth_user") != null) {
            return "redirect:/profile";
        }

        model.addAttribute("citizenships", citizenships);
        model.addAttribute("registeringUser", UserCreateDTO.builder().build());

        return "authorization/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerPost(Model model,
                               @ModelAttribute UserCreateDTO userCreateDTO,
                               HttpSession session) {

        UserReadDTO userReadDto;

        try {
            userReadDto = userService.save(userCreateDTO);
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("registeringUser", userCreateDTO);
            model.addAttribute("citizenships", citizenships);
            model.addAttribute("userExistsError", true);
            return "authorization/register";
        }
        catch (RepeatedPasswordException e) {
            model.addAttribute("registeringUser", userCreateDTO);
            model.addAttribute("citizenships", citizenships);
            model.addAttribute("errorsFound", false);
            model.addAttribute("userExistsError", false);
            model.addAttribute("repeatedPasswordError", true);
            return "authorization/register";
        }
        catch (Exception e) {
            model.addAttribute("registeringUser", userCreateDTO);
            model.addAttribute("citizenships", citizenships);
            model.addAttribute("errorsFound", true);
            return "authorization/register";
        }

        session.setAttribute("auth_user", userReadDto);
        return "redirect:/profile";
    }

}
