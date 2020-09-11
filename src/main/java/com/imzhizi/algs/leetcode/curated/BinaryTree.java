package com.imzhizi.algs.leetcode.curated;

import com.imzhizi.algs.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by zhizi
 * on 9/9/20 17:12
 * 主要基于：https://leetcode-cn.com/leetbook/detail/data-structure-binary-tree/
 */
public class BinaryTree {

    /**
     * 二叉树重建
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        return null;
    }


    /**
     * 填充每个节点的下一个右侧节点指针
     */
    static class Node {
        int val;
        Node left;
        Node right;
        Node next;
    }

    public Node buildConnection(Node root) {
        if (root == null) {
            return null;
        }

        List<Node> list = new ArrayList<>();
        list.add(root);
        int index = 0;
        while (index < list.size()) {
            int length = list.size();
            for (int i = index; i < length; i++) {
                Node node = list.get(i);
                if (node.left != null) {
                    list.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right);
                }

                if (i > index) {
                    list.get(i - 1).next = node;
                }
            }
            index = length;
        }
        return root;
    }

    /**
     * 如果给定的树是完美二叉树呢？
     */
    public Node connect(Node root) {

        return null;
    }

    /**
     * 二叉树的最近公共祖先
     * 如何思考，之前总想着在一层中办所有的事情，但其实递归不是这样的
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }

    @Test
    public void tree() {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);
        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n3.left = n5;
        n5.right = n1;
        String str = serialize(root);
        System.out.println(str);
        System.out.println(deserialize(str));
    }

    /**
     * 二叉树的序列化与反序列化
     * 最简单的思路，层次遍历，关键在于空白结点的处理
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        queue.offer(root);
        sb.append(root.val).append(",");
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                sb.append(node.left.val);
            }
            sb.append(",");
            if (node.right != null) {
                queue.offer(node.right);
                sb.append(node.right.val);
            }
            sb.append(",");
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = null;
        if (nodes[0].isEmpty()) {
            return null;
        } else {
            root = new TreeNode(Integer.parseInt(nodes[0]));
        }

        queue.offer(root);
        int create = 1;// 同于创建子节点
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (create < nodes.length && !nodes[create].isEmpty()) {
                node.left = new TreeNode(Integer.parseInt(nodes[create]));
                queue.offer(node.left);
            }
            create++;
            if (create < nodes.length && !nodes[create].isEmpty()) {
                node.right = new TreeNode(Integer.parseInt(nodes[create]));
                queue.offer(node.right);
            }
            create++;
        }

        return root;
    }

}
