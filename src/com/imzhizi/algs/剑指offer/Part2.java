package com.imzhizi.algs.剑指offer;

import com.imzhizi.algs.TreeNode;
import org.junit.Test;

import java.util.*;

public class Part2 {

    /**
     * 感觉路径的计算非常困难，只能够朴实的层层递归，先全部加进去，然后排序，再记一次，Comparator o2-o1是降序
     */
    @Test
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


    @Test
    // 最大连续子串和
    public void No27() {
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
}