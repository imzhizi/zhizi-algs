package com.imzhizi.algs.CodingInterview;

import com.imzhizi.algs.LeetCode.ListNode;
import com.imzhizi.algs.LeetCode.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
    @Test
    // 核心思想把一次性当道最终位置
    public void No5() {
        // 将空格替换为 %20
        char[] input = "we are happy".toCharArray();
        System.out.println(替换空格(input));


        // 数组B合并到A
        int[] numsA = {1, 3, 5, 7, 0, 0, 0};
        int[] numsB = {2, 4, 6};
        for (int i : 数组合并(numsA, numsB))
            System.out.print(i + " ");
    }

    private char[] 替换空格(char[] input) {
        int charCount = 0;

        for (char c : input) {
            if (c == ' ') charCount++;
        }


        char[] output = new char[input.length + 2 * charCount];
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] == ' ') {
                output[i + 2 * charCount] = '0';
                output[i + 2 * charCount - 1] = '2';
                output[i + 2 * charCount - 2] = '%';
                charCount--;
            } else {
                output[i + 2 * charCount] = input[i];
            }
        }

        return output;
    }

    private int[] 数组合并(int[] numsA, int[] numsB) {
        int b = numsB.length - 1;
        int a = numsA.length - numsB.length - 1;
        int l = numsA.length - 1;

        while (l > -1) {
            if (a > -1 && b > -1) {
                if (numsA[a] > numsB[b]) {
                    numsA[l--] = numsA[a--];
                } else {
                    numsA[l--] = numsB[b--];
                }
            } else if (a < 0) {
                numsA[l--] = numsB[b--];
            } else {
                numsA[l--] = numsA[a--];
            }
        }

        return numsA;
    }

    @Test
    public void No6() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        从头到尾打印链表(node1);
        System.out.println();
        从头到尾打印链表2(node1);
    }

    private void 从头到尾打印链表(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }
    }

    private void 从头到尾打印链表2(ListNode head) {
        if (head != null) {
            if (head.next != null)
                从头到尾打印链表2(head.next);
            System.out.print(head.val + " ");
        }
    }

    @Test
    public void No7() {
        // 前序遍历 中序遍历 重建二叉树
        int[] pres = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] mids = new int[]{4, 2, 5, 1, 6, 3, 7};
        System.out.println(重建二叉树(pres, mids));
    }


    private TreeNode 重建二叉树(int[] pres, int[] mids) {
        return rebuilt(pres, 0, pres.length - 1, mids, 0, mids.length - 1);
    }

    private TreeNode rebuilt(int[] pres, int p1, int p2, int[] mids, int m1, int m2) {
        TreeNode head = new TreeNode(pres[p1]);

        if (p1 == p2 && m1 == m2) {
            return head;
        }

        int mroot = 0;
        for (int i = 0; i < mids.length; i++) {
            if (mids[i] == pres[p1]) {
                mroot = i;
            }
        }

        int np2 = 0;
        for (int i = 0; i < pres.length; i++) {
            if (mids[mroot - 1] == pres[i]) {
                np2 = i;
            }
        }

        head.left = rebuilt(pres, p1 + 1, np2, mids, m1, mroot - 1);
        head.right = rebuilt(pres, np2 + 1, p2, mids, mroot + 1, m2);

        return head;
    }


    @Test
    public void No8() {
        int[] pres = new int[]{1, 2, 4, 5, 3, 6, 7};
        reOrderArray(pres);
        System.out.println(Arrays.toString(pres));
    }

    public void reOrderArray(int[] array) {
        // 感觉只能先搞个临时数组存偶数, 然后奇数覆盖式保存
        // 最后把偶数存回去
        int[] even = new int[array.length];
        int[] odd = new int[array.length];
        int e = 0;
        int o = 0;

        for (int i : array) {
            if (i % 2 != 0) {
                odd[o++] = i;
            } else {
                even[e++] = i;
            }
        }

        System.arraycopy(odd, 0, array, 0, o);
        System.arraycopy(even, 0, array, o, e);
    }

    @Test
    public void No9() {

    }

    @Test
    public void No10() {

    }

    @Test
    public void No11() {

    }

    @Test
    public void No12() {

    }

    @Test
    public void No13() {

    }

    @Test
    public void No14() {

    }

    // 在root1种寻找所有val 为 root2 的结点
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;

        if (root1.val == root2.val) {
            return compare(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        }

        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    // 原来子结构不是完全一致, 而是局部一致, 因此root2遍历结束即可
    public boolean compare(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val != root2.val) return false;

        return compare(root1.left, root2.left) && compare(root1.right, root2.right);

    }

    @Test
    public void No15() {

    }

    public void Mirror(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        Mirror(root.left);
        Mirror(root.right);
    }

    @Test
    public void No16() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        ArrayList<Integer> result = printMatrix(matrix);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();

        int h = matrix.length;
        int w = matrix[0].length;

        int i = 0;
        int j = 0;
        int loop = 0;
        int count = h * w;
        while (count > 0) {
            while (count > 0 && j < w - loop) {
                count--;
                result.add(matrix[i][j++]);
            }
            j--;
            i++;
            while (count > 0 && i < h - loop) {
                count--;
                result.add(matrix[i++][j]);
            }
            j--;
            i--;
            while (count > 0 && j >= loop) {
                count--;
                result.add(matrix[i][j--]);
            }

            j++;
            i--;
            loop++;

            while (count > 0 && i >= loop) {
                count--;
                result.add(matrix[i--][j]);
            }

            i++;
            j++;
        }

        return result;
    }

    @Test
    public void No17() {
        MinStack stack = new MinStack();
    }

    @Test
    public void No18() {
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

    @Test
    public void No19() {
        TreeNode root = new TreeNode(0);
        System.out.println(PrintFromTopToBottom(root));
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<TreeNode> nodes = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        nodes.add(root);
        while (nodes.isEmpty()) {
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

    @Test
    public void No20() {
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

    @Test
//  感觉路径的计算非常困难，只能够朴实的层层递归，先全部加进去，然后排序，再记一次，Comparator o2-o1是降序
    public void N021() {
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
                return;
            } else {
                return;
            }
        } else {
            if (val < target) {
                path.add(val);
                ArrayList<Integer> right = (ArrayList<Integer>) path.clone();
                if (root.left == null) return;
                else CalPath(paths, path, root.left, target - val);
                if (root.right == null) return;
                else CalPath(paths, right, root.right, target - val);
            }
            return;
        }
    }

    @Test
//        复杂链表的复制
    public void No22() {
    }

    @Test
//        二叉搜索树与双向链表
    public void No23() {
    }

    @Test
//        字符串的排列
    public void No24() {
        char[] str = new char[10];
        String s = String.valueOf(str);
        char[] newStr = str.clone();
    }

    @Test
// 数组中出现次数超过一半的数字
    public void No25() {
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


    @Test
// 最小的K个数
    public void No26() {

    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {

        return null;

    }
}
