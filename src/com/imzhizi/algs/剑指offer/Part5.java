package com.imzhizi.algs.剑指offer;

import com.imzhizi.algs.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Part5 {
    /**
     * 第一个只出现一次的字符
     */
    @Test
    public void No50_1() {
        System.out.println(FirstNotRepeatingChar("AAbbcdddw"));
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
     * [ 字符流中第一个不重复的字符_牛客网 ]( https://www.nowcoder.com/practice/00de97733b8e4f97a3fb5c680ee10720 )
     */
    @Test
    public void No50_2() {
        Insert('g');
        System.out.println(FirstAppearingOnce());
        Insert('o');
        System.out.println(FirstAppearingOnce());
        Insert('o');
        System.out.println(FirstAppearingOnce());
        Insert('g');
        System.out.println(FirstAppearingOnce());
        Insert('l');
        System.out.println(FirstAppearingOnce());
        Insert('e');
        System.out.println(FirstAppearingOnce());
    }

    private HashMap<Character, Integer> map = new HashMap<>();
    private int index = 1;

    //Insert one char from stringstream
    public void Insert(char ch) {
        if (map.containsKey(ch)) {
            map.remove(ch);
        } else {
            map.put(ch, index++);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        char ch = '#';
        int min = Integer.MAX_VALUE;
        for (char c : map.keySet()) {
            if (map.get(c) < min) {
                min=map.get(c);
                ch = c;
            }
        }
        return ch;
    }

    /**
     * [数组中的逆序对_牛客网]( https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5 )
     */
    @Test
    public void No51() {
        System.out.println(InversePairs(new int[]{1, 2, 3, 4, 5, 6, 7, 0}));
    }

    public int InversePairs(int[] array) {
        int[] nums = new int[array.length];
        int s = (int) InversePairsCore(array, nums, 0, array.length - 1);
        return s;
    }

    public long InversePairsCore(int[] array, int[] nums, int s, int e) {
        if (s + 1 == e || s == e) {
            if (array[s] > array[e]) {
                nums[s] = array[e];
                nums[e] = array[s];
                return 1;
            } else {
                nums[s] = array[s];
                nums[e] = array[e];
                return 0;
            }
        }

        int m = (e + s) / 2;
        long count = InversePairsCore(array, nums, s, m) + InversePairsCore(array, nums, m + 1, e);

        for (int i = m, j = e; i >= s && j > i; i--) {
            if (nums[i] > nums[j]) {
                count = count + j - i;
                int temp = nums[i];
                System.arraycopy(nums, i + 1, nums, i, j - i);
                nums[j] = temp;
            } else {
                i++;
            }
            j--;
        }

        return count % 1000000007;
    }

    /**
     *
     */
    @Test
    public void No52() {

    }

    /**
     * [数字在排序数组中出现的次数_牛客网]( https://www.nowcoder.com/practice/70610bf967994b22bb1c26f9ae901fa2 )
     */
    @Test
    public void No53_1() {
        System.out.println(GetNumberOfK2(new int[]{1, 2, 3, 3, 3, 3}, 3));
    }

    // 特别愚蠢的一种方法
    // 16ms
    public int GetNumberOfK(int[] array, int k) {
        int count = 0;
        for (int i : array) {
            if (i == k) count++;
        }
        return count;
    }

    // 特别愚蠢的一种方法改良版
    // 15ms
    public int GetNumberOfK1(int[] array, int k) {
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) {
                count++;
                flag = true;
            } else if (flag && array[i] != k) {
                break;
            }
        }
        return count;
    }

    // 因为数组是有序的，全部遍历显得过于浪费时间，因此使用二分查找
    // 15ms
    public int GetNumberOfK2(int[] array, int k) {
        int count = 0;

        int s = 0;
        int e = array.length - 1;
        int m = (s + e) / 2;

        while (s != m) {
            if (array[m] < k) {
                s = m;
                m = (s + e) / 2;
            } else if (array[m] > k) {
                e = m;
                m = (s + e) / 2;
            } else {
                while (m > 0 && array[m - 1] == k) {
                    m--;
                }
                break;
            }
        }

        while (m < array.length && array[m++] == k) count++;

        return count;
    }

    /**
     *
     */
    @Test
    public void No54() {

    }

    /**
     * [二叉树的深度_牛客网]( https://www.nowcoder.com/practice/435fb86331474282a3499955f0a41e8b )
     */
    @Test
    public void No55_1() {
        System.out.println(TreeDepth(new TreeNode(1)));
    }

    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }


    /**
     * [平衡二叉树_牛客网]( https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222 )
     */
    @Test
    public void No55_2() {
        System.out.println(IsBalanced_Solution(new TreeNode(1)));
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        return calculateDepth(root) != -1;
    }

    private int calculateDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int left = calculateDepth(root.left);
        int right = calculateDepth(root.right);

        if (left == -1 || right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }

    /**
     * [数组中只出现一次的两个数字_牛客网]( https://www.nowcoder.com/practice/e02fdb54d7524710a7d664d082bb7811 )
     */
    @Test
    public void No56_1() {
        FindNumsAppearOnce(new int[]{1, 2, 2, 3, 3, 4}, new int[1], new int[1]);
    }

    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int multi = 0;
        for (int i : array) {
            multi ^= i;
        }
        int index = divide(multi);

        for (int i : array) {
            if (divide(i) == index) {
                num1[0] ^= i;
            } else {
                num2[0] ^= i;
            }
        }
    }


    private int divide(int num) {
        int index = 0;
        while ((num & 1) != 1) {
            num >>= 1;
            index++;
        }
        return index;
    }

    /**
     *
     */
    @Test
    public void No57() {
    }

    /**
     * 运行时间：16ms
     * [翻转单词顺序列_牛客网]( https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3 )
     * <p>
     * 占用内存：9404k
     */
    @Test
    public void No58_1() {
        System.out.println(ReverseSentence(""));
        Assert.assertEquals(" ", ReverseSentence(" "));
        Assert.assertEquals("", ReverseSentence(""));
        Assert.assertEquals("I am a student.", ReverseSentence("student. a am I"));
    }

    public String ReverseSentence(String str) {
        String[] words = str.split(" ");
        if (words.length < 1) return str;
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i > 0; i--) {
            result.append(words[i]).append(" ");
        }
        result.append(words[0]);
        return result.toString();
    }

    /**
     * [左旋转字符串_牛客网]( https://www.nowcoder.com/practice/12d959b108cb42b1ab72cef4d36af5ec )
     */
    @Test
    public void No58_2() {
        System.out.println(LeftRotateString("abc", 2));
    }

    public String LeftRotateString(String str, int n) {
        if (str.length() == n || str.isEmpty()) {
            return str;
        } else {
            n = n % str.length();
        }

        return str.substring(n) + str.substring(0, n);
    }

    /**
     *
     */
    @Test
    public void No59() {

    }
}
