package com.imzhizi.algs.leetcode.剑指;

import com.imzhizi.algs.Common.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * created by zhizi
 * on 3/12/20 17:38
 */
public class TOP60 {
    /**
     * [面试题53 - I. 在排序数组中查找数字 I - 力扣（LeetCode）](https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/ )
     * 只要是查找，就要毫不犹豫开始搞二分查找
     * 二分查找，考虑 mid 在内部生成，加入 head、tail的 收缩可以有效防止死循环
     */
    @Test
    public void No53I() {

    }

    public int search(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            }
            if (num > target) {
                break;
            }
        }
        return count;
    }

    public int search2(int[] nums, int target) {
        int count = 0;
        int head = 0;
        int tail = nums.length - 1;

        while (head > -1 && tail < nums.length && head <= tail) {
            int mid = (head + tail) / 2;
            if (nums[mid] < target) {
                head = mid + 1;
            } else if (nums[mid] > target) {
                tail = mid - 1;
            } else {
                count++;
                head = mid - 1;
                tail = mid + 1;
                while (head > -1 && nums[head] == target) {
                    count++;
                    head--;
                }

                while (tail < nums.length && nums[tail] == target) {
                    count++;
                    tail++;
                }
                break;
            }
        }
        return count;
    }

    /**
     * [面试题53 - II. 0～n-1中缺失的数字 - 力扣（LeetCode）](https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/ )
     */
    @Test
    public void No53II() {

    }

    public int missingNumber(int[] nums) {
        int sum = (1 + nums.length) * nums.length / 2;
        for (int i : nums) {
            sum -= i;
        }
        return sum;
    }

    // 100%
    // 双头指针，加快一倍
    public int missingNumber2(int[] nums) {
        int head = 0, tail = nums.length - 1;

        while (head < tail && nums[head] == head && nums[tail] == tail + 1) {
            head++;
            tail--;
        }

        if (head == tail) {
            return nums.length - nums[head];
        } else {
            return head == nums[head] ? tail + 1 : head;
        }
    }

    // 100%
    // 更具有推广意义的二分查找
    public int missingNumber3(int[] nums) {
        int head = 0, tail = nums.length - 1;

        while (head <= tail) {
            int mid = (head + tail) >> 1;
            if (nums[mid] == mid) {
                head = mid + 1;
            } else if (nums[mid] > mid) {
                tail = mid - 1;
            }
        }

        return head;
    }

    /**
     * [面试题54. 二叉搜索树的第k大节点 - 力扣（LeetCode）](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/ )
     * 可以通过全局变量来避免 ArrayList 的使用，但不想使用全局变量破坏算法的封闭性
     */
    @Test
    public void No54() {

    }

    // 左中右
    // 必须要等遍历结束了
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        ldr(root, list);
        return list.get(list.size() - k);
    }

    public void ldr(TreeNode root, List<Integer> list) {
        if (root == null) return;
        ldr(root.left, list);
        list.add(root.val);
        ldr(root.right, list);
    }

    // 右中左
    // 通过 list 的 size 提前结束递归
    // 但时间复杂度上表现不明显
    public int kthLargest2(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        rdl(root, list, k);
        return list.get(k - 1);
    }

    public void rdl(TreeNode root, List<Integer> list, int k) {
        if (root == null) {
            return;
        }
        if (list.size() >= k) {
            return;
        }
        rdl(root.right, list, k);
        list.add(root.val);
        rdl(root.left, list, k);
    }


    /**
     * [面试题55 - I. 二叉树的深度 - 力扣（LeetCode）](https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/ )
     */
    @Test
    public void No55I() {

    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * [面试题55 - II. 平衡二叉树 - 力扣（LeetCode）](https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/ )
     */
    @Test
    public void No55II() {

    }

    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    public int depth(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return 1;

        int left = depth(root.left);
        int right = depth(root.right);

        if (left == -1 || right == -1) return -1;

        if (Math.abs(left - right) < 2) return Math.max(left, right) + 1;
        else return -1;
    }

    /**
     * [面试题56 - I. 数组中数字出现的次数 - 力扣（LeetCode）](https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/ )
     * xor中不为1的每一位, 都说明在这一位上a, b不同
     * 因此任选xor的不为0的一位, 都可以用来对a、b划分，而相同的数字在这一位上也相同，所以会在异或中消失
     * 而获取不为0的一位最简单的方法是 xor&-xor, 一种更直接、更蠢的方法是
     * while((xor&1)!=1){
     * flag<<=1;
     * xor>>>=1;
     * }
     */
    @Test
    public void No56I() {

    }

    public int[] singleNumbers(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        int a = 0;
        int b = 0;
        xor &= -xor;
        for (int num : nums) {
            if ((num & xor) == 0) a ^= num;
            else b ^= num;
        }

        return new int[]{a, b};
    }

    /**
     * [面试题56 - II. 数组中数字出现的次数 II - 力扣（LeetCode）](https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/ )
     * 来数一数每一位上有多少个 1, 如果 1 的数量是 3 的整数倍, 则说明目标数字在该位做贡献了
     * 如何把散碎的位搜集起来，要用「或」运算
     */
    @Test
    public void No56II() {

    }

    public int singleNumber(int[] nums) {
        int result = 0;
        int bit = 1;

        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int num : nums) {
                if ((num & bit) != 0) count++;
            }
            if (count % 3 != 0) result |= bit;
            bit <<= 1;
        }

        return result;
    }

    /**
     * [面试题57. 和为s的两个数字 - 力扣（LeetCode）](https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/ )
     */
    @Test
    public void No57I() {

    }

    // 双头指针
    public int[] twoSum(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head < tail) {
            int sum = nums[head] + nums[tail];
            if (sum == target) return new int[]{nums[head], nums[tail]};
            else if (sum > target) tail--;
            else head++;
        }
        return null;
    }

    /**
     * [面试题57 - II. 和为s的连续正数序列 - 力扣（LeetCode）](https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/ )
     */
    @Test
    public void No57II() {

    }


    // 45%
    // 滑动窗口法
    public int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();
        int l = 1;
        int r = 2;
        int sum = l + r;
        while (l <= target / 2) {
            if (sum < target) {
                r++;
                sum += r;
            } else if (sum == target) {
                int[] data = new int[r - l + 1];
                for (int i = 0; i <= r - l; i++) {
                    data[i] = l + i;
                }
                result.add(data);
                sum -= l;
                l++;
            } else {
                sum -= l;
                l++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    // 94%
    // 削尖法
    public int[][] findContinuousSequence2(int target) {
        List<int[]> result = new ArrayList<>();
        int i = 1;

        while (target > 0) {
            target -= i++;
            if (target > 0 && target % i == 0) {
                int[] array = new int[i];
                for (int k = target / i, j = 0; k < target / i + i; k++, j++) {
                    array[j] = k;
                }
                result.add(array);
            }
        }
        Collections.reverse(result);
        return result.toArray(new int[result.size()][]);
    }

    // 等差数列
    // 公式为 target = na + n(n-1)/2 a 为首项
    // n 代表项数，a = (target-n(n-1)/2)/n
    // 遍历 n 即可找到可行的结果
    public int[][] findContinuousSequence3(int target) {
        List<int[]> result = new ArrayList<>();

        double a = 1.0;
        for (int n = 2; n < target / 2; n++) {
            a = (target - n * (n - 1) / 2.0) / n;
            if (a <= 0) break;
            if (a % 1.0 == 0) {
                int[] data = new int[n];
                for (int i = 0; i < n; i++) data[i] = (int) a + i;
                result.add(data);
            }
        }

        int size = result.size();
        int[][] res = new int[size][];
        for (int i = 0; i < size; i++) {
            res[i] = result.get(size - 1 - i);
        }
        return res;
    }

    /**
     * [面试题58 - I. 翻转单词顺序 - 力扣（LeetCode）](https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/ )
     */
    @Test
    public void No58I() {

    }

    // 100%
    // split 效率还是相当高的
    public String reverseWords(String s) {
        String[] a = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = a.length - 1; i >= 0; i--) {
            if (!a[i].equals("")) {
                sb.append(a[i]);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    // 60%
    public String reverseWords2(String s) {
        if (s.length() == 0) return s;

        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = 0;

        while (end < s.length()) {
            while (end < s.length() && s.charAt(end) == ' ') {
                end++;
            }
            start = end;
            while (end < s.length() && s.charAt(end) != ' ') {
                end++;
            }
            sb.insert(0, s.substring(start, end));
            sb.insert(0, " ");
            while (end < s.length() && s.charAt(end) == ' ') {
                end++;
            }
        }

        return sb.substring(1);
    }

    /**
     * [面试题58 - II. 左旋转字符串 - 力扣（LeetCode）](https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/ )
     */
    @Test
    public void No58II() {

    }

    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    /**
     * [面试题59 - I. 滑动窗口的最大值 - 力扣（LeetCode）](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/ )
     * todo 队列？
     */
    @Test
    public void No59I() {

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        Deque<Integer> queue = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peekLast() < nums[i]) {
                queue.pollLast();
            }
            queue.addLast(nums[i]);
            if (i >= k && queue.peekFirst() == nums[i - k]) {
                queue.pollFirst();
            }
            if (i >= k - 1) {
                result[i + 1 - k] = queue.peekFirst();
            }
        }

        return result;
    }

    /**
     * [面试题59 - II. 队列的最大值 - 力扣（LeetCode）](https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/ )
     */
    @Test
    public void No59II() {

    }

    static class MaxQueue {
        LinkedList<Integer> single;
        LinkedList<Integer> queue;

        public MaxQueue() {
            single = new LinkedList<>();
            queue = new LinkedList<>();
        }

        public int max_value() {
            if (single.isEmpty()) return -1;
            return single.peekFirst();
        }

        public void push_back(int value) {
            queue.addLast(value);
            while (!single.isEmpty() && value > single.peekLast()) {
                single.pollLast();
            }
            single.addLast(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) return -1;
            if (single.peekFirst().equals(queue.peekFirst())) single.pollFirst();
            return queue.pollFirst();
        }
    }
}
