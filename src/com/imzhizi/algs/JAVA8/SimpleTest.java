package com.imzhizi.algs.JAVA8;


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

    // 01 背包问题
    @Test
    public void test01Bag() {
        int v = 6;
        int[] volumes = new int[]{3, 2, 4};
        int[] values = new int[]{5, 4, 2};
        System.out.println(calculate(v, volumes, values, 0));
    }

    public int calculate(int v, int[] volumes, int[] values, int index) {
        if (index == volumes.length) {
            return 0;
        }

        int valueA = 0;
        int valueB = 0;

        if (volumes[index] < v) {
            valueA = calculate(v - volumes[index], volumes, values, index + 1) + values[index];
        } else {
            valueB = calculate(v, volumes, values, index + 1);
        }

        return Math.max(valueA, valueB);
    }
}
