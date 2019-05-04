package com.imzhizi.algs.BUPT;

/**
 * created by zhizi on 2018/6/21
 */
class Student {
    String name;
    String sex;
    String year;
    String month;
    String day;

    Student(String s) {
        String[] info = s.split(" ");
        name = info[0];
        sex = info[1];
        String[] birth = info[2].split("/");
        year = birth[0];
        month = birth[1];
        day = birth[2];
    }
}
