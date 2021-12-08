package com.missouristate.csc450.socer.HelperClasses;

import java.io.File;  // Import the File class
import java.util.ArrayList;


public class DeleteFile {
   public DeleteFile(ArrayList<String> fileName) {
       for (String string: fileName) {


           File myObj = new File(string);
           //System.out.println(myObj);

           if (myObj.delete()) {
               System.out.println("Deleted the file: " + myObj.getName());
           } else {
               System.out.println("Failed to delete the file.");
           }
       }
   }

    public DeleteFile(String fileName) {
        File myObj = new File(fileName);
        //System.out.println(myObj);

        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}