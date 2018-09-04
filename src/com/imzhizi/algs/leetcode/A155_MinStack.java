package com.imzhizi.algs.leetcode;


import java.util.PriorityQueue;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * created by zhizi on 2018/7/8
 */

public class A155_MinStack {
    /**
     * 题目分析：
     * 要求实现一个最小栈，即随时可以给出这个栈的最小值，栈很容易实现，一个 int[] 加一个 top 即可，但最小值就比较复杂了；
     * 没什么很好的办法，只能追求外挂体系，使用的是 PriorityQueue，效果还行
     *
     * 运行时长：70ms / 98%
     *
     * 总结：
     * 结果运行居然超过 98% 的用户，以为会有什么高级做法，想来数据结构的实现的优化都不容易。
     */
    private int[] elements;
    private PriorityQueue<Integer> queue;
    private int top;

    public A155_MinStack() {
        elements = new int[100];
        queue=new PriorityQueue<>();
        top = -1;
    }

    public void push(int x) {
        if(top+1==elements.length){
            int[] copy=new int[elements.length+100];
            System.arraycopy(elements,0,copy,0,elements.length);
            elements=copy;
        }
        elements[++top]=x;
        queue.add(x);
    }

    public void pop() {
        if (elements[top]==queue.peek())
            queue.poll();
        elements[top--]=0;
    }

    public int top() {
        return elements[top];
    }

    public int getMin() {
        return queue.peek();
    }

    public static void main(String[] args) {
        A155_MinStack obj = new A155_MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
    }
}
