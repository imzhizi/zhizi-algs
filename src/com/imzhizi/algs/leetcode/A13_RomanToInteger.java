package com.imzhizi.algs.leetcode;


import java.util.HashMap;

/**
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * created by zhizi on 2018/4/13
 */
public class A13_RomanToInteger {
    /**
     * 题目分析：
     * 经室友提点，总结了罗马数字的特点，发现不用写得那么复杂
     * 当存在一个罗马数字时，假如左边的单位小于右边，那么右边一定是要被减去的，而大于等于右边，则是加上去
     * 接下来的一个很长的循环是针对各种情况做了 if 判断
     *
     * 时长：68 ms / 10%
     *
     * 总结：
     * 做了修改之后代码的长度和可读性有所提升，不过时间倒是没太大变化😂
     */
    public static int romanToInt1(String s) {
        int length=s.length();
        int[] roman = new int[length];
        for (int i = 0; i < length; i++) {
            switch (s.charAt(i)) {
                case 'I':
                    roman[i] = 1;
                    break;
                case 'V'://5
                    roman[i] = 5;
                    break;
                case 'X':
                    roman[i] = 10;
                    break;
                case 'L'://50
                    roman[i] = 50;
                    break;
                case 'C':
                    roman[i] = 100;
                    break;
                case 'D'://500
                    roman[i] = 500;
                    break;
                case 'M':
                    roman[i] = 1000;
                    break;
            }
        }
        int sum = roman[length-1];
        for (int i = length - 2; i >= 0; i--) {
            if (roman[i] < roman[i + 1])
                sum -= roman[i];
            else sum += roman[i];
        }
        return sum;
    }

    /**
     * 题目分析：
     * 核心思路没有变，依然是通过 switch 对字母进行转换，但在 romanToInt1() 中是先进行转换然后处理，需要两次遍历
     * 其实可以在转换的同时进行处理，只需要一次遍历即可，但需要注意保存结果
     *
     * 运行时长：48ms / 75%
     *
     * 总结：
     * 算法分析还是要回归到最本初的，O(n) 的降低总是有效果的
     */
    public static int romanToInt1B(String s) {
        int num=0;
        int sum=0;
        for (int i = s.length()-1; i >=0 ; i--) {
            int last=num;
            switch (s.charAt(i)) {
                case 'I':
                    num = 1;
                    break;
                case 'V'://5
                    num = 5;
                    break;
                case 'X':
                    num = 10;
                    break;
                case 'L'://50
                    num = 50;
                    break;
                case 'C':
                    num = 100;
                    break;
                case 'D'://500
                    num = 500;
                    break;
                case 'M':
                    num = 1000;
                    break;
            }
            if (num>=last) sum+=num;
            else sum-=num;
        }

       return sum;
    }

    /**
     * 题目分析：
     * 使用了 map 替换的方式来进行值的计算，减少了一次的遍历，但维护一个HashMap成本同样很高，所以运行时间仍然很长
     *
     * 运行时长：52ms / 38%
     *
     * 总结：
     *
     */
    public static int romanToInt2(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int length=s.length();
        int result = map.get(s.charAt(length-1));
        for (int i = length - 2; i >= 0; i--) {
            if (map.get(s.charAt(i))<map.get(s.charAt(i+1)))
                result -= map.get(s.charAt(i));
            else result += map.get(s.charAt(i));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt1B("MMMDLXXXVI"));//3586
        System.out.println(romanToInt1B("MCMXCVI"));//1996
        System.out.println(romanToInt1B("MMMCCCXXXIII"));//3333
        System.out.println(romanToInt1B("M"));//1000
        System.out.println(romanToInt1B("XCIX"));//99
        System.out.println(romanToInt1B("CMIX"));//909
    }
}
