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
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}