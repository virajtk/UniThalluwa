package com.scorpion.unithalluwa.data.model;

public class User {

    String userName;
    String regNumber;
    String email;
    String role;

    public User() {

    }

    public User(String userName, String regNumber, String email, String role) {
        this.userName = userName;
        this.regNumber = regNumber;
        this.email = email;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
