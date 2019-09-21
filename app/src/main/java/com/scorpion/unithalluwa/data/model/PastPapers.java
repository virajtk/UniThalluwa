package com.scorpion.unithalluwa.data.model;

public class PastPapers {
    private String year;
    private String semester;

    public PastPapers(String year, String semester){
        this.year = year;
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }
}
