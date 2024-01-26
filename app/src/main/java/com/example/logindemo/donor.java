package com.example.logindemo;

public class donor {
    String fullname,bloodgr,address, mobile_var, healthissue,status,accept_dt,next_donation;
    int donation_count;
    public donor(String fullname, String bloodgr, String address, String mobile_var, String healthissue, String accept_dt, String status,int donation_count) {
        this.fullname = fullname;
        this.bloodgr = bloodgr;
        this.address = address;
        this.mobile_var = mobile_var;
        this.healthissue = healthissue;
        this.accept_dt = accept_dt;
        this.status = status;
        this.donation_count=donation_count;
    }
    public donor(String fullname, String bloodgr, String address, String mobile_var, String healthissue,String status) {
        this.fullname = fullname;
        this.bloodgr = bloodgr;
        this.address = address;
        this.mobile_var = mobile_var;
        this.healthissue = healthissue;
        this.status=status;

    }
    public donor(String accept_dt,String status,int donation_count){
        this.accept_dt=accept_dt;
        this.status=status;
        this.donation_count=donation_count;
    }

    public int getDonation_count() {
        return donation_count;
    }

    public void setDonation_count(int donation_count) {
        this.donation_count = donation_count;
    }

    public donor() {
    }

    public String getAccept_dt() {
        return accept_dt;
    }

    public void setAccept_dt(String accept_dt) {
        this.accept_dt = accept_dt;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setBloodgr(String bloodgr) {
        this.bloodgr = bloodgr;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile_var(String mobile_var) {
        this.mobile_var = mobile_var;
    }

    public void setHealthissue(String healthissue) {
        this.healthissue = healthissue;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullname() {
        return fullname;
    }

    public String getBloodgr() {
        return bloodgr;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile_var() {
        return mobile_var;
    }

    public String getHealthissue() {
        return healthissue;
    }
}
