package com.imzhizi.algs.LeetCode;

/**
 * 225. Implement Stack using Queues
 * Implement the following operations of a stack using queues.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 *
 * created by zhizi on 11/13/18
 */
class MyStack {
    /**
     * 题目分析：
     * 栈是一种非常基本的数据结构，特点就是先进后出之类的
     * 用一个数组加一个指针可以轻松模拟栈的基本操作
     *
     * 运行时长：
     *
     * 总结：
     * 基础
     */
    int[] stack;
    int top=-1;

    /** Initialize your data structure here. */
    public MyStack() {
        stack = new int[100];
    }

    /** Push element x onto stack. */
    public void push(int x) {
        stack[++top] = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return stack[top--];
    }

    /** Get the top element. */
    public int top() {
        return stack[top];
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        if (top==-1) return true;
        else return false;
    }


    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());   // returns 2
        System.out.println(stack.pop());   // returns 2
        System.out.println(stack.empty()); // returns false
    }
}
