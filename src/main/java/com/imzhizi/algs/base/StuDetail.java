package com.imzhizi.algs.base;

import java.util.Date;

/**
 * created by zhizi
 * on 3/7/20 12:50
 */
public class StuDetail extends Student1 {
    private Integer age;
    private Date date;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DemoChildClass{" +
                super.toString() +
                "age=" + age +
                ", date=" + date +
                '}';
    }
}
