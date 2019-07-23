package com.imzhizi.algs.CodingInterview;

import org.junit.Test;

import java.util.ArrayList;
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
}
