package com.imzhizi.algs.CodingInterview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {
    /**
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     */
    @Test
    public void test44() {
//    todo
    }

    public String PrintMinNumber(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        List<String> nums = new ArrayList<>();
        for (int number : numbers) {
            nums.add(String.valueOf(number));
        }
        for (int i = 0; i < nums.size(); i++) {
            char min = nums.get(0).charAt(0);

            for (String num : nums) {

            }

        }

        return sb.toString();
    }

    @Test
    public void No45() {

    }

    /**
     * https://www.nowcoder.com/practice/1c82e8cf713b4bbeb2a5b31cf5b0417c
     * 第一个只出现一次的字符的位置
     */
    @Test
    public void No46() {

    }

    public int FirstNotRepeatingChar(String str) {
        char[] cs=str.toCharArray();
        int[] record = new int[58];

        int length=cs.length;

        int[] count = new int[58];
        Arrays.fill(count,length);

        for(int i=0;i<length;i++){
            record[cs[i]-'A']++;
            if(record[cs[i]-'A']==1){
                count[cs[i]-'A']=i;
            }else{
                count[cs[i]-'A']=length;
            }
        }

        Arrays.sort(count);
        return count[0]==length?-1:count[0];
    }

    /**
     *
     */
    @Test
    public void No47() {

    }

    /**
     *
     */
    @Test
    public void No48() {

    }

    /**
     *
     */
    @Test
    public void No49() {

    }

    /**
     *
     */
    @Test
    public void No50() {

    }

    /**
     *
     */
    @Test
    public void No51() {

    }
}
