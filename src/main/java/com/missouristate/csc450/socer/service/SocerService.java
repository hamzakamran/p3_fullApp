package com.missouristate.csc450.socer.service;

import com.missouristate.csc450.socer.TableEntryObjects.Function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface SocerService {
    ArrayList<String> addFunction(ArrayList<String> fileName, ArrayList<String> fileContents);
    List<Function> getFunctions();
    List<Function> getSearchContents(String keywords);
    void prePopulateDatabase() throws IOException;

    String getErrorString(String fileName, String fileContents);
}
