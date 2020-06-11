package com.imzhizi.algs.leetcode.daily;

import org.junit.Test;

import java.util.*;

/**
 * created by zhizi
 * on 6/11/20 10:40
 */
public class June {
    /**
     * [739. 每日温度 - 力扣（LeetCode）](https://leetcode-cn.com/problems/daily-temperatures/)
     */
    @Test
    public void Q739() {
//        int[] ints = dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        int[] ints = dailyTemperatures(new int[]{34, 80, 80, 34, 34, 80, 80, 80, 80, 34});
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    //    %83
    public int[] dailyTemperatures(int[] T) {
        ArrayDeque<Integer> queue = new ArrayDeque();
        queue.offerFirst(0);
        for (int i = 1; i < T.length; i++) {
            if (T[i] < T[queue.peekFirst()]) {
                while (!queue.isEmpty() && T[i] > T[queue.peekLast()]) {
                    int k = queue.pollLast();
                    T[k] = i - k;
                }

                queue.offerLast(i);
            } else if (T[i] > T[queue.peekFirst()]) {
                while (!queue.isEmpty() && T[i] > T[queue.peekFirst()]) {
                    int k = queue.pollFirst();
                    T[k] = i - k;
                }

                queue.offerFirst(i);
            } else {
                while (!queue.isEmpty() && T[i] > T[queue.peekLast()]) {
                    int k = queue.pollLast();
                    T[k] = i - k;
                }
                queue.offerFirst(i);
            }
        }

        while (!queue.isEmpty()) {
            T[queue.poll()] = 0;
        }

        return T;
    }
}
