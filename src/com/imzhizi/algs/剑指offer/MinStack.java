package com.imzhizi.algs.剑指offer;

import java.util.PriorityQueue;

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
