package com.imzhizi.algs;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * created by zhizi on 2019-02-04
 */
public class LeetCode1902 {
    /**
     * 题目：[Third Maximum Number - LeetCode](https://leetcode.com/problems/third-maximum-number/)
     *
     * 分析：返回第三大的数字，可以理解成寻找考试成绩的第三名，要注意的点在于存在并列的情况
     *
     * 总结：看似简单的一道题目，其实并不好处理，不过最后确实也没使用比较好的方法解决
     */

    /*
    思路一：4ms - 70%
    本来想直接排序，然后返回倒数第三个数字，但需要处理并列问题，所以需要找到真正的第三名
    只好对排序好的数列进行遍历，找出真正的第三名
     */
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int loc = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i])
                loc++;
            if (loc == 3)
                return nums[i];
        }

        return nums[nums.length - 1];
    }

    /*
    思路二：2ms - 98%
    思路是遍历数列，每个数字都与min、mid、max三个数字进行比较，保存最大的三个数字
    但因为数列中存在 Integer.MIN_VALUE，所以只有当三个数字足够小才能成功传值
     */
    public int thirdMax2(int[] nums) {
        long min = Long.MIN_VALUE;
        long mid = Long.MIN_VALUE;
        long max = Long.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                min = mid;
                mid = max;
                max = nums[i];
            } else if (nums[i] < max) {
                if (nums[i] > mid) {
                    min = mid;
                    mid = nums[i];
                } else if (nums[i] < mid) {
                    min = Math.max(min, nums[i]);
                }
            }
        }

        if (min < Integer.MIN_VALUE)
            return (int) max;
        else
            return (int) min;
    }


    @Test
    public void test414() {
        Assert.assertEquals(3, thirdMax2(new int[]{3, 1, 1}));
        Assert.assertEquals(2, thirdMax2(new int[]{2, 1}));
        Assert.assertEquals(1, thirdMax2(new int[]{2, 3, 2, 1}));
    }


    /**
     * 题目：[K-diff Pairs in an Array - LeetCode](https://leetcode.com/problems/k-diff-pairs-in-an-array/)
     *
     * 分析：计算数列中相差为 k 的数字对的数量，感觉是一道比较困难的题目
     *
     * 总结：
     */

    /*
    思路一 - 725ms - 3%
    两重循环遍历数组，直接逐个求差、求和，当等于 k 时则 result++
    但事实上，题目要求的 k-diff 指的是绝对值，又因为 <i,j> 和 <j,i> 等同，所以可以总是用大数减小数与 k 比较（max和min）
    同时 k 取负数时的情况也被过滤，缺点是这种方法效率太低
     */
    public int findPairs(int[] nums, int k) {
        int result = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int max = Math.max(nums[i], nums[j]);
                int min = Math.min(nums[i], nums[j]);
                if (max - min == k) {
                    if (!set.contains(max)) {
                        result++;
                        set.add(max);
                    }
                }
            }
        }

        return result;
    }


    /*
    思路二 - 20ms - 48%
    思路一是逐个尝试，也可以直接计算出来对应数字，去查看是否存在
    但是因为 k 取值的关系，需要情况处理，此方法的弊端是维护两个HashSet，内存占用较大
     */
    public int findPairs2(int[] nums, int k) {
        int result = 0;
        HashSet<Integer> numSet = new HashSet<>();
        HashSet<Integer> appearSet = new HashSet<>();

        // 因为题目中要求的是绝对值为k，但测试用例中k居然能取负值，简直没道理，所以先行判断
        if (k < 0) {
            return 0;
        }
        // 当k取0时，多个相同的数字只能记为出现一次
        else if (k == 0) {
            for (int i = 0; i < nums.length; i++) {
                if (numSet.contains(nums[i]) && !appearSet.contains(nums[i])) {
                    result++;
                    appearSet.add(nums[i]);
                } else
                    numSet.add(nums[i]);
            }
            return result;
        }
        // 正常情况，首先把 nums 转为 HashSet，然后统计
        else {
            for (int i = 0; i < nums.length; i++) {
                numSet.add(nums[i]);
            }

            for (int i = 0; i < nums.length; i++) {
                if (numSet.contains(nums[i] + k) && !appearSet.contains(nums[i] + k)) {
                    result++;
                    appearSet.add(nums[i] + k);
                }
            }

            return result;
        }
    }


    @Test
    public void test532() {
        Assert.assertEquals(2, findPairs2(new int[]{3, 1, 4, 1, 5}, 2));
        Assert.assertEquals(1, findPairs2(new int[]{3, 1, 4, 1, 5}, 0));
        Assert.assertEquals(1, findPairs2(new int[]{1, 1, 1, 1, 5}, 0));
    }

    /**
     * 题目：[Best Time to Buy and Sell Stock II - LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)
     *
     * 分析：能感觉到这是一道比较需要算法思想的题目，但我并没有系统地学过算法，尝试一下吧
     *
     * 总结：
     */

    /*
     思路一：1ms - 99%
     我猜测这道题可能需要所谓的贪心算法，个人理解是
     1、有得赚就买，这很容易理解，只要今天的比明天价格低，买了就能赚
     2、比明天赚得多就卖，已经买了如何决定卖不卖呢？只要比明天多，即便后面有赚得更多的，但明天可以买
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        int hold = -1;

        for (int i = 0; i < prices.length - 1; i++) {
            if (hold != -1 && prices[i] > prices[i + 1]) {// 要不要卖
                profit += prices[i] - hold;
                hold = -1;
            } else if (hold == -1 && prices[i] < prices[i + 1]) { //要不要买
                hold = prices[i];
            }
        }
        if (hold != -1) profit += prices[prices.length - 1] - hold;

        return profit;
    }

    @Test
    public void test122() {
        Assert.assertEquals(7, maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        Assert.assertEquals(4, maxProfit(new int[]{1, 2, 3, 4, 5}));
    }


    /**
     * 题目：[Find All Numbers Disappeared in an Array - LeetCode](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)
     *
     * 分析：从一个数列中找到所有消失的数字，根据提示，最优解法能达到 O(n) 时间复杂度和 O(1) 空间复杂度
     *
     * 总结：很多时候面对一个所有数据都需要保存的数列总是无从下手，想要新建一个数列来保存结果，这道题提供了别的思路
     */

    /*
     思路一：10ms - 64%
     使用一种近似于哈希的思想，记录下哪些数字出现了，未出现的在数组中就为0
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] loc = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            loc[nums[i] - 1]++;
        }

        for (int i = 0; i < loc.length; i++) {
            if (loc[i] == 0) list.add(i + 1);
        }

        return list;
    }

    /*
     思路二：8ms - 93%
     这个方法是在讨论区学到的，通过不断地交换使得所有的数字都回到了应该在的位置，本质和思路一相近
     很棒的一个方法
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
                i--;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                list.add(i + 1);
            }
        }

        return list;
    }


    @Test
    public void test448() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        Assert.assertEquals(list, findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        list.clear();
        list.add(2);
        Assert.assertEquals(list, findDisappearedNumbers(new int[]{1, 1}));

    }


    /**
     * 题目：[Max Consecutive Ones - LeetCode](https://leetcode.com/problems/max-consecutive-ones/)
     *
     * 分析：寻找最长的连续相同数字，感觉不是特别复杂，遍历一次即可
     *
     * 总结：出得比较差的题目，没想到题目这么简单，只要寻找连续的 1 即可，并不是相同数字
     */

    /*
    思路一：4ms - 99%
    原本以为是连续数字，没想到是 1，直接判断是否唯一，计算最大长度即可。
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int length = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                length++;
            } else {
                max = Math.max(max, length);
                length = 0;
            }
        }
        return Math.max(max, length);
    }

    /**
     * 题目：[Reshape the Matrix - LeetCode](https://leetcode.com/problems/reshape-the-matrix/)
     *
     * 分析：出得比较差的题目，因为题目的例子给得很不清楚，导致我走了很多弯路，reshape 的规则说的不清不楚
     *
     * 总结：无
     */

    /*
     思路一：3ms - 100%
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rr = nums.length;
        int cc = nums[0].length;

        if (r != rr && (rr * cc) % r == 0) {
            int[][] result = new int[r][(rr * cc) / r];
            int m = 0;
            int n = 0;

            for (int i = 0; i < rr; i++) {
                for (int j = 0; j < cc; j++) {
                    result[m][n++] = nums[i][j];
                    if (n == c) {
                        m++;
                        n = 0;
                    }
                }
            }

            return result;
        } else {
            return nums;
        }
    }

    @Test
    public void test566() {
        int[][] nums = new int[][]{
                {1, 2},
                {3, 4}
        };
        Assert.assertArrayEquals(new int[][]{{1, 2, 3, 4}}, matrixReshape(nums, 1, 4));
        Assert.assertArrayEquals(nums, matrixReshape(new int[][]{{1, 2, 3, 4}}, 2, 2));

    }


    /**
     * 题目：[Shortest Unsorted Continuous Subarray - LeetCode](https://leetcode.com/problems/shortest-unsorted-continuous-subarray/)
     *
     * 分析：求最短待排序子串的长度，存在已经递增，但是后面出现更小数字的情况，感觉不太好处理
     *
     * 总结：
     */

    /*
     思路一：190ms - 2.7%
     假如先排序，然后逐一对比即可找到该子串，但这样明显效果不好，先考虑其他算法
     可以尝试使用类似于插入排序的方法，一旦遇到不匹配的情况，即视为子串开始，然后从后向前寻找结束，但这种方法也很慢
     这种方法果然慢的可以，时间复杂度接近于 O(n^2)
     */
    public int findUnsortedSubarray(int[] nums) {
        int begin = -1;
        int end = -1;

        int i = 0;
        int j = nums.length - 1;

       while ((begin==-1||end==-1)&&i<=j){
            int min = nums[i];
            int max = nums[i];

            for (int m = i + 1; m <= j; m++) {
                min = Math.min(min, nums[m]);
                max = Math.max(max,nums[m]);
            }

            if (begin==-1&& min != nums[i++]) begin = --i;
            if (end==-1&& max != nums[j--]) end = ++j+1;
        }


        return end - begin;
    }

    /*
    思路二：18ms - 61%
    忘记考虑这种做法需要有一个数组专门用作对比使用，所以时间复杂度为 O(n + nlogn + n)
    还是比第一种方法快了许多的，但这还处于暴力比较的阶段
    */
    public int findUnsortedSubarray1(int[] nums) {
        int[] sortNums = nums.clone();
        Arrays.sort(sortNums);

        int i = 0;
        int j = nums.length - 1;

        while (i <= j && (nums[i] == sortNums[i] || nums[j] == sortNums[j])) {
            if (i == j) return 0;
            if (nums[i] == sortNums[i]) i++;
            if (nums[j] == sortNums[j]) j--;
        }

        return j - i + 1;
    }


    /*
    思路三：ms - %
    抄的方法，需要再琢磨
    */
    public int findUnsortedSubarray2(int[] nums) {
        int i = 0, j = -1;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int l = 0, r = nums.length - 1; r >= 0; l++, r--) {
            max = Math.max(max, nums[l]);
            if (nums[l] != max) j = l; //正向寻找最大数

            min = Math.min(min, nums[r]);
            if (nums[r] != min) i = r; //逆向寻找最小数
        }
        return (j - i + 1);
    }

    @Test
    public void test581() {
        Assert.assertEquals(5, findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }


    /**
     * 题目：[Can Place Flowers - LeetCode](https://leetcode.com/problems/can-place-flowers/)
     *
     * 分析：能不能种花，我想得很简单，假如前面是0，后面是0，那就可以种，遍历即可
     *
     * 总结：跟预想的差不多
     */

    /*
     思路一：6ms - 85%
     跟分析中说得一样，希望通过一次遍历来确认是否可以种花
     我发现自身存在一个问题，不喜欢自己思考问题，总是直接提交平台，希望平台来进一步发现找到我程序不合适的地方
     同时这道题的测试用例挺差的，有很多
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1) return flowerbed[0] == 0 || n == 0;

        if (flowerbed[0] == 0 && flowerbed[1] == 0) {
            n--;
            flowerbed[0] = 1;
        }

        for (int i = 2; i < flowerbed.length - 2; i++) {
            if (flowerbed[i - 1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                n--;
                flowerbed[i++] = 1;
            }
        }

        if (flowerbed[flowerbed.length - 2] == 0 && flowerbed[flowerbed.length - 1] == 0) {
            n--;
            flowerbed[flowerbed.length - 1] = 1;
        }

        return n <= 0;
    }

    @Test
    public void test605() {
        Assert.assertEquals(true,canPlaceFlowers(new int[]{1,0,0,0,1},1));
        Assert.assertEquals(false,canPlaceFlowers(new int[]{1,0,0,0,1},2));
    }

    /**
     * 题目：
     *
     * 分析：
     *
     * 总结：
     */

    /*
     思路一：ms - %
     */
    @Test
    public void test() {

    }
}
