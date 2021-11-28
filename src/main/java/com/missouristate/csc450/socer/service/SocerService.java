package com.missouristate.csc450.socer.service;

import com.missouristate.csc450.socer.TableEntryObjects.Function;

import java.util.List;

public interface SocerService {
    boolean addFunction(String fileName, String fileContents);
    List<Function> getFunctions();
}
