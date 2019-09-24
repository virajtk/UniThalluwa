package com.scorpion.unithalluwa.data.model;

public class PastPapers {
    private String years;
    private String semester;
    private String Module;
    private String pastPaperURL;

    public PastPapers() {
    }

    public PastPapers(String years, String semester) {
        this.years = years;
        this.semester = semester;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getModule() {
        return Module;
    }

    public void setModule(String module) {
        Module = module;
    }

    public String getPastpaperURL() {
        return pastPaperURL;
    }

    public void setPastpaperURL(String pastpaperURL) {
        this.pastPaperURL = pastpaperURL;
    }
}
