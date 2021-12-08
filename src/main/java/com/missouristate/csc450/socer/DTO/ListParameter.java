package com.missouristate.csc450.socer.DTO;

import java.util.ArrayList;

public class ListParameter {

    ArrayList<FileNameAndContents> fileNameAndContents;

    public ArrayList<FileNameAndContents> getFileNameAndContents() {
        return fileNameAndContents;
    }

    public void setFileNameAndContents(ArrayList<FileNameAndContents> fileNameAndContents) {
        this.fileNameAndContents = fileNameAndContents;
    }
}
