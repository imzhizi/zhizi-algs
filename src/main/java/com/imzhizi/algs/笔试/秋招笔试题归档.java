package com.imzhizi.algs.笔试;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * created by zhizi
 * on 9/15/20 09:58
 */
public class 秋招笔试题归档 {
    public void suitableRole() {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        int[][] ret = new int[T][];
        while (--T >= 0) {
            int n = input.nextInt();
            int m = input.nextInt();
            int[] expect = new int[n];
            for (int i = 0; i < n; i++) {
                expect[i] = input.nextInt();
            }

            int[] roles = new int[m];
            for (int i = 0; i < m; i++) {
                roles[i] = input.nextInt();
            }
            Arrays.sort(roles);
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = roles.length - binarySearch(roles, expect[i]);
            }
            ret[T] = result;
        }
        input.close();
        for (int[] n : ret) {
            for (int i : n) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }

    public int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        if (target > nums[r]) {
            return nums.length;
        }
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (nums[m] >= target) {
                if (m == 0 || nums[m - 1] < target) {
                    return m;
                } else {
                    r = m;
                }
            } else if (nums[m] < target) {
                l = m;
            }
        }
        return l;
    }

    public void longestExpect() {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[] windows = new int[m];
        for (int i = 0; i < m; i++) {
            windows[i] = input.nextInt();
        }

        double e = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                e += Math.pow(1.0 / m, i) * i / windows[j];
            }
        }
        System.out.println(e);
    }


    public void stringReverse() {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        String s = input.next();
        System.out.println(s);
        input.close();
    }

    public String reverse(String s, int k) {
        List<Integer> chars = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        boolean flag = true;
        int loc = 0;
        int l = s.length();
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '0' && flag) {
                chars.add(1);
                count.add(i - loc);
                loc = i;
                flag = false;
            } else if (s.charAt(i) == '1' && !flag) {
                chars.add(0);
                count.add(i - loc);
                loc = i;
                flag = true;
            }
        }
        chars.add(flag ? 1 : 0);
        count.add(l - loc);
        if (k % 2 == 0) {
            k++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.size() - k; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < count.get(i + k); j++) {
                    sb.append(1);
                }
            } else {
                for (int j = 0; j < count.get(i + k); j++) {
                    sb.append(0);
                }
            }
        }
        return sb.toString();
    }
}
