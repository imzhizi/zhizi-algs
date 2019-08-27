package com.imzhizi.algs.剑指offer;

import org.junit.Test;

public class Part6 {

    /**
     *
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
     *
     */
    @Test
    public void No67() {
        System.out.println(StrToInt("123"));
    }

    public int StrToInt(String str) {
        int length = str.length();
        if (length==0) return 0;

        char[] cs = str.toCharArray();
        int i = 1;
        int flag = 1;
        if(cs[0] == '-') flag = -1;
        else if (cs[0] != '+') i--;

        int num = 0;
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') num = (c - '0') +num*10;
            else return 0;
        }

        return num * flag;
    }
}
