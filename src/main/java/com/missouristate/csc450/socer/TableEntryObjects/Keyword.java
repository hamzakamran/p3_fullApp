package com.missouristate.csc450.socer.TableEntryObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="keywords")
public class Keyword implements Serializable {

    private static final long serialVersionUID = -2060173648972096986L;

    public Keyword() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id", columnDefinition = "INTEGER")
    private int propertyId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "function_id", referencedColumnName = "function_id", columnDefinition = "INTEGER")
    private Function function;


    @Column(name = "keyword", columnDefinition = "VARCHAR(128)")
    private String keyword;


    @Column(name = "score", columnDefinition = "VARCHAR(128)")
    private String score;

    @Column(name = "function_name", columnDefinition = "VARCHAR(64)")
    private String functionName;

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String setFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}