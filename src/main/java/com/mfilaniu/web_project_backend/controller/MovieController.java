package com.mfilaniu.web_project_backend.controller;

import com.mfilaniu.web_project_backend.exceptions.MovieNotFoundException;
import com.mfilaniu.web_project_backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

    private final MovieService movieService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long movieId, Model model) {

        try {
            model.addAttribute("found_movie", movieService.findMovie(movieId));
        } catch (MovieNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return "movies/movie";
    }

}
