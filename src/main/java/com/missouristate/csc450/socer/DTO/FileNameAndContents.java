package com.missouristate.csc450.socer.DTO;

public class FileNameAndContents {
    private String fileName;
    private String[] fileContents;

    public String getFileName() {
        return fileName;
    }

    public String[] getFileContents() {
        return fileContents;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileContents(String[] fileContents) {
        this.fileContents = fileContents;
    }
}
