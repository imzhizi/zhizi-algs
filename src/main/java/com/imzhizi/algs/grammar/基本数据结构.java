package com.imzhizi.algs.grammar;

import org.junit.Test;

import java.util.*;

/**
 * created by zhizi
 * on 3/7/20 16:02
 */
public class 基本数据结构 {
    @Test
    public void list() {
        // 数组
        // 朴实的数组
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(4);
        System.out.println(list.size());
    }


    @Test
    public void queue() {
        // 队列
        // 朴实的队列
        Queue<Integer> queue = new LinkedList<>();
        queue.add(3);
        queue.add(1);
        queue.add(4);
        System.out.println("list peek " + queue.peek());

        // ArrayDeque
        // ArrayDeque 功能多，队列、双头队列、栈，都可以
        // 作为双头队列使用，表现好于 LinkedList
        // 作为栈来使用，表现好于 Stack
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(3);
        deque.add(1);
        deque.add(4);
        System.out.println("deque first " + deque.getFirst());
        System.out.println("deque last " + deque.getLast());
    }

    @Test
    public void stack() {
        // 栈
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        System.out.println("stack peek " + stack.peek());
    }

    @Test
    public void heap() {
        // 基于优先队列实现大根堆、小根堆
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
        smallHeap.offer(3);
        smallHeap.offer(1);
        smallHeap.offer(4);

        PriorityQueue<Integer> bigHead = new PriorityQueue<>((o1, o2) -> o2 - o1);
        bigHead.offer(3);
        bigHead.offer(1);
        bigHead.offer(4);

        System.out.println("small-head peek " + smallHeap.peek());
        System.out.println("big-head peek " + bigHead.peek());
    }

    @Test
    public void set() {

    }

    @Test
    public void map() {

    }
}
