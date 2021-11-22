package com.missouristate.csc450.socer.controllers;

import com.missouristate.csc450.socer.DTO.FileNameAndContents;
import com.missouristate.csc450.socer.HelperClasses.CreateFile;
import com.missouristate.csc450.socer.HelperClasses.FunctionDescriptionGenerator;
import com.missouristate.csc450.socer.HelperClasses.Validater;
import com.missouristate.csc450.socer.HelperClasses.WriteToFile;
import com.missouristate.csc450.socer.service.SocerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;

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


    @PostMapping(value = "/addFunction", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addFunction(@RequestBody FileNameAndContents data, Model model) {

        String fileName;
        String[] fileContents;
        fileName = data.getFileName();
        fileContents = data.getFileContents();

        boolean willFunctionBeAdded = socerService.addFunction(fileName, fileContents);
        if (!willFunctionBeAdded)
        {
            return "{}";
        }
        return "redirect:/home";
    }

    @PostMapping(value = "/searchFunctions", produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchFunctions(@RequestBody String keywords, Model model) {

        // add logic here to do something with the keywords that were in the search bar
        System.out.println(keywords);
        return "redirect:/home";
    }

}


