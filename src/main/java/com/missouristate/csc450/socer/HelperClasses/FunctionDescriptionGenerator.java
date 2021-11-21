package com.missouristate.csc450.socer.HelperClasses;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

public class FunctionDescriptionGenerator {
    public FunctionDescriptionGenerator(String fileName){
        // create a list of documents
        ArrayList<Document> documents = new ArrayList<Document>();
        String fileNames[] = new String[]{
            fileName
                // add all the files from database here
        };
        for(String file : fileNames) documents.add(new Document(file));

        // calculate tfidf for all terms in all documents
        for(Document doc : documents) doc.calculateTfidf(documents);

        // generate result
        for(Document doc : documents) System.out.println(doc.generateRelevantKeywords());
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
            "    ", ",", "@", "/**", "*", "**/", "]", "//"
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
    
    public HashMap<Term, Document> generateRelevantKeywords(){
        HashMap<Term, Document> result = new HashMap<Term, Document>();
        System.out.println("-----" + title + "-----");
        for(Term term : terms){
            System.out.println(term.term + ": " + term.tfidf);
        }
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