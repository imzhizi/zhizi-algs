package com.imzhizi.algs.base;

/**
 * created by zhizi
 * on 3/7/20 12:39
 */
public class Student1 {
    private String username;
    private String pwd;
    private String gender;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Student1(String username, String pwd, String gender) {
        this.username = username;
        this.pwd = pwd;
        this.gender = gender;
    }

    public Student1() {
    }

    @Override
    public String toString() {
        return "Student1{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
