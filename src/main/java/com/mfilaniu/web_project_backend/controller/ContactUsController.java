package com.mfilaniu.web_project_backend.controller;

import com.mfilaniu.web_project_backend.dto.ContactUsDto;
import com.mfilaniu.web_project_backend.exceptions.ContactUsFieldsException;
import com.mfilaniu.web_project_backend.exceptions.ContactUsMessageFailedException;
import com.mfilaniu.web_project_backend.service.ContactUsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/contact-us")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContactUsController {

    private final ContactUsService contactUsService;

    @RequestMapping(method = RequestMethod.GET)
    public String aboutUsGet(ModelMap model) {
        model.addAttribute("message_sent", false);
        model.addAttribute("fields_error", false);
        model.addAttribute("message_sending_error", false);
        model.addAttribute("contact_us_dto", ContactUsDto.builder().build());
        return "categories/contact-us";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String aboutUsPost(ModelMap model, @ModelAttribute ContactUsDto contactUsDto) {

        try {
            contactUsService.sendEmailMessage(contactUsDto);
        } catch (ContactUsFieldsException e) {
            model.addAttribute("fields_error", true);
            model.addAttribute("message_sent", false);
            model.addAttribute("message_sending_error", false);
            model.addAttribute("contact_us_dto", contactUsDto);
            return "categories/contact-us";
        }
        catch (ContactUsMessageFailedException e) {
            model.addAttribute("message_sent", false);
            model.addAttribute("fields_error", false);
            model.addAttribute("message_sending_error", true);
            model.addAttribute("contact_us_dto", contactUsDto);
            return "categories/contact-us";
        }

        model.addAttribute("message_sent", true);
        return "categories/contact-us";
    }

}