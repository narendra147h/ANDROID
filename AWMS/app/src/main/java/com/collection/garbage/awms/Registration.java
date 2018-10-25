package com.collection.garbage.awms;

/**
 * Created by adpr270759 on 17-04-2018.
 */

public class Registration {

    private String name;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String pincode;

    public Registration() {
    }


    public Registration(String name, String email, String password, String gender, String address, String pincode) {
        this.name = name;

        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPincode() {
        return pincode;
    }



}
