package com.imzhizi.algs.LeetCode.LeetCode2018;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 * Example:
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 * created by zhizi on 2018/6/1
 */
public class A118_PascalTriangle {
    /**
     * 题目分析：
     * 就按照帕斯卡三角形的计算方法暴力得出，没有任何优化，时长短主要还是因为用例较少吧
     *
     * 运行时长：1 ms
     *
     * 总结：无
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result=new ArrayList<>();
        for (int i=0;i<numRows;i++){
            List<Integer> list=new ArrayList<>();
            list.add(1);
            for (int j = 1; j <= i-1; j++) {
                list.add(result.get(i-1).get(j-1)+result.get(i-1).get(j));
            }
            if(i>0) list.add(1);
            result.add(list);
        }
        return result;
    }



    public static void main(String[] args) {
        List<List<Integer>> result=generate(5);
        System.out.println(result);
    }
}
