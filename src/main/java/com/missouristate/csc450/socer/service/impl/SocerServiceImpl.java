package com.missouristate.csc450.socer.service.impl;

import com.missouristate.csc450.socer.DAO.FinalProjectRepository;
import com.missouristate.csc450.socer.HelperClasses.*;
import com.missouristate.csc450.socer.TableEntryObjects.Function;
import com.missouristate.csc450.socer.TableEntryObjects.Keyword;
import com.missouristate.csc450.socer.service.SocerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.type.ArrayType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class SocerServiceImpl implements SocerService {

    @Autowired
    FinalProjectRepository finalProjectRepository;


    @Override
    public boolean addFunction(ArrayList<String> fileName, ArrayList<String> fileContents) {

        ArrayList<ArrayList<String>> fileContentsFormattedArray = new ArrayList<>();
        ArrayList<String> functionContentList = new ArrayList<String>();

        for (int i =0; i<fileName.size();i++) {
            ArrayList<String> fileContentsFormatted = new ArrayList<>();
            String[] fileContentsFormattedTransition = fileContents.get(i).split("/r/n");
            for (int j = 0; j < fileContentsFormattedTransition.length; j++) {
                fileContentsFormatted.add(fileContentsFormattedTransition[j]);
            }
            fileContentsFormattedArray.add(fileContentsFormatted);
        }

        CreateFile createFile = new CreateFile(fileName);
        WriteToFile writeToFile = new WriteToFile(fileName, fileContentsFormattedArray);

        ArrayList<String> transitionNames = new ArrayList<>();
        ArrayList<String> finalFileNames = new ArrayList<>();
        ArrayList<String> finalFileContents = new ArrayList<>();
        transitionNames = doesFileCompile(fileName);
        for (int ppp = 0; ppp< fileName.size(); ppp++)
        {
            if(transitionNames.contains(fileName.get(ppp)))
            {
                finalFileNames.add(fileName.get(ppp));
                finalFileContents.add(fileContents.get(ppp));
            }
        }


        ArrayList<String> fileNamesMapped= new ArrayList<>();
        ArrayList<String> fileContentsMapped= new ArrayList<>();
        ArrayList<FunctionExtractor> extractionArray= new ArrayList<>();
        for (int z = 0; z< finalFileNames.size(); z++){

            // FR 1 Socer can extract valid functions from C++ files
            FunctionExtractor functionExtractor = new FunctionExtractor(finalFileNames.get(z));
            extractionArray.add(functionExtractor);

            for (int p = 0; p<functionExtractor.getfunctionList().size();p++)
            {
                fileNamesMapped.add(finalFileNames.get(z));
                fileContentsMapped.add(finalFileContents.get(z));
            }
        }


        ArrayList<ArrayList<String>> functionList = new ArrayList<>();
        ArrayList<String> listOfFunctionNames = new ArrayList<>();

        for (FunctionExtractor functionExtractor: extractionArray) {

            // this is the list of functions itself
            for (ArrayList<String> arrayLists : functionExtractor.getfunctionList()) {
                functionList.add(arrayLists);
            }


        }
        for(int i =0;i<findFunctionNames(functionList).size();i++)
        {
            if(listOfFunctionNames.contains(findFunctionNames(functionList).get(i)))
            {
                listOfFunctionNames.add(findFunctionNames(functionList).get(i) + "" + i);
            }
            else
            {
                listOfFunctionNames.add(findFunctionNames(functionList).get(i));
            }

        }


            for (int i = 0; i < functionList.size(); i++)
            {
                CreateFile createFile1 = new CreateFile(listOfFunctionNames.get(i));
                WriteToFile writeToFile1 = new WriteToFile(listOfFunctionNames.get(i), functionList.get(i));
                String functionContent = new String();
                for (String string: functionList.get(i)) {
                    functionContent += string + "\r\n";
                }
                functionContentList.add(functionContent);
            }

            ArrayList<String> descriptionList = new ArrayList<>();
            for(int i = 0; i<listOfFunctionNames.size(); i++) {
                CommentAndVariableExtractor commentAndVariableExtractor = new CommentAndVariableExtractor(listOfFunctionNames.get(i));
                String description = "";
                for (String string : commentAndVariableExtractor.getAllWordsArray()) {
                    if (string.length() > 1) {
                        description += (string + " ");
                    }
                }
                System.out.println(description);
                descriptionList.add(description);
            }
            FunctionDescriptionGenerator functionDescriptionGenerator = new FunctionDescriptionGenerator(listOfFunctionNames, functionContentList, descriptionList, fileNamesMapped,fileContentsMapped, finalProjectRepository);



        DeleteFile deleteFile = new DeleteFile(fileName);
        DeleteFile deleteFile1 = new DeleteFile(listOfFunctionNames);
        return true;
    }

    @Override
    public ArrayList<Function> getFunctions(){
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

    private ArrayList<String> doesFileCompile(ArrayList<String> fileNameArray)
    {
        ArrayList<String> returnValue = new ArrayList<>();
        int counterVariable = 0;
        for (String fileNameValue: fileNameArray) {
            try {
                String output = Runtime.getRuntime().exec("g++ .\\" + fileNameValue + " -o out" + counterVariable).toString();
                System.out.println(output);
                counterVariable++;

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ArrayList<File> fileArrayList = new ArrayList<File>();
        ArrayList<String> fileOutputArrayList = new ArrayList<String>();
        for (int i = 0; i<fileNameArray.size();i++) {
            File myObj = new File("out"+i + ".exe");
            fileArrayList.add(myObj);
            fileOutputArrayList.add("out"+i + ".exe");
        }
        Long longTime = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int j =0; j< fileArrayList.size(); j++){

            if (fileArrayList.get(j).exists()) {
                System.out.println("File has compiled: " + fileArrayList.get(j).getName());
                returnValue.add(fileNameArray.get(j));
            } else {
                System.out.println("File has not compiled: " + fileArrayList.get(j).getName());
                DeleteFile deleteFile = new DeleteFile(fileNameArray.get(j));
            }
        }
        System.out.println(System.currentTimeMillis()-longTime);

        DeleteFile deleteFile = new DeleteFile(fileOutputArrayList);

        return returnValue;
    }

    @Override
    public ArrayList<Function> getSearchContents(String keywords)
    {
        // add logic here to do something with the keywords that were in the search bar
        String searchBarContents = keywords;
        //System.out.println(searchBarContents);



        searchBarContents = searchBarContents.replace("\"", "");
        //System.out.println(searchBarContents);
        String[] searchBarList = searchBarContents.split(" ");


        ArrayList<Function> functionList = getFunctions();
        ArrayList<Function> functionListAfterSearch = new ArrayList<>();
        for (Function function: functionList) {
            boolean shouldFunctionBeenAdded = false;
            int numberOfMatchingWords = 0;
            double startingScore = Double.parseDouble(function.getTotalKeywordWeight());
            double score = 0.0;
            for (Keyword keywordValue: function.getKeywordList()) {
                for (String string: searchBarList) {
                    if (keywordValue.getKeyword().contains(string.toLowerCase(Locale.ROOT)))
                    {
                        score = score + Double.parseDouble(keywordValue.getScore());
                        numberOfMatchingWords++;
                        shouldFunctionBeenAdded = true;
                    }
                }
            }
            if (shouldFunctionBeenAdded) {
                function.setTotalKeywordWeight(String.valueOf(score/startingScore));
                System.out.println(score/startingScore + " " + function.getFunctionName() + " number of matching words: " + numberOfMatchingWords);
                functionListAfterSearch.add(function);
            }
        }

        Collections.sort(functionListAfterSearch);
        Collections.reverse(functionListAfterSearch);


        return functionListAfterSearch;
    }
}
