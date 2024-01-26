package com.example.logindemo;

public  class user {
    public user(){}

    public user(String username, String mobileno, String bloodgrp, String gender) {
        this.username = username;
        this.mobileno = mobileno;
        this.bloodgrp = bloodgrp;
        this.gender = gender;

    }

    static String username;
    static String mobileno;
    static String bloodgrp;
    static String gender;

    public  String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getBloodgrp() {
        return bloodgrp;
    }

    public void setBloodgrp(String bloodgrp) {
        this.bloodgrp = bloodgrp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
