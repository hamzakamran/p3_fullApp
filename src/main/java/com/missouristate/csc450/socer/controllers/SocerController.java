package com.missouristate.csc450.socer.controllers;

import com.missouristate.csc450.socer.DTO.FileNameAndContents;
import com.missouristate.csc450.socer.HelperClasses.CreateFile;
import com.missouristate.csc450.socer.HelperClasses.FunctionDescriptionGenerator;
import com.missouristate.csc450.socer.HelperClasses.Validater;
import com.missouristate.csc450.socer.HelperClasses.WriteToFile;
import com.missouristate.csc450.socer.TableEntryObjects.Function;
import com.missouristate.csc450.socer.TableEntryObjects.Keyword;
import com.missouristate.csc450.socer.service.SocerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        String fileContents;
        fileName = data.getFileName();
        fileContents = data.getFileContents();

        boolean willFunctionBeAdded = socerService.addFunction(fileName, fileContents);
        if (!willFunctionBeAdded)
        {
            String errorMessage = socerService.getErrorString(fileName, fileContents);
            System.out.println(errorMessage);
            return errorMessage ;

        }
        return "redirect:/home";
    }

    @ResponseBody
    @PostMapping(value = "/searchFunctions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object searchFunctions(@RequestBody String keywords, Model model) {

        // add logic here to do something with the keywords that were in the search bar
        String searchBarContents = keywords;
        //System.out.println(searchBarContents);

        searchBarContents = searchBarContents.replace("\"", "");
        //System.out.println(searchBarContents);
        String[] searchBarList = searchBarContents.split(" ");


        List<Function> functionList = socerService.getFunctions();
        List<Function> functionListAfterSearch = new ArrayList<>();
        for (Function function: functionList) {
            boolean shouldFunctionBeenAdded = false;
            int numberOfMatchingWords = 0;
            double startingScore = Double.parseDouble(function.getTotalKeywordWeight());
            double score = 0.0;
            for (Keyword keywordValue: function.getKeywordList()) {
                for (String string: searchBarList) {
                    if (keywordValue.getKeyword().equals(string))
                    {
                        score = score + Double.parseDouble(keywordValue.getScore());
                        numberOfMatchingWords++;
                        shouldFunctionBeenAdded = true;
                    }
                }
            }
            if (shouldFunctionBeenAdded) {
                function.setTotalKeywordWeight(String.valueOf(score/startingScore));
                System.out.println(score/startingScore + " " + function.getFileName() + " number of matching words: " + numberOfMatchingWords);
                functionListAfterSearch.add(function);
            }
        }

        Collections.sort(functionListAfterSearch);
        Collections.reverse(functionListAfterSearch);
        return functionListAfterSearch;
        //return "redirect:/home";
    }

}


