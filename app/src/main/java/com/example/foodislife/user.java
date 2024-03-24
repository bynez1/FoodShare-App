package com.example.foodislife;

import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class user
{
    String donor_name;
    String fooditem;
    String phone;
    String description;
    String userid;
    String address;


    public user() {

    }


    public user(String donor_name, String fooditem, String phone, String description, String address, String userid) {
        this.donor_name = donor_name;
        this.fooditem = fooditem;
        this.phone = phone;
        this.description = description;
        this.address = address;
        this.userid = userid;
    }



    public String getName() {
        return donor_name;
    }

    public String getFood() {
        return fooditem;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {return description;}
    public String getAddress() {return address;}

    public String getUserid() {
        return userid;
    }
}
