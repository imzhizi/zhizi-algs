package com.imzhizi.algs.leetcode;


/**
 * 13. Roman to Integer
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * created by zhizi on 2018/4/13
 */
public class A13_RomanToInteger {
    /**
     * 函数描述：
     * 经室友提点，总结了罗马数字的特点，发现不用写得那么复杂
     * 当存在一个罗马数字时，假如左边的单位小于右边，那么右边一定是要被减去的，而大于等于右边，则是加上去
     * 接下来的一个很长的循环是针对各种情况做了if判断
     * 时长：130 ms
     * <p>
     * 总结：
     * 做了修改之后代码的长度和可读性有所提升，不过时间倒是没太大变化😂
     */
    public static int romanToInt2(String s) {
        int[] roman = new int[s.length()];
        for (int i = roman.length - 1; i >= 0; i--) {
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
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (i == s.length() - 1 || roman[i] >= roman[i + 1])
                result += roman[i];
            else result -= roman[i];
        }
        return result;
    }

    /**
     * 函数描述：
     * 非常无脑的办法，因为各单位之间没有关系，所以使用switch函数将String转化为有一定意义的char[]
     * 接下来的一个很长的循环是针对各种情况做了if判断
     * 时长：139 ms
     * <p>
     * 总结：
     * 程序非常的长，耗时也比较久
     */
    public static int romanToInt(String s) {
        char[] cs = s.toCharArray();

        for (int i = cs.length - 1; i >= 0; i--) {
            switch (cs[i]) {
                case 'I':
                    cs[i] = '0';
                    break;
                case 'V'://5
                    cs[i] = '1';
                    break;
                case 'X':
                    cs[i] = '2';
                    break;
                case 'L'://50
                    cs[i] = '3';
                    break;
                case 'C':
                    cs[i] = '4';
                    break;
                case 'D'://500
                    cs[i] = '5';
                    break;
                case 'M':
                    cs[i] = '6';
                    break;
            }
        }

        short[] total = new short[4];
        int position;

        for (int i = cs.length - 1; i >= 0; i--) {
            position = (cs[i] - 48) / 2;
            if ((cs[i] - 48) % 2 == 0) {
                if (i > 1 && cs[i] == cs[i - 1] && cs[i] == cs[i - 2]) { //3
                    total[position] += 3;
                    if (i > 2 && cs[i] == cs[i - 3] - 1) {//8 VIII
                        total[position] += 5;
                        i--;
                    }
                    i -= 2;
                } else if (i > 1 && cs[i] == cs[i - 1]) { //2
                    total[position] += 2;
                    if (i > 2 && cs[i] == cs[i - 2] - 1) {//7 VII
                        total[position] += 5;
                        i--;
                    }
                    i--;
                } else if (i > 0 && cs[i] == cs[i - 1] - 1) {//6 VI XC
                    total[position] += 6;
                    i--;
                } else if (i > 0 && cs[i] == cs[i - 1] + 2) {//9 IX
                    position--;
                    total[position] += 9;
                    i--;
                } else {
                    total[position]++; //1
                }
            } else {
                if (i > 0 && cs[i] == cs[i - 1] + 1) {//4 IV
                    total[position] += 4;
                    i--;
                } else {
                    total[position] += 5;
                }
            }
        }
        return total[3] * 1000 + total[2] * 100 + total[1] * 10 + total[0];
    }

    public static void main(String[] args) {
        System.out.println(romanToInt2("MMMDLXXXVI"));//3586
        System.out.println(romanToInt2("MCMXCVI"));//1996
        System.out.println(romanToInt2("MMMCCCXXXIII"));//3333
        System.out.println(romanToInt2("M"));//1000
        System.out.println(romanToInt2("XCIX"));//99
        System.out.println(romanToInt2("CMIX"));//909
    }
}
