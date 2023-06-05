package com.example.backend.mybatis.po;

public class User {
    private int userID;
    private String username;
    private String password;
    private String email;
    private String phone;
    private int schedule;
    public User(int userID, String username, String password, String email, String
            phone, int schedule) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.schedule = schedule;
    }
    public User(String username, String password, String email, String phone, int
            schedule) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.schedule = schedule;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
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
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) { this.phone = phone; }
    public int getSchedule() { return schedule; }
    public void setSchedule(int schedule) { this.schedule = schedule; }
}
