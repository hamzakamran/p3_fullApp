package com.missouristate.csc450.socer.service;

import com.missouristate.csc450.socer.TableEntryObjects.Function;

import java.util.ArrayList;
import java.util.List;

public interface SocerService {
    boolean addFunction(ArrayList<String> fileName, ArrayList<String> fileContents);
    List<Function> getFunctions();
    List<Function> getSearchContents(String keywords);

    String getErrorString(String fileName, String fileContents);
}
