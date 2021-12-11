/**

 -- AUTHORS --
 + Hamza Kamran
 + Adam Gibbons
 + Kimmy Thach

 -- DESCRIPTION: --
 This is a class that will delete whatever filename is passed in.

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

import java.io.File;  // Import the File class
import java.util.ArrayList;


public class DeleteFile {
   public DeleteFile(ArrayList<String> fileName) {
       for (String string: fileName) {


           File myObj = new File(string);
           //System.out.println(myObj);

           if (myObj.delete()) {
  //             System.out.println("Deleted the file: " + myObj.getName());
           } else {
  //             System.out.println("Failed to delete the file.");
           }
       }
   }

    public DeleteFile(String fileName) {

        File myObj = new File(fileName);
        //System.out.println(myObj);

        if (myObj.delete()) {
 //           System.out.println("Deleted the file: " + myObj.getName());
        } else {
//            System.out.println("Failed to delete the file.");
        }
    }
}