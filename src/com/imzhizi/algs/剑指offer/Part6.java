package com.imzhizi.algs.剑指offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Part6 {

    /**
     * 面试题60 : 个骰子的点数 未找到
     */
    @Test
    public void No60() {

    }

    /**
     * [扑克牌顺子_牛客网]( https://www.nowcoder.com/practice/762836f4d43d43ca9deb273b3de8e1f4 )
     */
    @Test
    public void No61() {
        System.out.println(isContinuous(new int[]{0, 0, 1, 2, 5}));
    }

    public boolean isContinuous(int[] numbers) {
        int[] record = new int[14];

        for (int i : numbers) {
            record[i]++;
        }

        int count = 0;
        for (int i = 1; i < 14; i++) {
            if (record[i] == 2) return false;
            if (count == 5) return true;
            if (count != 0 && record[i] == 0) {
                if (record[0] > 0) {
                    record[0]--;
                    count++;
                } else {
                    return false;
                }
            }
            if (record[i] == 1) count++;
        }

        return false;
    }


    /**
     * [孩子们的游戏(圆圈中最后剩下的数)_牛客网]( https://www.nowcoder.com/practice/f78a359491e64a50bce2d89cff857eb6 )
     */
    @Test
    public void No62() {
        Assert.assertEquals(3, LastRemaining_Solution(5, 3));
    }

    public int LastRemaining_Solution(int n, int m) {
        List<Integer> list = new ArrayList<Integer>();
        m--;
        for (int i = 0; i < n; i++) list.add(i);
        int loc = 0;
        while (list.size() > 1) {
            loc += m % list.size();
            loc %= list.size();
            list.remove(loc);
        }

        return list.isEmpty() ? -1 : list.get(0);
    }

    /**
     * [求1+2+3+...+n_牛客网]( https://www.nowcoder.com/practice/7a0da8fc483247ff8800059e12d7caf1 )
     */
    @Test
    public void No64() {
        System.out.println(Sum_Solution(5));
    }

    public int Sum_Solution(int n) {
        Solution[] ss=new Solution[n];
        return Solution.sum;
    }


    /**
     * [不用加减乘除做加法_牛客网]( https://www.nowcoder.com/practice/59ac416b4b944300b617d4f7f111b215 )
     * todo
     */
    @Test
    public void No65() {
    }

    /**
     * [构建乘积数组_牛客网]( https://www.nowcoder.com/practice/94a4d381a68b47b7a8bed86f2975db46 )
     * todo
     */
    @Test
    public void No66() {
    }


    /**
     * [把字符串转换成整数_牛客网]( https://www.nowcoder.com/practice/1277c681251b4372bdef344468e4f26e )
     */
    @Test
    public void No67() {
        System.out.println(StrToInt("123"));
    }

    public int StrToInt(String str) {
        int length = str.length();
        if (length == 0) return 0;

        char[] cs = str.toCharArray();
        int i = 1;
        int flag = 1;
        if (cs[0] == '-') flag = -1;
        else if (cs[0] != '+') i--;

        int num = 0;
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') num = (c - '0') + num * 10;
            else return 0;
        }

        return num * flag;
    }
}
