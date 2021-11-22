package com.missouristate.csc450.socer.service.impl;

import com.missouristate.csc450.socer.HelperClasses.CreateFile;
import com.missouristate.csc450.socer.HelperClasses.FunctionDescriptionGenerator;
import com.missouristate.csc450.socer.HelperClasses.Validater;
import com.missouristate.csc450.socer.HelperClasses.WriteToFile;
import com.missouristate.csc450.socer.service.SocerService;
import org.springframework.stereotype.Service;

@Service
public class SocerServiceImpl implements SocerService {

    @Override
    public boolean addFunction(String fileName, String[] fileContents) {

        CreateFile createFile = new CreateFile(fileName);
        WriteToFile writeToFile = new WriteToFile(fileName, fileContents);

        // call the validator
        Validater validater = new Validater(fileName);
        boolean isFunctionValid = validater.isValidFunction();


        if (isFunctionValid == true)
        {
            // call function description generator
            FunctionDescriptionGenerator functionDescriptionGenerator = new FunctionDescriptionGenerator(fileName);

            // add to database
        }
        else {
            return false;
        }

        return true;
    }
}
