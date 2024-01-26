package com.example.logindemo;

import java.sql.Timestamp;
import java.util.Date;

public class receiver {

    String fullname;
    String address;
    String mobile_var;
    String bloodgr;
    String bloodunit;
    String accept_date;
    String status;
    //Timestamp accept_date;
    String user_reg;
    /*static String fullname;
   static String address;
   static String mobile_var;
   static String bloodgr;
   static String bloodunit;
   static String accept_dt;
   static String user_reg;

     public receiver(String fullname, String address, String mobile_var, String bloodgr, String bloodunit ,String user_reg,Timestamp accept_date) {
        this.fullname=fullname;
        this.address=address;
        this.mobile_var=mobile_var;
        this.bloodgr=bloodgr;
        this.bloodunit=bloodunit;
        this.user_reg=user_reg;
        this.accept_date=accept_date;
    }
*/
    public receiver(String fullname, String address, String mobile_var, String bloodgr, String bloodunit ,String user_reg,String accept_date,String status) {
        this.fullname=fullname;
        this.address=address;
        this.mobile_var=mobile_var;
        this.bloodgr=bloodgr;
        this.bloodunit=bloodunit;
        this.user_reg=user_reg;
        this.accept_date=accept_date;
        this.status=status;
    }
    public receiver(String fullname, String address, String mobile_var, String bloodgr, String bloodunit,String user_reg,String status ) {
        this.fullname=fullname;
        this.address=address;
        this.mobile_var=mobile_var;
        this.bloodgr=bloodgr;
        this.bloodunit=bloodunit;
        this.user_reg=user_reg;
        this.status=status;
    }
    public receiver() {
    }
   public receiver(String accept_date,String status) {
        this.accept_date=accept_date;
        this.status=status;
    }



    public String getUser_reg() {
        return user_reg;
    }
    public void setUser_reg(String user_reg) {
        this.user_reg = user_reg;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile_var(String mobile_var) {
        this.mobile_var = mobile_var;
    }

  /*  public Timestamp getAccept_date() {
        return accept_date;
    }

    public void setAccept_date(Timestamp accept_date) {
        this.accept_date = accept_date;
    }

    */
            public String getAccept_date() {
                return accept_date;
            }

            public void setAccept_date(String accept_date) {
                this.accept_date = accept_date;
            }
    public void setBloodgr(String bloodgr) {
        this.bloodgr = bloodgr;
    }

    public void setBloodunit(String bloodunit) {
        this.bloodunit = bloodunit;
    }



    public String getFullname() {
        return fullname;
    }

    public String getAddress() {
        return address;
    }
    public String getMobile_var() {
        return mobile_var;
    }

    public String getBloodgr() {
        return bloodgr;
    }

    public String getBloodunit() {
        return bloodunit;
    }
}
