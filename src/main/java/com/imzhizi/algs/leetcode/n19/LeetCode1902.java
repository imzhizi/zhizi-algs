package com.imzhizi.algs.leetcode.n19;

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

    /* 思路一：4ms - 70% - 很容易想到的蠢方法, 先直接排序，然后返回倒数找到第三名 */
    int thirdMax(int[] nums) {
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
    int thirdMax2(int[] nums) {
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
    private int findPairs(int[] nums, int k) {
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
    private int findPairs2(int[] nums, int k) {
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
    private int maxProfit(int[] prices) {
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
    private List<Integer> findDisappearedNumbers(int[] nums) {
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
    List<Integer> findDisappearedNumbers2(int[] nums) {
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
    private int findMaxConsecutiveOnes(int[] nums) {
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
    private int[][] matrixReshape(int[][] nums, int r, int c) {
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
    private int findUnsortedSubarray(int[] nums) {
        int begin = -1;
        int end = -1;

        int i = 0;
        int j = nums.length - 1;

        while ((begin == -1 || end == -1) && i <= j) {
            int min = nums[i];
            int max = nums[i];

            for (int m = i + 1; m <= j; m++) {
                min = Math.min(min, nums[m]);
                max = Math.max(max, nums[m]);
            }

            if (begin == -1 && min != nums[i++]) begin = --i;
            if (end == -1 && max != nums[j--]) end = ++j + 1;
        }


        return end - begin;
    }

    /*
    思路二：18ms - 61%
    忘记考虑这种做法需要有一个数组专门用作对比使用，所以时间复杂度为 O(n + nlogn + n)
    还是比第一种方法快了许多的，但这还处于暴力比较的阶段
    */
    private int findUnsortedSubarray1(int[] nums) {
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


    /* 思路三：ms - % - */
    private int findUnsortedSubarray2(int[] nums) {
        //todo 抄的方法，需要再琢磨
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
    private boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1) return flowerbed[0] == 0 || n == 0;//

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
        Assert.assertTrue(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
        Assert.assertFalse(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
    }

    /**
     * 题目：[Maximum Product of Three Numbers - LeetCode](https://leetcode.com/problems/maximum-product-of-three-numbers/)
     *
     * 分析：可以借鉴之前寻找第三名的做法，在一次遍历中找出所有最值
     *
     * 总结：要记住这种在一次循环中寻找多个最值的思路
     */

    /*
     思路一：8ms - 75%
     少考虑了负数，如果存在负数，那么最大的就是最大的正数乘以最小的两个负数，所以需要寻找的不仅是前三名还有倒数两名
     */
    private int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;

        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;


        for (int l = 0; l < nums.length; l++) {
            if (min1 <= nums[l]) {
                min2 = Math.min(min2, nums[l]);
            } else {
                min2 = min1;
                min1 = nums[l];
            }

            if (max1 >= nums[l]) {
                if (max2 >= nums[l]) {
                    max3 = Math.max(max3, nums[l]);
                } else {
                    max3 = max2;
                    max2 = nums[l];
                }
            } else {
                max3 = max2;
                max2 = max1;
                max1 = nums[l];
            }
        }

        return max1 * Math.max(min1 * min2, max2 * max3);
    }

    @Test
    public void test628() {
        Assert.assertEquals(24, maximumProduct(new int[]{1, 2, 3, 4}));
        Assert.assertEquals(720, maximumProduct(new int[]{-4, -2, -3, -1, 60}));
    }


    /**
     * 题目：[Maximum Average Subarray I - LeetCode](https://leetcode.com/problems/maximum-average-subarray-i/)
     *
     * 分析：平均值最大的子串，因为长度是确定的，很容易想得的一种思路是一次遍历，逐个求平均值比较得出
     * 进而可以想到不必每次都求平均值，其实相当于固定长度的最大子串和，那求最大子串和的方法是什么？或许可以参考并加以利用
     *
     * 总结：算法真奇妙，确实利用到了之前求最大子串中保留上一步计算结果降低计算次数的方法，效果也十分明显
     */

    /*
     思路一：412ms - 7%
     如果通过遍历逐个计算来做，那么每次都要计算和，所以复杂度为 O(k*n)
     */
    private double findMaxAverage(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i <= nums.length - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            maxSum = Math.max(maxSum, sum);
        }

        return ((double) maxSum) / k;
    }

    /*
     思路二：8ms - 99%
     没想到思路一这么慢，所以必须要想其他办法，突然意识到，其实我也可以仿照以前的做法
     事实上每次不必重新求一个sum，对于现有的sum，只要减去第一个，加上新的一个即可，不过第一个sum要逐个相加得出
     这样时间复杂度降低到 O(k+n) 应该是一个有效的优化
     */
    private double findMaxAverage1(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];
        int maxSum = sum;

        for (int i = 1; i <= nums.length - k; i++) {
            sum -= nums[i - 1];
            sum += nums[i + k - 1];
            maxSum = Math.max(maxSum, sum);
        }

        return ((double) maxSum) / k;
    }


    @Test
    public void test643() {
        Assert.assertEquals(12.75, findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4), 0);
        Assert.assertEquals(5, findMaxAverage(new int[]{5}, 1), 0);
    }

    /**
     * 题目：[Non-decreasing Array - LeetCode](https://leetcode.com/problems/non-decreasing-array/)
     *
     * 分析：至多修改一个数字，能否变成递增不减数列，搞个计数器，遍历一次就可以了吧
     *
     * 总结：其实远没有想象得简单，做了很久，脑中的逻辑比较混乱，所以在正确思路附近徘徊了许久
     */

    /* 思路一：9ms - 100% - 发现异常时直接填入进行修改，记录修改次数，若接下来再次修改则 false，反之 true */
    private boolean checkPossibility(int[] nums) {
        int count = 0; // 表示出现异常的次数

        for (int i = 0; i < nums.length - 1; i++) {
            // 假如说当前的数字为B，当B大于C的时候不满足不减数列，即出现异常，此时有两种情况
            if (nums[i] > nums[i + 1]) {
                count++;
                if (i > 0 && nums[i + 1] < nums[i - 1]) //B大于C，A也大于C，说明C自身有问题，要修改C
                    nums[i + 1] = nums[i];
                else    // A小于C，说明是自己B的问题，B改为A/C都万事大吉
                    nums[i] = nums[i + 1];
            } else if (count > 1) { // 这个判断是一个很重要的优化
                return false;
            }
        }

        return count <= 1; // 异常带来的修改不超过一次即可
    }

    @Test
    public void test665() {
        Assert.assertTrue(checkPossibility(new int[]{4, 2, 3}));
        Assert.assertTrue(checkPossibility(new int[]{-1, 4, 2, 3}));
        Assert.assertTrue(checkPossibility(new int[]{2, 4, 4, 2, 5}));
        Assert.assertTrue(checkPossibility(new int[]{1, 3, 4, 2}));

        Assert.assertFalse(checkPossibility(new int[]{3, 4, 2, 3}));
    }


    /**
     * 题目：[Degree of an Array - LeetCode](https://leetcode.com/problems/degree-of-an-array/)
     *
     * 分析：数列的度就是一个数列中出现次数最多数字出现的次数，求这个子串的长度，也就是以此数开始，以此数结束的子串长度
     *
     * 总结：复杂的问题还是要理清楚，想清楚之后才又解决的可能
     */

    /* 思路一：24ms - 78% - 非常的朴实的思路 */
    private int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>(); // countmap 用来保存数字出现的次数
        HashMap<Integer, Integer> indexMap = new HashMap<>(); //indexmap 用来保存数字第一次出现的位置

        int length = 1; //用 length 表示含有出现最多数字的子串
        int count = 1;  // 使用 count 表示当前出现次数最多的数字

        for (int i = 0; i < nums.length; i++) {
            //如何更新count和length，遍历中一旦发现map中含有某一数字，就使count加一：countMap.put(nums[i], v)
            // length 则通过当前数字的下标减去第一次出现的坐标来计算
            if (countMap.containsKey(nums[i])) {
                int v = countMap.get(nums[i]) + 1;
                if (v > count) {
                    count = v;
                    length = i - indexMap.get(nums[i]) + 1;
                } else if (v == count) {
                    length = Math.min(length, i - indexMap.get(nums[i]) + 1);
                }
                countMap.put(nums[i], v);
            } else {
                countMap.put(nums[i], 1); // 第一次出现时出现次数为1
                indexMap.put(nums[i], i); // 第一次出现的坐标
            }
        }

        return length;
    }

    /* 思路二：9ms - 99%
    其实和思路一是相同的做法，只不过从 Hashmap 换成了数组，难道是 contains() 函数比较慢吗 */
    private int findShortestSubArray1(int[] nums) {
        int[] countMap = new int[50000];
        int[] indexMap = new int[50000];
        int length = 1;
        int count = 1;

        for (int i = 0; i < nums.length; i++) {
            if (countMap[nums[i]] != 0) {
                int v = countMap[nums[i]] + 1;
                if (v > count) {
                    count = v;
                    length = i - indexMap[nums[i]] + 1;
                } else if (v == count) {
                    length = Math.min(length, i - indexMap[nums[i]] + 1);
                }
                countMap[nums[i]]++;
            } else {
                countMap[nums[i]]++;
                indexMap[nums[i]] = i;
            }
        }

        return length;
    }


    @Test
    public void test697() {
        Assert.assertEquals(2, findShortestSubArray(new int[]{1, 2, 2, 3, 1}));
        Assert.assertEquals(7, findShortestSubArray(new int[]{2, 1, 1, 2, 1, 3, 3, 3, 1, 3, 1, 3, 2}));
    }

    /**
     * 题目：[1-bit and 2-bit Characters - LeetCode](https://leetcode.com/problems/1-bit-and-2-bit-characters/)
     *
     * 分析：主要是要判断一个字符串是以10结尾， 还是以0结尾，那核心就在于在前方做好断句
     *
     * 总结：一道很简单的题目，其实前面的都不太重要，主要是为了知道最后两位是一个 2-bit 还是 1-bit 的字符
     * 所以执行过程中只要能判断是两位，跳过即可
     */

    /* 思路一：3ms - 100% - 某种程度上，这也算贪心算法 */
    private boolean isOneBitCharacter(int[] bits) {
        for (int i = 0; i < bits.length; i++) {
            if (i == bits.length - 1) return true;
            if (bits[i] == 1) i++;
        }
        return false;
    }

    @Test
    public void test717() {
        Assert.assertTrue(isOneBitCharacter(new int[]{1, 0, 0}));
        Assert.assertFalse(isOneBitCharacter(new int[]{1, 1, 1, 0}));
    }

    /**
     * 题目：[Find Pivot Index - LeetCode](https://leetcode.com/problems/find-pivot-index/)
     *
     * 分析：这个坐标两端数字的和相等，显然可以两侧辗转相加，哪边和比较小哪边就向中间移动
     * 但事实上这道题我做了很久很久还是没做出来，在负数参与进来之后，情况越来越多，脑子越想越乱
     *
     * 总结：
     */

    /* 思路一：17ms - 64% - 参考了讨论区的一种很简洁的做法 */
    /* 思路就是先求出总和 total，然后再逐个判断，如果这个点是 pivot，那么这个点之前的和 sum 一定等于这个点之后的数据的和
       即 sum+nums[pivot]+sum=total
     */
    private int pivotIndex(int[] nums) {
        int total = 0, sum = 0;
        for (int i : nums) total += i;
        for (int i = 0; i < nums.length; i++) {
            if (sum * 2 == total - nums[i]) return i;
            sum += nums[i];
        }
        return -1;
    }


    /* 思路一：ms - % - */
    private int pivotIndex1(int[] nums) {
        //todo
        return -1;
    }


    @Test
    public void test724() {
        Assert.assertEquals(3, pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
        Assert.assertEquals(-1, pivotIndex(new int[]{1, 2, 3}));
        Assert.assertEquals(1, pivotIndex(new int[]{-1, -1, -1, -1, 0, 1}));
        Assert.assertEquals(2, pivotIndex(new int[]{-1, -1, -1, -1, -1, 0}));
        Assert.assertEquals(2, pivotIndex(new int[]{-1, -1, -1, -1, 0, -1}));
        Assert.assertEquals(2, pivotIndex(new int[]{-1, -1, -1, 0, -1, -1}));
        Assert.assertEquals(3, pivotIndex(new int[]{-1, -1, 0, -1, -1, -1}));
        Assert.assertEquals(3, pivotIndex(new int[]{-1, 0, -1, -1, -1, -1}));
    }
}
