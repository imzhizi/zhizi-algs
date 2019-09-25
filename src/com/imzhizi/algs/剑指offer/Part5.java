package com.imzhizi.algs.剑指offer;

import com.imzhizi.algs.ListNode;
import com.imzhizi.algs.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
                min = map.get(c);
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
     * [两个链表的第一个公共结点_牛客网]( https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46 )
     * todo
     */
    @Test
    public void No52() {

    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        return null;
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
     * [zhizi-algs/A268_MissingNumber.java ]( https://github.com/imzhizi/zhizi-algs/blob/a67c67bd593e94b1b56642b0026da380c2d7cddf/src/com/imzhizi/algs/LeetCode/LeetCode2018/A268_MissingNumber.java )
     * [ 268 Missing Number - leetcode]( https://leetcode.com/problems/missing-number )
     */
    @Test
    public void No53_2() {

    }


    /**
     * [二叉搜索树的第k个结点_牛客网]( https://www.nowcoder.com/practice/ef068f602dde4d28aab2b210e859150a )
     * 先移动到最左下角, 按照中序遍历, 第几个就是第几个
     * 需要递归
     */
    @Test
    public void No54() {

    }

    TreeNode KthNode(TreeNode pRoot, int k)
    {
        List<TreeNode> list =new ArrayList<>();
        mid(pRoot,list);
        if(k==0||k>list.size()) return null;
        return list.get(k-1);
    }

    void mid(TreeNode pRoot, List<TreeNode> list)
    {
        if(pRoot==null){
            return;
        }

        if(pRoot.left!=null){
            mid(pRoot.left,list);
        }

        list.add(pRoot);

        if(pRoot.right!=null){
            mid(pRoot.right,list);
        }
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
     * [和为S的两个数字_牛客网]( https://www.nowcoder.com/practice/390da4f7a00f44bea7c2f3d19491311b )
     */
    @Test
    public void No57_1() {


    }

    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        int head=0;
        int tail=array.length-1;
        ArrayList<Integer> result=new ArrayList<Integer>();

        while(head<tail){
            if(array[head]+array[tail]==sum){
                result.add(array[head]);
                result.add(array[tail]);
                break;
            }else if(array[head]+array[tail]>sum){
                tail--;
            }else{
                head++;
            }
        }

        return result;
    }

    /**
     * todo [和为S的连续正数序列_牛客网]( https://www.nowcoder.com/practice/c451a3fd84b64cb19485dad758a55ebe )
     */
    @Test
    public void No57_2() {

    }

    /**
     * [翻转单词顺序列_牛客网]( https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3 )
     * 运行时间：16ms
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
     * [滑动窗口的最大值_牛客网]( https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788 )
     */
    @Test
    public void No59() {

    }

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int loc = -1;
        for (int i = size - 1; i >= 0 && i < num.length; i++) {
            if (loc == i - size) {
                max = Integer.MIN_VALUE;
                for (int j = i - size + 1; j <= i; j++) {
                    if (max < num[j]) {
                        max = num[j];
                        loc = j;
                    }
                }
            } else if (max < num[i]) {
                max = num[i];
                loc = i;
            }
            list.add(max);
        }

        return list;
    }
}
