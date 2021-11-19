package com.missouristate.csc450.socer.controllers;

import com.missouristate.csc450.socer.service.SocerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocerController {

    @Autowired
    private SocerService socerService;

    @GetMapping({"/", "/home"})
    public String socer(Model model){
        return "home";
    }

    @GetMapping({"/upload"})
    public String upload(Model model){
        return "upload";
    }
}
