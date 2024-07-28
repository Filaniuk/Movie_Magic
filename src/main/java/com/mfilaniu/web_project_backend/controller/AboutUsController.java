package com.mfilaniu.web_project_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/about-us")
public class AboutUsController {

    @RequestMapping(method = RequestMethod.GET)
    public String aboutUsGet(ModelMap model) {
        return "categories/about-us";
    }

}
