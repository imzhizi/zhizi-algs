package com.imzhizi.algs.leetcode_2018;

/**
 * Implement the following operations of a queue using stacks.
 *
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * created by zhizi on 11/17/18
 */
class MyQueue {
    /**
     * 题目分析：
     * 栈、队列是什么大家都很清楚，上一道题刚让用队列实现了栈，这立刻又让用栈来实现队列
     * 用栈实现队列是个苦差事，我也不明白为什么这样，但既然这样要求了，我就用上一题的栈实现了队列
     *
     * 运行时长：99% / 54ms
     *
     * 总结：
     * 本来我的运行时很长，然后我就把栈的初始化长度调到了20，直接就上了，反正就非常基础，没什么意思的题目
     */
    MyStack queue;
    int front = -1;
    int end = -1;


    /** Initialize your data structure here.*/
    public MyQueue() {
        queue = new MyStack();
    }

    /** Push element x to the back of queue.*/
    public void push(int x) {
        queue.push(x);
        this.end++;
    }

    /** Removes the element from in front of queue and returns that element.*/
    public int pop() {
        int l = end - front;

        int[] tmp = new int[l - 1];
        for (int i = 0; i < l - 1; i++) {
            tmp[i] = queue.pop();
        }
        int pop = queue.pop();
        for (int i = 0; i < l - 1; i++) {
            queue.push(tmp[l-i-2]);
        }
        end--;
        return pop;
    }

    /** Get the front element.*/
    public int peek() {
        int l = end - front;

        int[] tmp = new int[l - 1];
        for (int i = 0; i < l - 1; i++) {
            tmp[i] = queue.pop();
        }
        int pop = queue.top();
        for (int i = 0; i < l - 1; i++) {
            queue.push(tmp[l-i-2]);
        }
        return pop;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if (end == -1)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        System.out.println(obj.pop());
        obj.push(2);
        System.out.println(obj.peek());
        System.out.println(obj.empty());

    }
}
