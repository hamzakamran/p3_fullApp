package com.missouristate.csc450.socer.HelperClasses;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;

public class WriteToFile {
  public WriteToFile(String fileName, ArrayList<String> fileContent) {
    try {
      FileWriter myWriter = new FileWriter(fileName);

    for (String string: fileContent) {
      myWriter.write(System.getProperty( "line.separator" ));
      myWriter.write(string);
    }
      myWriter.close();
      //System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      //System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public WriteToFile(ArrayList<String> fileNames, ArrayList<ArrayList<String>> fileContent) {
    for (int i =0; i< fileNames.size(); i++) {
      try {
        FileWriter myWriter = new FileWriter(fileNames.get(i));

        for (String string : fileContent.get(i)) {
          myWriter.write(System.getProperty("line.separator"));
          myWriter.write(string);
        }
        myWriter.close();
        //System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        //System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
  }

}