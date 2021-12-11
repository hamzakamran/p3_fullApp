/**

 -- AUTHORS --
 + Hamza Kamran
 + Adam Gibbons
 + Kimmy Thach

 -- DESCRIPTION: --
 This is the controller. This is place where the javascript sends its data.

 -- FUNCTIONAL REQUIREMENT(S) MET: --
 + FR 4 Found in: searchFunctions()

 -- NONFUNCTIONAL REQUIREMENT(S) MET: --
 + NFR 1 Found in: socer() and upload()
 + NFR 2 Found in: searchFunctions
 + NFR 3 Found in: addFunction()

 -- USER INTERFACE REQUIREMENT(S) MET: --
 + None

 -- SOFTWARE INTERFACE REQUIREMENT(S) MET: --
 + None

 -- LOGICAL DATABASE REQUIREMENT(S) MET: --
 + None

 --------------------------------------

 The MIT License (MIT)

 Copyright (c) 2021 OpenFin

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 **/


package com.missouristate.csc450.socer.controllers;

import com.missouristate.csc450.socer.DTO.FileNameAndContents;
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
        // NFR.1 SoCeRC++ shall have a pre-populated database of at least 100 functions.

        try {
            if (socerService.getFunctions().size()<9)
            {socerService.prePopulateDatabase();}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "home";
    }


    @GetMapping({"/upload"})
    public String upload(Model model){
        // NFR.1 SoCeRC++ shall have a pre-populated database of at least 100 functions.

        try {
            if (socerService.getFunctions().size()<9)
            {socerService.prePopulateDatabase();}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload";
    }

    @ResponseBody
    @PostMapping(value = "/addFunction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<String> addFunction(@RequestBody ArrayList<FileNameAndContents> data, Model model) {
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

        ArrayList<String> willFunctionBeAdded = socerService.addFunction(fileNamesArray, fileContentsArray);
        return willFunctionBeAdded;
//        return "redirect:/home";
    }

    @ResponseBody
    @PostMapping(value = "/searchFunctions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object searchFunctions(@RequestBody String keywords, Model model) {
        // FR 4 returns C++ function in response to user search query.
        // NFR 2 returns functions within 30 seconds
        return socerService.getSearchContents(keywords);
    }

}


