package com.missouristate.csc450.socer.controllers;

import com.missouristate.csc450.socer.DTO.FileNameAndContents;
import com.missouristate.csc450.socer.DTO.ListParameter;
import com.missouristate.csc450.socer.HelperClasses.CreateFile;
import com.missouristate.csc450.socer.HelperClasses.FunctionDescriptionGenerator;
import com.missouristate.csc450.socer.HelperClasses.Validater;
import com.missouristate.csc450.socer.HelperClasses.WriteToFile;
import com.missouristate.csc450.socer.TableEntryObjects.Function;
import com.missouristate.csc450.socer.TableEntryObjects.Keyword;
import com.missouristate.csc450.socer.service.SocerService;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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
    public String addFunction(@RequestBody ArrayList<FileNameAndContents> data, Model model) {
        // NFR 3 Uploaded function will be able to be searched within 24 hours

        System.out.println(data.size());
        ArrayList<String> fileNamesArray = new ArrayList<>();
        ArrayList<String> fileContentsArray = new ArrayList<>();

        for (int i =0;i<data.size();i++) {
            String fileName = data.get(i).getFileName();
            String fileContents = data.get(i).getFileURL();
            System.out.println(fileName +" " + i);

            fileNamesArray.add(fileName);
            fileContentsArray.add(fileContents);
        }

        boolean willFunctionBeAdded = socerService.addFunction(fileNamesArray, fileContentsArray);
        if (!willFunctionBeAdded)
        {
            //String errorMessage = socerService.getErrorString(fileName, fileContents);
            //.out.println(errorMessage);
            //return errorMessage ;
        }
        return "redirect:/home";
    }

    @ResponseBody
    @PostMapping(value = "/searchFunctions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object searchFunctions(@RequestBody String keywords, Model model) {
        // FR 4 returns C++ function in response to user search query.
        // NFR 2 returns functions within 30 seconds
        return socerService.getSearchContents(keywords);
    }

}


