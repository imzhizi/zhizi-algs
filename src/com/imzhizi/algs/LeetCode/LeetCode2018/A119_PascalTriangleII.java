package com.imzhizi.algs.LeetCode.LeetCode2018;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * created by zhizi on 2018/6/17
 */
public class A119_PascalTriangleII {
    /**
     * 题目分析：
     * 跟上一题差别不大，不过因为结果只要求最后一行，所以节约了许多内存，使整个代码的书写也变得简洁了不少
     *
     * 运行时长：2 ms
     *
     * 总结：
     * 但只达到了 14%，可见算法的优化空间很大，之后再处理吧
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>(rowIndex+1);
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = 1; j < i; j++) {
                result.add(result.get(0) + result.get(1));
                result.remove(0);
            }
            result.add(1);
        }
        return result;
    }
}
