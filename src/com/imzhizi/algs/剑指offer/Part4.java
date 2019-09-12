package com.imzhizi.algs.剑指offer;

import org.junit.Test;

import java.util.*;

public class Part4 {
    private PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.comparingInt((integer) -> -1 * integer));
    private PriorityQueue<Integer> right = new PriorityQueue<>();

    /**
     * [数据流中的中位数_牛客网]( https://www.nowcoder.com/practice/9be0172896bd43948f8a32fb954e1be1 )
     */
    @Test
    public void No41() {
        Insert(3);
        System.out.println(GetMedian());
        Insert(6);
        System.out.println(GetMedian());
        Insert(2);
        System.out.println(GetMedian());
        Insert(4);
        System.out.println(GetMedian());
    }

    public void Insert(Integer num) {
        if (left.isEmpty() || num <= left.peek()) {
            left.add(num);
        } else {
            right.add(num);
        }

        if (left.size() - right.size() > 1) {
            for (int i = 1; i < left.size() - right.size(); i++) {
                right.add(left.poll());
            }
        } else if (right.size() - left.size() > 1) {
            for (int i = 1; i < right.size() - left.size(); i++) {
                left.add(right.poll());
            }
        }
    }

    public Double GetMedian() {
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        } else if (left.size() > right.size()) {
            return left.peek() * 1.0;
        } else {
            return right.peek() * 1.0;
        }
    }


    /**
     * 从1到n中1的个数
     * [整数中1出现的次数（从1到n整数中1出现的次数）_牛客网]( https://www.nowcoder.com/practice/bd7f978302044eee894445e244c7eee6?tpId=13&tqId=11184&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking )
     */
    @Test
    public void No43() {
        System.out.println(NumberOf1Between1AndN_Solution2(21345));
        System.out.println(NumberOf1Between1AndN_Solution2(55));
        System.out.println(NumberOf1Between1AndN_Solution2(45));
        System.out.println(NumberOf1Between1AndN_Solution2(82));
        System.out.println(NumberOf1Between1AndN_Solution2(92));
        System.out.println(NumberOf1Between1AndN_Solution2(99));
        System.out.println(NumberOf1Between1AndN_Solution2(999));
        System.out.println(NumberOf1Between1AndN_Solution2(2));
        System.out.println(NumberOf1Between1AndN_Solution2(12));
        System.out.println(NumberOf1Between1AndN_Solution2(10000));
        System.out.println(NumberOf1Between1AndN_Solution2(10));

    }

    public int NumberOf1Between1AndN_Solution2(int n) {
        char[] cs = String.valueOf(n).toCharArray();
        int sum = 0;
        for (int i = cs.length - 1; i >= 0; i--) {
            sum += cal(cs, i);
        }
        return sum;
    }

    public int cal(char[] nums, int index) {
        int sum = 0;
        int power = nums.length - index - 1;
        if (nums[index] == '1') {
            sum += 1 + Math.pow(10, power - 1) * power;
            for (int i = index + 1; i < nums.length; i++) {
                sum += (nums[i] - '0') * Math.pow(10, nums.length - i - 1);
            }
        } else if (nums[index] > '1') {
            sum += Math.pow(10, power) + (nums[index] - '0') * power * Math.pow(10, power - 1);
        }
        return sum;
    }

    //    理解错题意了，是1出现的次数，而不是含1的数字的个数
    public int NumberOf1Between1AndN_Solution(int n) {
        int[] count = new int[10];
        count[0] = 1;
        int sum = 0;
        sum++;
        n = n / 10;
        for (int i = 1; n > 0; i++) {
            int mod = n % 10;
            sum = sum + mod * count[i - 1];
            if (mod > 1) sum += (int) Math.pow(10, i);
            n = n / 10;
            count[i] = (int) Math.pow(10, i) + 9 * count[i - 1];
        }
        return sum;
    }

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
     * [第一个只出现一次的字符_牛客网]( https://www.nowcoder.com/practice/1c82e8cf713b4bbeb2a5b31cf5b0417c )
     */
    @Test
    public void No46() {

    }

    public int FirstNotRepeatingChar(String str) {
        char[] cs = str.toCharArray();
        int[] record = new int[58];

        int length = cs.length;

        int[] count = new int[58];
        Arrays.fill(count, length);

        for (int i = 0; i < length; i++) {
            record[cs[i] - 'A']++;
            if (record[cs[i] - 'A'] == 1) {
                count[cs[i] - 'A'] = i;
            } else {
                count[cs[i] - 'A'] = length;
            }
        }

        Arrays.sort(count);
        return count[0] == length ? -1 : count[0];
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
}
