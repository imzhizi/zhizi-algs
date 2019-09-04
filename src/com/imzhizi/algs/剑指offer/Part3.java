package com.imzhizi.algs.剑指offer;

import com.imzhizi.algs.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Part3 {
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
}
