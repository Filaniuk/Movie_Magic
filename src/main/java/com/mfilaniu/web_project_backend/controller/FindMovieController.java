package com.mfilaniu.web_project_backend.controller;

import com.mfilaniu.web_project_backend.controller.util.ProfileAlertState;
import com.mfilaniu.web_project_backend.dto.MovieDTO;
import com.mfilaniu.web_project_backend.dto.UserReadDTO;
import com.mfilaniu.web_project_backend.exceptions.UserNotFoundException;
import com.mfilaniu.web_project_backend.service.MovieService;
import com.mfilaniu.web_project_backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FindMovieController {

    private final MovieService movieService;
    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String findMovie(Model model) {

        model.addAttribute("findMovie_btn", false);
        model.addAttribute("nextMovie_btn", false);
        model.addAttribute("movieSaved_notf", ProfileAlertState.NEUTRAL);
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String findMoviePost(Model model,
                                @RequestParam("findMovie_btn") boolean findMovie_btn) {

        boolean reversedValue = !findMovie_btn;
        model.addAttribute("findMovie_btn", reversedValue);
        model.addAttribute("movieSaved_notf", ProfileAlertState.NEUTRAL);
        model.addAttribute("removeSaveButton", false);

        if (reversedValue) {
            model.addAttribute("randomMovieData", movieService.generateMovie());
        }

        return "index";
    }

    @RequestMapping(value = "/next", method = RequestMethod.POST)
    public String findMoviePost(Model model) {

        model.addAttribute("findMovie_btn", true);
        model.addAttribute("movieSaved_notf", ProfileAlertState.NEUTRAL);
        model.addAttribute("removeSaveButton", false);
        model.addAttribute("randomMovieData", movieService.generateMovie());

        return "index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveMoviePost(Model model,
                                HttpSession session,
                                @ModelAttribute("currentMovie") MovieDTO currentMovie) {

        if(session.getAttribute("auth_user") == null) {
            return "redirect:/login";
        }

        model.addAttribute("findMovie_btn", true);
        model.addAttribute("randomMovieData", currentMovie);
        Long userId = ((UserReadDTO) session.getAttribute("auth_user")).getId();

        if(movieService.saveMovieToProfile(userId, currentMovie)) {
            model.addAttribute("movieSaved_notf", ProfileAlertState.SAVED);
            model.addAttribute("removeSaveButton", true);
            try {
                session.setAttribute("auth_user", userService.find(userId));
            } catch (UserNotFoundException e) {
                //skip
            }
        }
        else {
            model.addAttribute("movieSaved_notf", ProfileAlertState.ALREADY_EXISTS);
        }

        return "index";
    }

}
