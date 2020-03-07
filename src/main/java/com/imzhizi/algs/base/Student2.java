package com.imzhizi.algs.base;

/**
 * created by zhizi
 * on 3/7/20 12:59
 */
public class Student2 {
    private String username;
    private Integer gender;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "username='" + username + '\'' +
                ", gender=" + gender +
                '}';
    }
}
