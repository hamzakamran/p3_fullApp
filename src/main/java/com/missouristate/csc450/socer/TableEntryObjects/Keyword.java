//package com.missouristate.csc450.socer.TableEntryObjects;
//
//import java.io.Serializable;
//
//@Entity
//@Table(name="keywords")
//public class Keyword implements Serializable {
//
//    private static final long serialVersionUID = -2060173648972096986L;
//
//    public Keyword() {
//        super();
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "keyword_id", columnDefinition = "INTEGER")
//    private int propertyId;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "function_name", referencedColumnName = "function_name", columnDefinition = "VARCHAR(64)")
//    private Fuction function;
//
//
//    @Column(name = "keyword", columnDefinition = "VARCHAR(32)")
//    private String eventKey;
//
//
//    @Column(name = "score", columnDefinition = "INTEGER")
//    private String score;
//
//
//    public int getPropertyId() {
//        return propertyId;
//    }
//
//    public void setPropertyId(int propertyId) {
//        this.propertyId = propertyId;
//    }
//
//    public Fuction getFunction() {
//        return function;
//    }
//
//    public void setFunction(Fuction function) {
//        this.function = function;
//    }
//
//    public String getEventKey() {
//        return eventKey;
//    }
//
//    public void setEventKey(String eventKey) {
//        this.eventKey = eventKey;
//    }
//
//    public String getScore() {
//        return score;
//    }
//
//    public void setScore(String score) {
//        this.score = score;
//    }
//}