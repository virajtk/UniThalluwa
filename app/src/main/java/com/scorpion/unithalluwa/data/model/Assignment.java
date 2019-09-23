package com.scorpion.unithalluwa.data.model;

public class Assignment {
   private String assTitle;
   private String year;
   private String sem;
   private String module;
   private String assURL;

    public Assignment() {
    }

    public String getAssTitle() {
        return assTitle;
    }

    public void setAssTitle(String assTitle) {
        this.assTitle = assTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAssURL() {
        return assURL;
    }

    public void setAssURL(String assURL) {
        this.assURL = assURL;
    }
}
