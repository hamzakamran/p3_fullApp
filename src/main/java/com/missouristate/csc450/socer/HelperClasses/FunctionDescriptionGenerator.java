/**

 -- AUTHORS --
 + Hamza Kamran
 + Adam Gibbons
 + Kimmy Thach

 -- DESCRIPTION: --
 This is a file that will generate the keywords for the data by using the term frequency of the function.

 -- FUNCTIONAL REQUIREMENT(S) MET: --
 + None

 -- NONFUNCTIONAL REQUIREMENT(S) MET: --
 + None

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


package com.missouristate.csc450.socer.HelperClasses;

import com.missouristate.csc450.socer.DAO.FinalProjectRepository;
import com.missouristate.csc450.socer.TableEntryObjects.Function;
import com.missouristate.csc450.socer.TableEntryObjects.Keyword;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;



public class FunctionDescriptionGenerator {
    public FunctionDescriptionGenerator(ArrayList<String> fileName, ArrayList<String> functionBody, ArrayList<String> functionDescription, ArrayList<String> fileNamesMapped, ArrayList<String> fileContentsMapped, FinalProjectRepository finalProjectRepository){
        // create a list of documents
        ArrayList<Document> documents = new ArrayList<Document>();
//        String fileNames[] = new String[] {
//
//            fileName
//                // add all the files from database here
//        };
        ArrayList<String> fileNames = fileName;
        for(String file : fileNames) documents.add(new Document(file));

        // calculate tfidf for all terms in all documents
        for(Document doc : documents) doc.calculateTfidf(documents);

        for (int i=0; i< documents.size(); i++ )
        {
            Function function = new Function();
            //System.out.println(functionBody.get(i));
            function.setFunctionContents(functionBody.get(i));
            function.setFileName(fileNamesMapped.get(i));
            function.setFunctionName(fileName.get(i));
            function.setFileContents(fileContentsMapped.get(i));
            function.setFunctionDescription(functionDescription.get(i));

            System.out.println(documents.get(i).generateRelevantKeywords(function, finalProjectRepository));
        }
        // generate result
//        for(Document doc : documents) {
//            Function function = new Function();
//            function.setFunctionContents(functionBody);
//            function.setFileName(fileName);
//
//            System.out.println(doc.generateRelevantKeywords(function, finalProjectRepository));
//        }
    }
}

class Document {

    String title;
    ArrayList<String> words = new ArrayList<String>();
    ArrayList<Term> terms = new ArrayList<Term>();
    public Document(String t){
        this.title = t;
        String removeable[] = new String[]{
            ";", "{", "}", "<", ">", "==", "=", "+", "-", "*", "/", "+=", "-=", "++", "--", 
            "    ", ",", "@", "/**", "*", "**/", "]", "//", "%", "\'", "    ", "\"", "\\n", ".", ":"
        };
        String replaceWithSpace[] = new String[]{
            "(", ")", "["
        };
        //read file
        try {
            File myFile = new File(title);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine(); // get line from file
                for(int i = 0; i  < replaceWithSpace.length; i ++){
                    data = data.replace(replaceWithSpace[i], " ");
                }
                for(int i = 0; i < removeable.length; i ++){
                    data = data.replace(removeable[i], ""); // replace punctuation
                }
                // split into words
                String processedData[] = data.split(" ");
                for(String word : processedData){
                    words.add(word.toLowerCase()); // add words into arraylist
                }
            }
            myReader.close();
            for(int i = 0; i < words.size(); i ++){
                if(words.get(i).length() == 0 || words.get(i) == " "){
                    words.remove(i);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // add unique words into terms
        for(String word : words){
            boolean containsWord = false;
            for(Term term :  terms){
                if(term.term.equals(word)){
                    containsWord = true;
                }
            }
            if(!containsWord){
                terms.add(new Term(word));
            }
        }
    }

    public void calculateTfidf(ArrayList<Document> documents){
        for(Term term : terms){
            term.calculateTf(words);
            term.calculateDf(documents);
            term.calculateIdf(documents);
            term.calculateTfidf();
        }
    }
    
    public HashMap<Term, Document> generateRelevantKeywords(Function function, FinalProjectRepository finalProjectRepository){
        HashMap<Term, Document> result = new HashMap<Term, Document>();
        List<Keyword> keywordList = new ArrayList<>();
        System.out.println("-----" + title + "-----");
        double total = 0.0;
        for(Term term : terms){
            //System.out.println(term.term + ": " + term.tfidf);
            Keyword keyword = new Keyword();
            keyword.setFunction(function);
            keyword.setFunctionName(title);
            keyword.setScore(""+(term.tfidf));
            keyword.setKeyword(term.term);
           if (term.term.length() > 2) {
               keywordList.add(keyword);
               total += term.tfidf;
           }


        }
        function.setTotalKeywordWeight("" + total);
        function.setKeywordList(keywordList);
        finalProjectRepository.save(function);

        return result;
    }
}

class Term {
    double tf, df, idf, tfidf;
    String term;

    public Term(String s){
        this.tf = 0;
        this.df = 0;
        this.idf = 0;
        this.tfidf = 0;
        this.term = s;
    }

    public double calculateTf(ArrayList<String> words){
        double count = 0;
        for(String word : words)
            if(term.equals(word)) 
                count ++;
        tf = (double)(count / words.size());
        return tf;
    }

    public double calculateDf(ArrayList<Document> documents){
        df = 0;
        for(Document doc : documents){
            if(doc.words.contains(term))
                df ++;
        }
        return df;
    }

    public double calculateIdf(ArrayList<Document> documents){
        idf = Math.log(documents.size() / df);
        return idf;
    }

    public double calculateTfidf(){
        tfidf = tf * idf;
        return tfidf;
    }
}