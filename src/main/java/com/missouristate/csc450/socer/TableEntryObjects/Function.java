package com.missouristate.csc450.socer.TableEntryObjects;

import com.missouristate.csc450.socer.TableEntryObjects.Keyword;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="functions")
public class Function implements Serializable, Comparable<Function>{

    public Function() {
        super();
    }

    private static final long serialVersionUID = -4513604167770036149L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "function_id", columnDefinition = "INTEGER")
    private int functionId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "function", cascade = {CascadeType.ALL})
    List<Keyword> keywordList = new ArrayList<Keyword>();

    @Column(name = "function_name", columnDefinition = "VARCHAR(64)")
    private String functionName;

    @Column(name = "function_content", columnDefinition = "text")
    private String functionContents;

    @Column(name = "function_description", columnDefinition = "text")
    private String functionDescription;

    @Column(name = "total_keyword_weight", columnDefinition = "VARCHAR(128)")
    private String totalKeywordWeight;

    @Column(name = "file_name", columnDefinition = "VARCHAR(128)")
    private String fileName;

    @Column(name = "file_content", columnDefinition = "text")
    private String fileContents;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContents() {
        return fileContents;
    }

    public void setFileContents(String fileContents) {
        this.fileContents = fileContents;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public List<Keyword> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<Keyword> keywordList) {
        this.keywordList = keywordList;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String fileName) {
        this.functionName = fileName;
    }

    public String getFunctionContents() {
        return functionContents;
    }

    public void setFunctionContents(String functionContents) {
        this.functionContents = functionContents;
    }

    public String getTotalKeywordWeight() {
        return totalKeywordWeight;
    }

    public void setTotalKeywordWeight(String totalKeywordWeight) {
        this.totalKeywordWeight = totalKeywordWeight;
    }

    @Override
    public int compareTo(Function u) {
        if (getTotalKeywordWeight() == null || u.getTotalKeywordWeight() == null) {
            return 0;
        }
        return getTotalKeywordWeight().compareTo(u.getTotalKeywordWeight());
    }
}