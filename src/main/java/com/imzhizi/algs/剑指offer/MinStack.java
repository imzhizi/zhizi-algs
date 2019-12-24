package com.imzhizi.algs.剑指offer;

import java.util.PriorityQueue;

/**
 * [ 包含min函数的栈_牛客网 ]( https://github.com/imzhizi/zhizi-algs/blob/master/src/com/imzhizi/algs/%E5%89%91%E6%8C%87offer/Part3.java#L17 )
 */
public class MinStack {
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
