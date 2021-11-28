package com.missouristate.csc450.socer.service.impl;

import com.missouristate.csc450.socer.DAO.FinalProjectRepository;
import com.missouristate.csc450.socer.HelperClasses.CreateFile;
import com.missouristate.csc450.socer.HelperClasses.FunctionDescriptionGenerator;
import com.missouristate.csc450.socer.HelperClasses.Validater;
import com.missouristate.csc450.socer.HelperClasses.WriteToFile;
import com.missouristate.csc450.socer.TableEntryObjects.Function;
import com.missouristate.csc450.socer.service.SocerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocerServiceImpl implements SocerService {

    @Autowired
    FinalProjectRepository finalProjectRepository;


    @Override
    public boolean addFunction(String fileName, String fileContents) {

        String[] fileContentsFormatted;
        fileContentsFormatted = fileContents.split("/r/n");

        CreateFile createFile = new CreateFile(fileName);
        WriteToFile writeToFile = new WriteToFile(fileName, fileContentsFormatted);

        // call the validator
        Validater validater = new Validater(fileName);
        boolean isFunctionValid = validater.isValidFunction();


        if (isFunctionValid == true)
        {
            // call function description generator
            FunctionDescriptionGenerator functionDescriptionGenerator = new FunctionDescriptionGenerator(fileName, fileContents, finalProjectRepository);

        }
        else {
            return false;
        }

        return true;
    }

    @Override
    public List<Function> getFunctions(){
        return finalProjectRepository.getFunctions();
    }

}
