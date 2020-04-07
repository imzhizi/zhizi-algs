package com.imzhizi.algs.Common;

import java.util.*;

/**
 * created by zhizi
 * on 3/29/20 21:08
 */
public class Archive {
    /**
     * 百度
     */
    static class s1 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            long n = input.nextLong();
            input.close();
            System.out.println(n * (n - 1) - 1);
        }
    }

    static class s2 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            long[] nums = new long[n];
            long max = Long.MIN_VALUE;
            int index = -1;
            for (int i = 0; i < n; i++) {
                nums[i] = input.nextLong();
                if (max < nums[i]) {
                    max = nums[i];
                    index = i;
                }
                max = Math.max(max, nums[i]);
            }
            input.close();

            int count = 0;
            while (max >= n) {
                if (n > 10) break;
                count++;
                nums[index] -= n;
                max -= n;
                int mi = index;
                for (int i = 0; i < n; i++) {
                    if (i != mi) {
                        nums[i]++;
                    }
                    if (max < nums[i]) {
                        max = nums[i];
                        index = i;
                    }
                }
            }

            System.out.println(count);
        }
    }

    /**
     * 美团
     */
    static class m1 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            int gap = input.nextInt();

            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = input.nextInt();
            }
            input.close();
            Arrays.sort(nums);
            int[][] matrix = new int[n][n];
            System.out.println(lowCost(nums, matrix, 0, n - 1, gap));
        }

        static int lowCost(int[] nums, int[][] matrix, int head, int tail, int gap) {
            if (nums[tail] - nums[head] <= gap && head <= tail) return nums.length - (tail - head + 1);
            if (matrix[head][tail] != 0) return matrix[head][tail];
            matrix[head][tail] = Math.min(lowCost(nums, matrix, head + 1, tail, gap), lowCost(nums, matrix, head, tail - 1, gap));
            return matrix[head][tail];
        }
    }

    static class m2 {
        public static void main(String[] args) {

            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            long m = input.nextLong();
            int[] rooms = new int[n];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                rooms[i] = input.nextInt();
                sum += rooms[i];
            }
            input.close();

            long count = m / sum * n;
            long mod = m % sum;

            int i = 0;
            while (mod >= 0) {
                if (mod >= rooms[i % n]) {
                    mod -= rooms[i % n];
                    count++;
                    i++;
                }
            }
            System.out.println(count);
        }
    }
}
