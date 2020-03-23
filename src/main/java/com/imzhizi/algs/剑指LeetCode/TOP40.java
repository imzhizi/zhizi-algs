package com.imzhizi.algs.剑指LeetCode;

import com.imzhizi.algs.base.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * created by zhizi
 * on 3/11/20 17:19
 */
public class TOP40 {
    /**
     *
     */
    @Test
    public void No31() {

    }

    /**
     *
     */
    @Test
    public void No32() {

    }

    // 7% 超慢……
    public int[] levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root != null) {
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.addLast(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.pollFirst();
                result.add(node.val);
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        // return result.stream().mapToInt(Integer::valueOf).toArray();
        // 这样写超级慢，我也不知道为啥
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++)
            res[i] = result.get(i);
        return res;
    }

    /**
     * [面试题32 - II. 从上到下打印二叉树 II - 力扣（LeetCode）](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/ )
     */
    @Test
    public void No32II() {

    }

    public List<List<Integer>> levelOrderII(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<TreeNode> stack = new ArrayList<>();
        stack.add(root);
        int index = 0;
        while (index != stack.size()) {
            int size = stack.size();
            List<Integer> list = new ArrayList<>();
            for (; index < size; index++) {
                TreeNode temp = stack.get(index);
                list.add(temp.val);
                if (temp.left != null) stack.add(temp.left);
                if (temp.right != null) stack.add(temp.right);
            }
            result.add(list);
        }
        return result;
    }

    /**
     * [面试题32 - III. 从上到下打印二叉树 III - 力扣（LeetCode）](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/ )
     */
    @Test
    public void No32III() {

    }

    public List<List<Integer>> levelOrderIII(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        List<Integer> r = new ArrayList<>();
        r.add(root.val);
        result.add(r);

        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        boolean dire = false;

        while (!list.isEmpty()) {
            List<TreeNode> iter = new ArrayList<>();
            List<Integer> values = new ArrayList<>();
            for (int i = list.size() - 1; i >= 0; i--) {
                TreeNode node = list.get(i);
                if (dire) {
                    if (node.left != null) {
                        iter.add(node.left);
                        values.add(node.left.val);
                    }
                    if (node.right != null) {
                        iter.add(node.right);
                        values.add(node.right.val);
                    }
                } else {
                    if (node.right != null) {
                        iter.add(node.right);
                        values.add(node.right.val);
                    }
                    if (node.left != null) {
                        iter.add(node.left);
                        values.add(node.left.val);
                    }
                }
            }
            dire = !dire;
            list = iter;
            if (!values.isEmpty()) result.add(values);
        }
        return result;
    }


    /**
     *
     */
    @Test
    public void No33() {

    }

    /**
     * [面试题34. 二叉树中和为某一值的路径 - 力扣（LeetCode）](https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/ )
     */
    @Test
    public void No34() {

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum(root, result, new ArrayList<>(), sum);
        return result;
    }

    public void pathSum(TreeNode root, List<List<Integer>> result, List<Integer> path, int sum) {
        if (root == null) return;
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right == null) {
            path.add(root.val);
            result.add(path);
        } else {
            path.add(root.val);
            List<Integer> list = new ArrayList<>(path);
            pathSum(root.left, result, path, sum);
            pathSum(root.right, result, list, sum);
        }
    }

    /**
     * [面试题35. 复杂链表的复制 - 力扣（LeetCode）](https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/ )
     */
    @Test
    public void No35() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.next = n2;
        n1.random = n2;
        n2.next = n3;
        n2.random = n2;
        n3.next = n4;
        System.out.println(copyRandomList5(n1));
    }

    // dfs 法，容易理解，代码量也小
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(head, map);
    }

    public Node dfs(Node head, Map<Node, Node> map) {
        if (head == null) return null;
        if (map.containsKey(head)) {
            return map.get(head);
        }
        Node node = new Node(head.val);
        map.put(head, node);
        node.next = dfs(head.next, map);
        node.random = dfs(head.random, map);
        return node;
    }

    // 标准的迭代法
    // 思路在于建立新旧之间的映射关系
    public Node copyRandomList2(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node res = new Node(head.val);
        Node node = head;
        map.put(head, res);

        while (node.next != null) {
            Node f = map.get(node);
            Node b = new Node(node.next.val);
            f.next = b;
            map.put(node.next, b);
            node = node.next;
        }

        while (head != null) {
            Node t = map.get(head);
            if (head.random == null) t.random = null;
            else t.random = map.get(head.random);
            head = head.next;
        }
        return res;
    }

    // 愚蠢的迭代法
    public Node copyRandomList3(Node head) {
        if (head == null) return null;

        List<Node> list = new ArrayList<>();
        Node res = new Node(head.val);
        list.add(res);
        Map<Node, Integer> map = new HashMap<>();
        Node node = head.next;
        int index = 0;
        map.put(head, index++);

        while (node != null) {
            map.put(node, index++);
            Node n = new Node(node.val);
            list.get(list.size() - 1).next = n;
            list.add(n);
            node = node.next;
        }
        while (head != null) {
            int i = map.get(head);
            if (head.random == null) list.get(i).random = null;
            else list.get(i).random = list.get(map.get(head.random));
            head = head.next;
        }
        return res;
    }

    // bfs
    public Node copyRandomList4(Node head) {
        if (head == null) return null;
        Deque<Node> deque = new ArrayDeque<>();
        deque.addLast(head);
        Map<Node, Node> map = new HashMap<>();
        Node res = new Node(head.val);
        map.put(head, res);

        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();
            Node clone = map.get(node);

            Node n = null;
            if (node.next != null) {
                if (map.containsKey(node.next)) {
                    n = map.get(node.next);
                } else {
                    n = new Node(node.next.val);
                    deque.addLast(node.next);
                }
            }
            map.put(node.next, n);
            clone.next = n;

            Node r = null;
            if (node.random != null) {
                if (map.containsKey(node.random)) {
                    r = map.get(node.random);
                } else {
                    r = new Node(node.random.val);
                    deque.addLast(node.random);
                }
            }
            map.put(node.random, r);
            clone.random = r;
        }

        return res;
    }

    // 链表就地复制法
    public Node copyRandomList5(Node head) {
        if (head == null) return null;
        Node node = head;

        while (node != null) {
            Node n = new Node(node.val);
            n.next = node.next;
            node.next = n;
            node = node.next.next;
        }

        node = head;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        Node res = head.next;
        Node split = head.next;
        node = head;
        while (node != null) {
            node.next = node.next.next;
            if (node.next != null) split.next = node.next.next;
            node = node.next;
            split = split.next;
        }

        return res;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            String res = "[" + val + ",";
            if (random == null) res += "null]";
            else res += random.val + "]";
            if (next != null) res += "->" + next;
            return res;
        }
    }

    /**
     * [面试题39. 数组中出现次数超过一半的数字 - 力扣（LeetCode）](https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/ )
     */
    @Test
    public void No39() {

    }

    public int majorityElement(int[] nums) {
        int count = 0;
        int result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == result) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                result = nums[i + 1];
            }
        }
        return result;
    }

    /**
     * [面试题40. 最小的k个数 - 力扣（LeetCode）](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/ )
     * todo 桶排序
     */
    @Test
    public void No40() {

    }

    // 巨慢的实现
    // 时间复杂度为 O(k*n)
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ks = new int[k];
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            ks[i] = arr[i];
        }
        return ks;
    }

    // 桶排序？时间复杂度为 O(n+10001)
    public int[] getLeastNumbers2(int[] arr, int k) {
        int[] res = new int[k];
        if (k == 0) return res;
        if (k == arr.length) return arr;

        //桶思想
        int[] bucket = new int[100001];
        for (int value : arr) {
            bucket[value]++;
        }

        //遍历 bucket,跳过元素0的下标
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            // 主要用于筛选重复出现的数字
            while (bucket[i] != 0) {
                res[index++] = i;
                bucket[i]--;
                if (index == k) return res;
            }
        }
        return res;
    }
}
