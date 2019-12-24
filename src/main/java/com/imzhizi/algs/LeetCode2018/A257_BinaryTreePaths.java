package com.imzhizi.algs.LeetCode2018;

import com.imzhizi.algs.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 *
 * created by zhizi on 11/21/18
 */
public class A257_BinaryTreePaths {
    /**
     * 题目分析：
     * 递归、递归、递归
     *
     * 运行时长：7ms / 99%
     *
     * 总结：
     *
     */
    static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) return list;
        findLeaf(list, "", root);
        return list;
    }

    static void findLeaf(List<String> list, String path, TreeNode root) {
        if (path.length() == 0)
            path = path + root.val;
        else
            path += "->" + root.val;

        if (root.left == null && root.right == null) {
            list.add(path);
            return;
        }
        if (root.left != null) {
            findLeaf(list, path, root.left);
        }
        if (root.right != null) {
            findLeaf(list, path, root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        List<String> list = binaryTreePaths(t1);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
