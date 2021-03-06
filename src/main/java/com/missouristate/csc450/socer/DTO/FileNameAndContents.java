/**

 -- AUTHORS --
 + Hamza Kamran
 + Adam Gibbons
 + Kimmy Thach

 -- DESCRIPTION: --
 This is a Data transfer object. The data that we get from the javascript is converted to an object of this type.

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
package com.missouristate.csc450.socer.DTO;

public class FileNameAndContents {
    private String fileName;
    private String fileURL;

    public String getFileName() {
        return fileName;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
}
