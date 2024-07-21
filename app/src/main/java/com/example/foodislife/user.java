package com.example.foodislife;

import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class user
{

    String userid;
    String Organization;
    String foodtype;
    String phone;
    String description;
    String address;


    public user() {

    }


    public user(String organization, String foodtype, String phone, String description, String address, String userid) {

        this.Organization = organization;
        this.foodtype = foodtype;
        this.phone = phone;
        this.description = description;
        this.address = address;
        this.userid = userid;
    }

// getters
    //public String getOrganization() {return Organization;}

    public String getOrganization() {return Organization;}

    public String getFoodtype() {return foodtype;}

    public String getPhone() {
        return phone;
    }

    public String getDescription() {return description;}
    public String getAddress() {return address;}

    public String getUserid() {
        return userid;
    }
}
