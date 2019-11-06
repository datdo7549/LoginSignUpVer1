package com.example.loginsignupver1;

public class User_Register {
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String dob;
    private int gender;

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDob() {
        return dob;
    }

    public int getGender() {
        return gender;
    }

    public User_Register(String password, String fullName, String email, String phone, String address, String dob, int gender) {
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
    }
}
