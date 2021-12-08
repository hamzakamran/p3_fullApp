package com.missouristate.csc450.socer.HelperClasses;

import java.util.ArrayList;
import java.util.HashMap;

class FunctionData {
    private String function = "";
    private String functionName = "";
    private ArrayList<String> variables = new ArrayList<String>();
    private ArrayList<String> comments = new ArrayList<String>();
    private String documentDescription = "";
    private HashMap<String, String> parameters = new HashMap<String, String>();

    public FunctionData(String fn){
        function = fn;
    }

    // setters
    public void setFunction(String s){ function = s; }
    public void addComment(String s){ comments.add(s); }
    public void addVariable(String s){ variables.add(s); }
    public void setFunctionName(String s){ functionName = s; }
    public void setDocDescription(String s){ documentDescription = s; }
    public void addParameter(String name, String desc){  parameters.put(name, desc); }

    // getters
    public String getFunction(){ return function; }
    public String getFunctionName(){ return functionName; }
    public ArrayList<String> getComments(){ return comments; }
    public ArrayList<String> getVariables(){ return variables; }
    public String getDocDescription(){ return documentDescription; }
    public HashMap<String, String> getParameters(){ return parameters; }
}
