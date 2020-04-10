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

    /**
     * 网易面试
     */
    static class w1 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) {
                nums[i] = input.nextLong();
            }
            input.close();

            long min = Long.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                long gap = nums[i] - nums[i - 1];
                if (gap <= 0) {
                    System.out.println(-1);
                    return;
                }
                min = Math.min(min, gap);
            }

            for (int i = 1; i < n; i++) {
                if ((nums[i] - nums[i - 1]) % min != 0) {
                    System.out.println(-1);
                    return;
                }
            }

            System.out.println(min);
        }
    }

    static class w3 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            int m = input.nextInt();
            boolean[] virus = new boolean[n];
            virus[input.nextInt()] = true;

            for (int i = 0; i < m; i++) {
                int q = input.nextInt();
                int[] qs = new int[q];
                boolean flag = false;
                for (int j = 0; j < q; j++) {
                    qs[j] = input.nextInt();
                    if (virus[qs[j]]) flag = true;
                }
                if (flag) {
                    for (int j : qs) virus[j] = true;
                }
            }
            input.close();

            int count = 0;
            for (int i = 0; i < n; i++) {
                if (virus[i]) count++;
            }
            System.out.println(count);
        }
    }


    /**
     * 便利蜂
     */
    static class b1 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            List<Long> list = new ArrayList<>();

            long max = Long.MIN_VALUE;
            long sum = 0;

            String str = input.nextLine();
            input.close();
            String[] nums = str.split(",");
            for (String num : nums) {
                list.add(Long.valueOf(num));
                int size = list.size();
                sum += list.get(size - 1);
                if (list.size() > 3) sum -= list.get(size - 4);
                if (list.size() >= 3) max = Math.max(max, sum);
            }

            System.out.println(max);
        }
    }

    static class b3 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            input.close();
            String[] strs = str.split(",");
            int[] nums = new int[strs.length];
            for (int i = 0; i < strs.length; i++) nums[i] = Integer.parseInt(strs[i]);

            int time = 1;
            int l = nums.length;
            if (l < 3) {
                time = l - 1;
            } else {
                for (int i = 0; i < l && nums[i] < l - 1 - i; i++) {
                    int step = 0;
                    int stepI = 0;
                    for (int j = 1; j <= nums[i]; j++) {
                        if (nums[i + j] + j > step) {
                            step = nums[i + j] + j;
                            stepI = i + j;
                        }
                    }

                    i = stepI - 1;
                    if (i < 0) {
                        System.out.println(-1);
                        return;
                    }
                    time++;
                }
            }

            System.out.println(time);
        }
    }
}
