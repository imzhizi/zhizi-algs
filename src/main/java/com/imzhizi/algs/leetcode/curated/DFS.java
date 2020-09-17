package com.imzhizi.algs.leetcode.curated;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by zhizi
 * on 9/16/20 11:24
 */
public class DFS {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int n : nums) {
            path.add(n);
        }
        permute(ret, new ArrayList<>(), path);
        return ret;
    }

    public void permute(List<List<Integer>> ret, List<Integer> list, List<Integer> path) {
        if (path.size() == 1) {
            list.add(path.get(0));
            ret.add(list);
            return;
        }

        for (int i = 0; i < path.size(); i++) {
            int n = path.remove(0);
            List<Integer> l = new ArrayList<>(list);
            l.add(n);
            permute(ret, l, path);
            path.add(n);
        }
    }

    @Test
    public void test(){
        System.out.println("leetcode".substring(0,3));
    }
}
