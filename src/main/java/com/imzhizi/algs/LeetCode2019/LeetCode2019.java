package com.imzhizi.algs.LeetCode2019;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class LeetCode2019 {
    /**
     * [Triangle - LeetCode]( https://leetcode.com/problems/triangle/submissions/ )
     */
    @Test
    public void No120() {

    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) return triangle.get(0).get(0);

        for (int i = 1; i < triangle.size(); i++) {
            triangle.get(i).set(0, triangle.get(i).get(0) + triangle.get(i - 1).get(0));
            for (int j = 1; j < i; j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j)));
            }
            triangle.get(i).set(i, triangle.get(i).get(i) + triangle.get(i - 1).get(i - 1));
        }

        return Collections.min(triangle.get(triangle.size() - 1));
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int[] result = new int[triangle.size()];
        result[0] = triangle.get(0).get(0);

        for (int i = 1; i < result.length; i++) {
            result[i] = triangle.get(i).get(i) + result[i - 1];

            for (int j = i - 1; j > 0; j--) {
                result[j] = triangle.get(i).get(j) + Math.min(result[j - 1], result[j]);
            }

            result[0] = triangle.get(i).get(0) + result[0];
        }

        int min = Integer.MAX_VALUE;
        for (int i : result) {
            min = Math.min(min, i);
        }

        return min;
    }
}
