package com.imzhizi.algs.剑指NowCoder;

import com.imzhizi.algs.base.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Part3 {
    /**
     * [包含min函数的栈_牛客网]( https://www.nowcoder.com/practice/4c776177d2c04c2494f2555c9fcc1e49 )
     * [ MinStack ]( https://github.com/imzhizi/zhizi-algs/blob/master/src/com/imzhizi/algs/%E5%89%91%E6%8C%87offer/MinStack.java )
     */
    @Test
    public void No30() {
        MinStack minStack = new MinStack();
    }

    class MinStack {
        private int[] stack = new int[100];
        private int index = -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        public void push(int node) {
            stack[++index] = node;
            pq.add(node);
        }

        public void pop() {
            pq.remove(stack[index--]);
        }

        public int top() {
            return stack[index];
        }

        public int min() {
            return pq.peek();
        }
    }

    /**
     * [栈的压入、弹出序列_牛客网]( https://www.nowcoder.com/practice/d77d11405cc7470d82554cb392585106 )
     */
    @Test
    public void No31() {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop = {4, 5, 3, 2, 1};
        System.out.println(IsPopOrder2(push, pop));
    }

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        List<Integer> list = Arrays.stream(pushA).boxed().collect(Collectors.toList());
        int i = 0;
        int j = 0;
        while (list.size() != 0 && i < list.size()) {
            if (list.get(i) == popA[j]) {
                list.remove(i);
                i = i - 2;
                j++;
            }
            i++;
        }
        System.out.println(j);
        if (j != pushA.length) {
            return false;
        } else {
            return true;
        }
    }

    public boolean IsPopOrder2(int[] pushA, int[] popA) {
        int[] stack = new int[pushA.length];
        int index = -1;
        int length = pushA.length;
        int push = 0;
        int pop = 0;

        while (push < length) {
            stack[++index] = pushA[push++];
            while (stack[index] == popA[pop]) {
                pop++;
                index--;
            }
        }

        if (pop == length) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * [从上往下打印二叉树_牛客网]( https://www.nowcoder.com/practice/7fe2212963db4790b57431d9ed259701 )
     */
    @Test
    public void No32_1() {

    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<TreeNode> nodes = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        else nodes.add(root);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.get(0);
            result.add(node.val);
            nodes.remove(0);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }

        return result;
    }

    /**
     * [把二叉树打印成多行_牛客网]( https://www.nowcoder.com/practice/445c44d982d04483b04a54f298796288 )
     */
    @Test
    public void No32_2() {

    }

    public ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        if (pRoot == null) return result;
        else list.add(pRoot);
        while (!list.isEmpty()) {
            ArrayList<Integer> ints = new ArrayList<Integer>();
            int size = list.size();
            for (int i = size - 1; i >= 0; i--) {
                TreeNode node = list.get(0);
                list.remove(0);
                ints.add(node.val);
                if (node.left != null) list.add(node.left);
                if (node.right != null) list.add(node.right);
            }
            result.add(ints);
        }
        return result;
    }

    /**
     * [按之字形顺序打印二叉树_牛客网]( https://www.nowcoder.com/practice/91b69814117f4e8097390d107d2efbe0 )
     */
    @Test
    public void No32_3() {

    }

    public ArrayList<ArrayList<Integer>> Print3(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        if (pRoot == null) return result;
        else list.add(pRoot);
        int layer = 0;
        while (!list.isEmpty()) {
            ArrayList<Integer> ints = new ArrayList<Integer>();
            int size = list.size();
            for (int i = size - 1; i >= 0; i--) {
                TreeNode node = list.get(i);
                list.remove(i);
                ints.add(node.val);
                if (layer % 2 == 0) {
                    if (node.left != null) list.add(node.left);
                    if (node.right != null) list.add(node.right);
                } else {
                    if (node.right != null) list.add(node.right);
                    if (node.left != null) list.add(node.left);
                }
            }
            result.add(ints);
            layer++;
        }
        return result;
    }

    /**
     * [二叉搜索树的后序遍历序列_牛客网]( https://www.nowcoder.com/practice/a861533d45854474ac791d90e447bafd )
     */
    @Test
    public void No33() {
        System.out.println(VerifySquenceOfBST(new int[]{3, 5, 4, 7, 9, 8, 6}));
        System.out.println(VerifySquenceOfBST(new int[]{1, 2, 3, 4, 5}));
        System.out.println(VerifySquenceOfBST(new int[]{7, 4, 6, 5}));
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        return Verify(sequence, 0, sequence.length - 1);
    }

    public boolean Verify(int[] sequence, int head, int tail) {
        if (head >= tail) return true;

        int root = sequence[tail];
        int i = 0;
        // find root
        for (i = head; i <= tail; i++) {
            if (sequence[i] < root) {
                break;
            }
        }

        for (int j = i + 1; j < tail; j++) {
            if (sequence[j] > root) return false;
        }

        return Verify(sequence, 0, i - 1) && Verify(sequence, i, tail - 1);
    }

    /**
     * [二叉树中和为某一值的路径_牛客网]( https://www.nowcoder.com/practice/b736e784e3e34731af99065031301bca )
     * 二叉树中和为某一值的路径
     * 感觉路径的计算非常困难，只能够朴实的层层递归，先全部加进去，然后排序，再记一次，Comparator o2-o1是降序
     */
    @Test
    public void No34() {
        TreeNode root = new TreeNode(2);
        TreeNode l = new TreeNode(3);
        TreeNode r = new TreeNode(5);
        TreeNode ll = new TreeNode(2);
        l.left = ll;
        root.left = l;
        root.right = r;
        System.out.println(root);
        System.out.println(FindPath(root, 7));
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        CalPath(paths, path, root, target);

        paths.sort((o1, o2) -> o2.size() - o1.size());

        return paths;
    }

    public void CalPath(List<ArrayList<Integer>> paths, ArrayList<Integer> path, TreeNode root, int target) {
        if (root == null) return;
        int val = root.val;
        if (root.right == null && root.left == null) {
            if (val == target) {
                path.add(val);
                paths.add(path);
            }
        } else {
            if (val < target) {
                path.add(val);
                ArrayList<Integer> right = (ArrayList<Integer>) path.clone();
                if (root.left != null)
                    CalPath(paths, path, root.left, target - val);
                if (root.right != null)
                    CalPath(paths, right, root.right, target - val);
            }
        }
    }

    /**
     * [复杂链表的复制_牛客网]( https://www.nowcoder.com/practice/f836b2c43afc4b35ad6adc41ec941dba )
     */
    @Test
    public void No35() {

    }

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;

        RandomListNode node = new RandomListNode(pHead.label);
        HashMap<Integer, RandomListNode> map = new HashMap<>();
        map.put(pHead.label, node);


        RandomListNode head = node;
        RandomListNode p = pHead;
        while (pHead.next != null) {
            head.next = new RandomListNode(pHead.next.label);
            map.put(pHead.next.label, head.next);
            pHead = pHead.next;
            head = head.next;
        }

        head = node;

        while (p != null) {
            if (p.random != null)
                head.random = map.get(p.random.label);
            head = head.next;
            p = p.next;
        }
        return node;
    }

    class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * [二叉搜索树与双向链表_牛客网]( https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5 )
     */
    @Test
    public void No36() {

    }

    public TreeNode Convert(TreeNode pRootOfTree) {
        TreeNode left = Convert(pRootOfTree, true);
        if (left == null) return null;
        while (left.left != null) {
            left = left.left;
        }
        return left;
    }

    public TreeNode Convert(TreeNode pRootOfTree, boolean flag) {
        if (pRootOfTree == null) return null;
        if (pRootOfTree.left == null && pRootOfTree.right == null) {
            return pRootOfTree;
        }
        TreeNode left = Convert(pRootOfTree.left, true);
        pRootOfTree.left = left;
        TreeNode right = Convert(pRootOfTree.right, false);
        pRootOfTree.right = right;
        if (left != null) left.right = pRootOfTree;
        if (right != null) right.left = pRootOfTree;
        if (flag) return right == null ? pRootOfTree : right;
        else return left == null ? pRootOfTree : left;
    }

    /**
     * [序列化二叉树_牛客网]( https://www.nowcoder.com/practice/cf7e25aa97c04cc1a68c8f040e71fb84 )
     */
    @Test
    public void No37() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.right = new TreeNode(4);
        node.right.left = new TreeNode(5);
        node.right.right = new TreeNode(6);

        String str = Serialize(node);
        System.out.println(str);
        TreeNode root = Deserialize(str);
        System.out.println(root);

    }

    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        if (root != null) sb.append(root.val).append("!");
        else sb.append("#!");
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.get(0);
                list.remove(0);
                if (node == null) break;

                if (node.left != null) {
                    list.add(node.left);
                    sb.append(node.left.val).append("!");
                } else {
                    sb.append("#!");
                }
                if (node.right != null) {
                    list.add(node.right);
                    sb.append(node.right.val).append("!");
                } else {
                    sb.append("#!");
                }
            }
        }

        return sb.toString();
    }

    TreeNode Deserialize(String str) {
        String[] strs = str.split("!");
        TreeNode[] list = new TreeNode[strs.length];
        for (int i = 0; i < list.length; i++) {
            if (strs[i].equals("#")) list[i] = null;
            else list[i] = new TreeNode(Integer.parseInt(strs[i]));
        }

        int pa = 0;
        int child = 1;
        while (child < list.length) {
            if (list[pa] != null) {
                list[pa].left = list[child++];
                if (child < list.length)
                    list[pa].right = list[child++];
            }
            pa++;
        }

        return list[0];
    }

    /**
     * [字符串的排列_牛客网]( https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7 )
     */
    @Test
    public void No38() {

    }

    public ArrayList<String> Permutation(String str) {
        List<String> result = new ArrayList<>();
        if (str.length() == 0)
            return (ArrayList) result;
        find(str.toCharArray(), 0, result);
        return (ArrayList) result;
    }

    public void find(char[] str, int loc, List<String> result) {
        if (loc == str.length) {
            String s = String.valueOf(str);
            if (!result.contains(s)) result.add(s);
            return;
        }

        char[] newStr = str.clone();
        find(newStr, loc + 1, result);

        for (int i = loc + 1; i < str.length; i++) {
            char temp = str[loc];
            str[loc] = str[i];
            str[i] = temp;
            char[] copy = str.clone();
            find(copy, loc + 1, result);
        }
    }

    /**
     * [数组中出现次数超过一半的数字_牛客网]( https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163 )
     */
    @Test
    public void No39() {

    }

    public int MoreThanHalfNum_Solution(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
            if (map.get(array[i]) > length / 2) {
                return array[i];
            }
        }
        return 0;
    }

}