package com.imzhizi.algs.leetcode;

import java.util.Arrays;

public class A0_HelloWorld {
    /**
     * 题目分析：
     *
     *
     * 运行时长：
     *
     * 总结：
     *
     */
    public static void main(String[] args) {
        int x=0;
        int y=0;
        for (x = 30,y=0;  x>=10||y<10;x--,y++) {
            x/=2;
            y+=2;
        }
        System.out.println(x+","+y);
    }
}
