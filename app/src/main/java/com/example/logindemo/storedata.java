package com.example.logindemo;

public class storedata {

    String username,password,mobileno,gender,emailaddress,bloodgrp,reg_date;
    //storedata user;

    public storedata(String username, String password, String mobileno, String gender, String bloodgrp,String reg_date) {
        this.username = username;
        this.password = password;
        //this.emailaddress = emailaddress;
        this.mobileno = mobileno;
        this.gender = gender;
        this.bloodgrp = bloodgrp;
        this.reg_date=reg_date;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //public String getEmailaddress(){return emailaddress;}

    //public void setEmailaddress(){this.emailaddress=emailaddress;}
    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;

    }

    public String getBloodgrp() {
        return bloodgrp;
    }

    public void setBloodgrp(String bloodgrp) {
        this.bloodgrp = bloodgrp;
    }
}
