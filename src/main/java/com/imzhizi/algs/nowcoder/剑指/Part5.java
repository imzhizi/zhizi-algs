package com.imzhizi.algs.nowcoder.剑指;

import com.imzhizi.algs.common.ListNode;
import com.imzhizi.algs.common.TreeNode;
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
     */
    @Test
    public void No52() {

    }

    // 链表的公共节点, 首先是最朴实的便利法, 时间复杂度为 O(n^2)
    public ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2) {
        while (pHead1 != null) {
            ListNode node = pHead2;
            while (node != null) {
                if (pHead1 == node) {
                    return pHead1;
                } else {
                    node = node.next;
                }
            }
            pHead1 = pHead1.next;
        }
        return null;
    }

// 一种可能的方法是把两个链表的数据都复制到list中, 然后反向遍历, 遇到的第一个不同的next即为公共节点, 时间复杂度为O(2.5n)
// 还有一种方法, 首先把一个链表哈希化, 然后遍历另一个链表, 测试hash中是否包含此链表中的节点

    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        HashSet<ListNode> set = new HashSet<>();

        while (pHead1 != null) {
            set.add(pHead1);
            pHead1 = pHead1.next;
        }

        while (pHead2 != null) {
            if (set.contains(pHead2)) {
                return pHead2;
            } else {
                pHead2 = pHead2.next;
            }
        }

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

    TreeNode KthNode(TreeNode pRoot, int k) {
        List<TreeNode> list = new ArrayList<>();
        mid(pRoot, list);
        if (k == 0 || k > list.size()) return null;
        return list.get(k - 1);
    }

    void mid(TreeNode pRoot, List<TreeNode> list) {
        if (pRoot == null) {
            return;
        }

        if (pRoot.left != null) {
            mid(pRoot.left, list);
        }

        list.add(pRoot);

        if (pRoot.right != null) {
            mid(pRoot.right, list);
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

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        int head = 0;
        int tail = array.length - 1;
        ArrayList<Integer> result = new ArrayList<Integer>();

        while (head < tail) {
            if (array[head] + array[tail] == sum) {
                result.add(array[head]);
                result.add(array[tail]);
                break;
            } else if (array[head] + array[tail] > sum) {
                tail--;
            } else {
                head++;
            }
        }

        return result;
    }

    /**
     * [和为S的连续正数序列_牛客网]( https://www.nowcoder.com/practice/c451a3fd84b64cb19485dad758a55ebe )
     */
    @Test
    public void No57_2() {
        ArrayList<ArrayList<Integer>> result = FindContinuousSequence(100);
        result.forEach(System.out::println);
    }

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        // 首先求出来所有质因数
        for (int i = sum / 2; i > 2; i--) {
            if (isPrime(i) && sum % i == 0) {
                ArrayList<Integer> list = generateFromPrime(sum / i, i);
                if (!list.isEmpty()) result.add(list);
            } else if (sum / (i * 1.0) % 1.0 == 0.5) {
                ArrayList<Integer> list = generateFromOdd(sum / i, i);
                if (!list.isEmpty()) result.add(list);
            }
        }

        if (sum % 2 != 0) {
            ArrayList<Integer> list = generateFromOdd(sum / 2, 2);
            if (!list.isEmpty()) result.add(list);
        }

        return result;
    }

    public boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> generateFromPrime(int mid, int n) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = mid - n / 2; i > 0 && i <= mid + n / 2; i++) {
            list.add(i);
        }
        return list;
    }

    public ArrayList<Integer> generateFromOdd(int mid, int n) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = mid - n / 2 + 1; i > 0 && i <= mid + n / 2; i++) {
            list.add(i);
        }
        return list;
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
     * todo
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
