package com.example.loginsignupver1;

public class User_Login {
    private String emailPhone;
    private String password;

    public User_Login(String emailPhone, String password) {
        this.emailPhone = emailPhone;
        this.password = password;
    }

    public String getEmailPhone() {
        return emailPhone;
    }

    public String getPassword() {
        return password;
    }
}
