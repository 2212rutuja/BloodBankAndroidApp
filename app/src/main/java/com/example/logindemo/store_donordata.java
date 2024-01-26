package com.example.logindemo;

public class store_donordata {

    String fullname, address, mobile_var, bloodgr, healthissue,accept_dt,status;
    int donation_count;
    public store_donordata(String fullname,String address,String mobile_var,String bloodgr,String healthissue,String accept_dt,String status,int donation_count){
        this.fullname=fullname;
        this.address=address;
        this.mobile_var=mobile_var;
        this.bloodgr=bloodgr;
        this.healthissue=healthissue;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccept_dt() {
        return accept_dt;
    }

    public void setAccept_dt(String accept_dt) {
        this.accept_dt = accept_dt;
    }

    public String getFullname(){ return fullname;}

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile_var() {
        return mobile_var;
    }

    public void setMobile_var(String mobile_var) {
        this.mobile_var = mobile_var;
    }

    public String getBloodgr() {
        return bloodgr;
    }

    public void setBloodgr(String bloodgr) {
        this.bloodgr = bloodgr;
    }

    public String getHealthissue() {
        return healthissue;
    }

    public void setHealthissue(String healthissue) {
        this.healthissue = healthissue;
    }
}
