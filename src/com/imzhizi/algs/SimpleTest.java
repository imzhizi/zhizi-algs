package com.imzhizi.algs;


import org.junit.Test;

import java.util.PriorityQueue;

public class SimpleTest {

    @Test
    public void codeTest1() {
        System.out.println(0.1 * 6 == 0.6);
        System.out.println(-1 >> 1 == Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE - Integer.MIN_VALUE == -1);
        System.out.println("http://localhost:8090/");
    }


    @Test
    public void codeTest2() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(2);
        pq.add(7);
        pq.add(5);
        pq.add(1);
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
    }
}
