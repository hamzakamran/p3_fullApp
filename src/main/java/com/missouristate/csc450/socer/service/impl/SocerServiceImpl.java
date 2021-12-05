package com.missouristate.csc450.socer.service.impl;

import com.missouristate.csc450.socer.DAO.FinalProjectRepository;
import com.missouristate.csc450.socer.HelperClasses.*;
import com.missouristate.csc450.socer.TableEntryObjects.Function;
import com.missouristate.csc450.socer.service.SocerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocerServiceImpl implements SocerService {

    @Autowired
    FinalProjectRepository finalProjectRepository;


    @Override
    public boolean addFunction(String fileName, String fileContents) {

        ArrayList<String> fileContentsFormatted = new ArrayList<String>();
        ArrayList<String> functionContentList = new ArrayList<String>();

        String[] fileContentsFormattedTransition = fileContents.split("/r/n");
        for(int i=0;i<fileContentsFormattedTransition.length;i++)
        {
            fileContentsFormatted.add(fileContentsFormattedTransition[i]);
        }

        CreateFile createFile = new CreateFile(fileName);
        WriteToFile writeToFile = new WriteToFile(fileName, fileContentsFormatted);

        // call the validator
//        Validater validater = new Validater(fileName);
//        boolean isFunctionValid = validater.isValidFunction();

        FunctionExtractor functionExtractor = new FunctionExtractor(fileName);

        // this is the list of functions itself
        ArrayList<ArrayList<String>> functionList = functionExtractor.getfunctionList();
        //this is a list of function names
        ArrayList<String> listOfFunctionNames = findFunctionNames(functionList);

        for (int i = 0; i < listOfFunctionNames.size(); i++)
        {
            CreateFile createFile1 = new CreateFile(listOfFunctionNames.get(i));
            WriteToFile writeToFile1 = new WriteToFile(listOfFunctionNames.get(i), functionList.get(i));
            String functionContent = "";
            for (String string: functionList.get(i)) {
                functionContent += string + "\r\n";
            }
            functionContentList.add(functionContent);

            //
        }
        FunctionDescriptionGenerator functionDescriptionGenerator = new FunctionDescriptionGenerator(listOfFunctionNames, functionContentList, finalProjectRepository);

        DeleteFile deleteFile1 = new DeleteFile(listOfFunctionNames);
        //if (isFunctionValid == true)
        if (true)
        {
//            // call function description generator
//            FunctionDescriptionGenerator functionDescriptionGenerator = new FunctionDescriptionGenerator(fileName, fileContents, finalProjectRepository);
            //DeleteFile deleteFile = new DeleteFile(fileName);

        }
        else {
            //DeleteFile deleteFile = new DeleteFile(fileName);
            //return false;
        }

        return true;
    }

    @Override
    public List<Function> getFunctions(){
        return finalProjectRepository.getFunctions();
    }

    @Override
    public String getErrorString(String fileName, String fileContents) {
        ArrayList<String> fileContentsFormatted = new ArrayList<String>();
        String[] fileContentsFormattedTransition = fileContents.split("/r/n");
        for(int i=0;i<fileContentsFormattedTransition.length;i++)
        {
            fileContentsFormatted.add(fileContentsFormattedTransition[i]);
        }
        CreateFile createFile = new CreateFile(fileName);
        WriteToFile writeToFile = new WriteToFile(fileName, fileContentsFormatted);

        // call the validator
        Validater validater = new Validater(fileName);
        String isFunctionValid = validater.getErrorMessage();
        DeleteFile deleteFile = new DeleteFile(fileName);
        return isFunctionValid;
    }

    public ArrayList<String> findFunctionNames(ArrayList<ArrayList<String>> functionList)
    {
        ArrayList<String> listOfFunctionNames = new ArrayList<String>();
        for (ArrayList<String> function: functionList  ) {
            String functionHeader =  function.get(0);
            String functionName;

            int locationOfOpenParenth = 0;
            int locationOfLastSpace = 0;
            for(int i = 0; i<functionHeader.length(); i++)
            {
                if (functionHeader.charAt(i) == ' '){
                    locationOfLastSpace = i;
                }
                if (functionHeader.charAt(i) == '(' || functionHeader.charAt(i) == '{' )
                {
                    locationOfOpenParenth = i;

                    for (int j = locationOfOpenParenth-1; j>=0; j--)
                    {
                        if (functionHeader.charAt(j) == ' ')
                        {
                            locationOfLastSpace = j;
                        }
                        if (functionHeader.charAt(j) != ' '){
                            for (int k = j-1; k>=0; k--)
                            {
                                if (functionHeader.charAt(k) == ' ')
                                {
                                    locationOfLastSpace = k;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }

            }

            functionName = functionHeader.substring(locationOfLastSpace+1, locationOfOpenParenth);
            listOfFunctionNames.add(functionName);
        }
        return listOfFunctionNames;
    }
    
    
}
