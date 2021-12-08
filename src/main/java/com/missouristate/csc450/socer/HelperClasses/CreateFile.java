package com.missouristate.csc450.socer.HelperClasses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateFile {
  public CreateFile(String fileName) {
    try {
      File myObj = new File(fileName);
      if (myObj.createNewFile()) {
        //System.out.println("File created: " + myObj.getName());
      } else {
        //System.out.println("File already exists.");
      }
    } catch (IOException e) {
      //System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public CreateFile(ArrayList<String> fileNames) {
    for (String fileName : fileNames) {
      try {
        File myObj = new File(fileName);
        if (myObj.createNewFile()) {
          //System.out.println("File created: " + myObj.getName());
        } else {
          //System.out.println("File already exists.");
        }
      } catch (IOException e) {
        //System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
  }
}