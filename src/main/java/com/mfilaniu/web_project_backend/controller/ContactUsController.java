package com.mfilaniu.web_project_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/contact-us")
public class ContactUsController {

    @RequestMapping(method = RequestMethod.GET)
    public String aboutUsGet(ModelMap model) {
        return "categories/contact-us";
    }

}