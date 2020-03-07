package com.imzhizi.algs.剑指offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Part4 {
    /**
     * [最小的K个数_牛客网]( https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf )
     */
    @Test
    public void No40() {
        System.out.println(GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 8, 2}, 4));
    }

    // 最小的K个数
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (k > input.length) return result;

        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] > input[j]) {
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
            result.add(input[i]);
        }
        return result;
    }


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
     * [连续子数组的最大和_牛客网]( https://www.nowcoder.com/practice/459bd355da1549fa8a49e350bf3df484 )
     */
    @Test
    public void No42() {

    }

    public int FindGreatestSumOfSubArray(int[] array) {
        int[] result = new int[array.length];
        result[0] = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (result[i - 1] > 0) {
                result[i] = array[i] + result[i - 1];
            } else {
                result[i] = array[i];
            }
            if (result[i] > max) max = result[i];
        }

        return max;
    }

    /**
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

    public int NumberOf1Between1AndN_Solution(int n) {
        //    理解错题意了，是1出现的次数，而不是含1的数字的个数
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
     * 数字序列中某一位的数字
     * 数字序列为 1234567891011
     * 那么第10位数字是1，第十一位为0，那可以找到的规律是一位数9个，二位数90个
     */
    @Test
    public void test44() {
        System.out.println(getNumberFromNumberSeq(1023));
    }

    public int getNumberFromNumberSeq(int k) {
        int limit = 9;
        int layer = 1;

        while (k > limit) {
            k -= limit;
            layer++;
            limit = (int) (9 * Math.pow(10, layer - 1) * layer);
        }

        int count = k / layer - 1;
        int mod = k % layer;
        int target = (int) (Math.pow(10, layer - 1) + count);

        if (mod == 0) return target % 10;
        else return String.valueOf(target + 1).charAt(mod - 1) - '0';
    }

    /**
     * [把数组排成最小的数_牛客网]( https://www.nowcoder.com/practice/8fecd3f8ba334add803bf2a06af1b993 )
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     * #大数处理
     */
    @Test
    public void test45() {
        System.out.println(PrintMinNumber(new int[]{3, 32, 321}));
    }

    public String PrintMinNumber(int[] numbers) {
        if (numbers.length == 1) return String.valueOf(numbers[0]);
        int[] results = new int[numbers.length];
        results[0] = numbers[0];
        int length = 1;

        for (int i = 1; i < numbers.length; i++) {
            order(results, length, numbers[i]);
            length++;
        }

        StringBuilder result = new StringBuilder();
        for (int value : results) {
            result.append(value);
        }

        return result.toString();
    }

    void order(int[] results, int length, int num) {
        for (int i = 0; i < length; i++) {
            if ((num + "" + results[i]).compareTo(results[i] + "" + num) < 0) {
                System.arraycopy(results, i, results, i + 1, length - i);
                results[i] = num;
                break;
            }
        }
        if (results[length] == 0) {
            // 说明未插入，即大于所有数字
            results[length] = num;
        }
    }

    /**
     * 46. 把数字翻译成字符串
     * 用 0～25 表示字母 a～z，此时对于一个序列，123可以有不同的翻译——「abc」「mc」「ax」
     * 希望知道可以翻译的种类
     */
    @Test
    public void test46() {
        System.out.println(strCountFromNumber(12258));
    }

    int strCountFromNumber(int number) {
        return strCountFromStr(String.valueOf(number));
    }

    int strCountFromStr(String str) {
        if (str.length() == 1) return 1;

        if (str.charAt(0) == '1' || (str.charAt(0) == '2' && str.charAt(1) < '6')) {
            if (str.length() == 2) return 2;
            return strCountFromStr(str.substring(1)) + strCountFromStr(str.substring(2));
        } else {
            return strCountFromStr(str.substring(1));
        }
    }

    /**
     * 47. 礼物的最大价值
     * 在一个m×n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物，请计算你最多能拿到多少价值的礼物？
     */
    @Test
    public void No47() {
        int[][] matrix = {
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}
        };
        int[][] matrix2 = {
                {1, 10, 3, 8}
        };
        System.out.println(highestValue(matrix));
        System.out.println(highestValue(matrix2));
    }

    int highestValue(int[][] matrix) {
        return highestValue(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    int highestValue(int[][] matrix, int r, int c) {
        if (r != 0 && c != 0) {
            return Math.max(highestValue(matrix, r, c - 1), highestValue(matrix, r - 1, c))
                    + matrix[r][c];
        } else if (r == 0 && c == 0) {
            return matrix[r][c];
        } else if (r == 0) {
            return matrix[r][c] + highestValue(matrix, r, c - 1);
        } else {
            return matrix[r][c] + highestValue(matrix, r - 1, c);
        }
    }

    /**
     * 面试题48 :最长不含重复字符的子字符串. 未找到
     * 比如说一个字符串 "arabacdf"，那么最长的不含重复子字符串的就是 "bacdf"
     * 最基本的思路：
     * 为每一位都建立一个HashSet，依次注入，如果已存在，那就停止注入
     * 此时HashSet中的个数就是子字符串的长度，HashSet的位置就是子字符串的开始位置
     * 时间复杂度：O(n^2)， 还有一些具体的问题需要处理，比如说停止注入的判断
     * <p>
     * 动态规划方法：
     * 计算出以每一位开始的子字符串的长度，比较即可得出最大长度
     * 应该是从上一个未出现重复的字符到上一个冲突字符之间的子字符串长度可以确定
     * 而长度就是这一个冲突字符到每个字符的距离，显然最大距离就是第一个未重复字符到这个冲突字符的距离
     */
    @Test
    public void No48() {
        System.out.println(LongestDiffSubString1("arabacdf"));
        System.out.println(LongestDiffSubString2("aracacdf"));
    }

    int LongestDiffSubString1(String str) {
        int[] map = new int[26];
        int[] lengths = new int[str.length()];
        int loc = 0;

        for (int i = 0; i < str.length(); i++) {
            int cr = str.charAt(i) - 'a';
            if (map[cr] != 0) {
                // 如果先前已经存在，需要进行的处理
                // 应该是从上一个未出现重复的字符到冲突字符之间的子字符串长度都确定了
                while (loc < map[cr]) {
                    lengths[loc] = i - loc;
                    loc++;
                }
            }
            map[cr] = i + 1;
        }

        while (loc < str.length()) {
            lengths[loc] = str.length() - loc;
            loc++;
        }

        Arrays.sort(lengths);
        return lengths[lengths.length - 1];
    }

    int LongestDiffSubString2(String str) {
        int[] map = new int[26];
        int loc = 0;
        int max = 0;

        for (int i = 0; i < str.length(); i++) {
            int cr = str.charAt(i) - 'a';
            if (map[cr] != 0) {
                max = Math.max(max, i - loc);
                loc = map[cr];
            }
            map[cr] = i + 1;
        }
        max = Math.max(max, str.length() - loc);
        return max;
    }

    /**
     * [丑数_牛客网]( https://www.nowcoder.com/practice/6aa9e04fc3794f68acf8778237ba065b )
     */
    @Test
    public void No49() {

    }

    public int GetUglyNumber_Solution(int index) {
        if(index<=0) return 0;
        int[] result=new int[index];
        result[0] = 1;
        int p2=0;
        int p3=0;
        int p5=0;
        for(int i=1;i<index;i++){
            result[i] = Math.min(result[p2]*2, Math.min(result[p3]*3, result[p5]*5));
            if(result[i] == result[p2]*2)p2++;//为了防止重复需要三个if都能够走到
            if(result[i] == result[p3]*3)p3++;//为了防止重复需要三个if都能够走到
            if(result[i] == result[p5]*5)p5++;//为了防止重复需要三个if都能够走到
        }
        return result[index-1];
    }
}
