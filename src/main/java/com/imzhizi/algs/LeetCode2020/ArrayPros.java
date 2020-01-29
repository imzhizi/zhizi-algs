package com.imzhizi.algs.LeetCode2020;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayPros {

    /**
     * [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)
     * 求多个数列的中位数
     */
    @Test
    public void No4() {

    }

    // 首先是最朴实的合并法, 建造数组(m+n), 然后计算中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
        int n1 = 0;
        int n2 = 0;
        while (n1 + n2 < m + n) {
            if (n1 == m) {
                nums[n1 + n2] = nums2[n2++];
            } else if (n2 == n) {
                nums[n1 + n2] = nums1[n1++];
            } else {
                nums[n1 + n2] = nums1[n1] < nums2[n2] ? nums1[n1++] : nums2[n2++];
            }
        }

        if ((m + n) % 2 == 0) {
            return (nums[(m + n) / 2 - 1] + nums[(m + n) / 2]) / 2.0;
        } else {
            return (double) nums[(m + n) / 2];
        }
    }

    // 一种可能的改进是在合并的同时就进行中位数的计算
    // 聊胜于无的改进, 只不过用了稍微小一点的空间, 本质没有变化
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int l = m + n;
        int[] nums = new int[l / 2 + 1];
        int n1 = 0;
        int n2 = 0;
        while (n1 + n2 < l / 2 + 1) {
            if (n1 == m) {
                nums[n1 + n2] = nums2[n2++];
            } else if (n2 == n) {
                nums[n1 + n2] = nums1[n1++];
            } else {
                nums[n1 + n2] = nums1[n1] < nums2[n2] ? nums1[n1++] : nums2[n2++];
            }
        }

        if ((m + n) % 2 == 0) {
            return (nums[(m + n) / 2 - 1] + nums[(m + n) / 2]) / 2.0;
        } else {
            return (double) nums[(m + n) / 2];
        }
    }

    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int l = m + n;

        // 用来记录数组遍历的位置
        int n1 = 0;
        int n2 = 0;
        int num1 = 0;
        int num2 = 0;

        // 如果能直接在遍历的时侯拿到值, 那么直接就可以得出结果
        while (n1 + n2 < (l / 2 + 1)) {
            if (n1 == m) {
                num1 = num2;
                num2 = nums2[n2++];
            } else if (n2 == n) {
                num1 = num2;
                num2 = nums1[n1++];
            } else {
                num1 = num2;
                num2 = nums1[n1] < nums2[n2] ? nums1[n1++] : nums2[n2++];
            }
        }

        if ((m + n) % 2 == 0) {
            return (num1 + num2) / 2.0;
        } else {
            return (double) num2;
        }
    }

    /**
     * https://leetcode.com/problems/container-with-most-water/
     */
    @Test
    public void No11() {

    }

    public int maxArea1(int[] height) {
        int max = 0;
        int l = height.length;
        int[] areas = new int[l];
        for (int i = 0; i < l - 1; i++) {
            for (int j = i + 1; j < l; j++) {
                max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            }
        }

        return max;
    }

    // 辗转法
    // 一开始思路就错了, 希望从DP, 最大子串和的角度做, 但其实区别很大, 这个题目的值只由两个数字产生
    public int maxArea2(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            max = Math.max(max, (r - l) * (height[l] < height[r] ? height[l++] : height[r--]));
        }
        return max;
    }


    @Test
    public void No15() {

    }

    // 一开始思路就错了, 这样的话, 无法判断是否重复
    public List<List<Integer>> threeSum(int[] nums) {
        int l = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < l - 2; i++) {
            for (int j = i + 1; j < l - 1; j++) {
                List<Integer> list = new ArrayList<Integer>();
                for (int k = j + 1; k < l; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        break;
                    }
                }
                if (!list.isEmpty()) {
                    result.add(list);
                }
            }
        }
        return result;
    }

    // 优化版本, 41 ms 54% 46.1 MB 91%
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r && r < nums.length) {
                if (nums[i] + nums[l] + nums[r] == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    result.add(list);
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) l++;
                } else if (nums[i] + nums[l] + nums[r] > 0) {
                    r--;
                } else {
                    l++;
                }
            }

            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/3sum-closest/
     */
    @Test
    public void No16() {

    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (target > sum) {
                    l++;
                } else if (target == sum) {
                    return sum;
                } else {
                    r--;
                }
                if (Math.abs(target - sum) < Math.abs(min)) {
                    min = target - sum;
                }
            }
        }

        return target - min;
    }

    // https://leetcode-cn.com/problems/4sum/
    @Test
    public void No18() {

    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i + 1]) {
                continue;
            }
            for (int j = nums.length - 1; j > i + 2; j--) {
                if (j < nums.length - 2 && nums[j] == nums[j + 1]) {
                    continue;
                }
                while (j > i && nums[j] == nums[j - 1]) j--;
                int l = i + 1;
                int r = j - 1;
                while (l < r) {
                    if (nums[i] + nums[l] + nums[r] + nums[j] == target) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        list.add(nums[j]);
                        result.add(list);
                        l++;
                        r--;
                        while (l < r && nums[l] == nums[l - 1]) l++;
                        while (r > l && nums[r] == nums[r + 1]) r--;
                    } else if (nums[i] + nums[l] + nums[r] + nums[j] > target) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }
        return result;
    }

    // https://leetcode-cn.com/problems/next-permutation/
    @Test
    public void No31() {

    }

    public void nextPermutation(int[] nums) {
        // 首先判断是否是最大的(单向递减)
        int biggest = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                biggest = i;
            }
        }

        if (biggest == -1) {
            //翻转
            int i = 0;
            int j = nums.length - 1;
            while (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        } else {
            // 如何拿到下一个最接近的数字
            // biggest 说明需要进位的地方, 工作应该放在如何处理进位
            // 具体来说, 进位处应该是后方最接近被进位数字的数字, 其他数字递增排列
            // 所以可以先把后方递增排列, 然后用最近接近的和进位处交换
            // 排序如何做? 可以冒泡排序? 快速排序?
            Arrays.sort(nums, biggest, nums.length);
            for (int i = biggest; i < nums.length; i++) {
                if (nums[i] > nums[biggest - 1]) {
                    int temp = nums[biggest - 1];
                    nums[biggest - 1] = nums[i];
                    nums[i] = temp;
                    break;
                }
            }
        }
    }


    // [ 33. 搜索旋转排序数组 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/ )
    @Test
    public void No33() {

    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int l = 0;
        int r = nums.length - 1;
        if (target > nums[l]) {
            while (l < nums.length - 1 && target != nums[l]) {
                l++;
                if (nums[l] < nums[l - 1]) {
                    return -1;
                }
            }
        } else if (target < nums[l]) {
            while (r > 0 && target != nums[r]) {
                r--;
                if (nums[r] > nums[r + 1]) {
                    return -1;
                }
            }
        }

        if (target == nums[l]) {
            return l;
        } else if (target == nums[r]) {
            return r;
        } else {
            return -1;
        }
    }

    // [ 34. 在排序数组中查找元素的第一个和最后一个位置 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/ )
    @Test
    public void No34() {

    }

    // 性能一般, 这道题只跟target的首尾值相关, 其实可以用双头指针来做
    public int[] searchRange1(int[] nums, int target) {
        int[] result = new int[2];
        boolean flag = false;
        result[0] = -1;
        result[1] = -1;
        for (int i = 0; i < nums.length; i++) {
            if (!flag && nums[i] == target) {
                flag = true;
                result[0] = i;
                result[1] = i;
            }
            if (flag && (i == nums.length - 1 || nums[i + 1] != target)) {
                flag = false;
                result[1] = i;
                return result;
            }
        }

        return result;
    }

    // 双头指针法, 也没太多提升
    public int[] searchRange2(int[] nums, int target) {
        int[] result = new int[2];
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            if (nums[l] == target && nums[r] == target) {
                result[0] = l;
                result[1] = r;
                return result;
            }
            if (nums[l] < target) l++;
            if (nums[r] > target) r--;
        }
        result[0] = -1;
        result[1] = -1;
        return result;
    }

    // 二分查找的边界条件掌握非常差, 折腾好久才做出来
    public int[] searchRange3(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (nums.length == 0) return result;

        int h = 0;
        int t = nums.length - 1;
        int m = (h + t) / 2;

        while (h < t && m < t) {
            if (nums[m] > target) {
                t = m;
                m = (h + t) / 2;
            } else if (nums[m] < target) {
                h = m;
                m = (h + t + 1) / 2;
            } else {
                result[0] = m;
                result[1] = m;
                break;
            }
        }

        h = m;
        t = m;
        while (h > -1 && nums[h] == target) {
            result[0] = h;
            h--;
        }
        while (t < nums.length && nums[t] == target) {
            result[1] = t;
            t++;
        }

        return result;
    }

    // [ 39. 组合总和 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/combination-sum/ )
    @Test
    public void No39() {

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        workable1(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    public void workable1(List<List<Integer>> result, List<Integer> list, int[] candidates, int start, int target) {
        for (int i = start; i < candidates.length; i++) {
            if (target > candidates[i]) {
                List<Integer> copy = new ArrayList<>(list);
                copy.add(candidates[i]);
                workable1(result, copy, candidates, i, target - candidates[i]);
            } else if (target == candidates[i]) {
                List<Integer> copy = new ArrayList<>(list);
                copy.add(candidates[i]);
                result.add(copy);
                return;
            } else {
                return;
            }
        }
    }

    /**
     * 不知道是不是玄学, 感觉多加return在递归中能够更快结束, 占用更少的内存
     * 不算事优化方法, 在这种方法中, 虽然无需事先排序, 但是这样会产生许多无用枝干
     */
    public void workable2(List<List<Integer>> result, List<Integer> list, int[] candidates, int start, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        } else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                if (target >= candidates[i]) {
                    List<Integer> copy = new ArrayList<>(list);
                    copy.add(candidates[i]);
                    workable2(result, copy, candidates, i, target - candidates[i]);
                }
            }
            return;
        } else {
            return;
        }
    }

    // [ 40. 组合总和 II - 力扣（LeetCode） ](https://leetcode-cn.com/problems/combination-sum-ii/ )
    @Test
    public void No40() {

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        process(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    public void process(List<List<Integer>> result, List<Integer> list, int[] candidates, int start, int target) {
        for (int i = start; i < candidates.length; i++) {
            if (target > candidates[i]) {
                List<Integer> copy = new ArrayList<>(list);
                copy.add(candidates[i]);
                process(result, copy, candidates, i + 1, target - candidates[i]);
                while (i < candidates.length - 1 && candidates[i] == candidates[i + 1]) i++;
            } else if (target == candidates[i]) {
                List<Integer> copy = new ArrayList<>(list);
                copy.add(candidates[i]);
                result.add(copy);
                return;
            } else {
                return;
            }
        }
    }

    // [ 41. 缺失的第一个正数 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/first-missing-positive/ )
    @Test
    public void No41() {
        int[] nums = {1, 2, 0};
        System.out.println(firstMissingPositive1(nums));
        System.out.println(firstMissingPositive2(nums));
    }

    // 很容易想到使用哈希法来做
    public int firstMissingPositive1(int[] nums) {
        int[] count = new int[nums.length];

        for (int num : nums) {
            if (num > 0 && num <= nums.length) {
                count[num - 1]++;
            }
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    // 题目要求只能使用常数级的空间, 所以就不能使用哈希法
    // 如果用自身做哈希表, 比如本题使用负数表示, 就有覆盖问题, 要保证未处理就变了的数字能找回原数字
    public int firstMissingPositive2(int[] nums) {
        int flag = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = flag;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int abs = Math.abs(nums[i]);
            if (abs > 0 && abs <= nums.length) {
                if (i >= abs) {
                    nums[abs - 1] = flag * -1;
                } else if (nums[abs - 1] > 0) {
                    nums[abs - 1] *= -1;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return flag;
    }

    // [ 42. 接雨水 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/trapping-rain-water/ )
    @Test
    public void No42() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap0(height));
        System.out.println(trap1(height));
        System.out.println(trap2(height));
    }

    // 不可用
    public int trap0(int[] height) {
        List<Integer> list = new ArrayList<>();
        int h = 0;
        int l = height.length;
        while (h < l - 1 && height[h] <= height[h + 1]) {
            h++;
        }
        int summit = h;
        list.add(summit);
        h++;
        while (h < l) {
            if (height[h] >= height[h - 1] && ((h < l - 1 && height[h] >= height[h + 1]) || h == l - 1)) {
                summit = h;
                list.add(summit);
            }
            h++;
        }

        int sum = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            int min = Math.min(height[list.get(i)], height[list.get(i + 1)]);
            for (int j = list.get(i) + 1; j < list.get(i + 1); j++) {
                if (min > height[j]) sum = sum + min - height[j];
            }

        }
        return sum;
    }

    // 思路是找到最高的，最高的可能出现不止一次，最高的两侧按照当前较小值
    public int trap1(int[] height) {
        if (height.length == 0) return 0;

        int maxl = 0;
        int maxr = 0;

        int total = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > height[maxl]) maxl = i;
            if (height[i] >= height[maxr]) maxr = i;
            total += height[i];
        }

        int missing = 0;
        int ll = 0;
        int liml = 0;
        int lr = height.length - 1;
        int limr = 0;

        while (ll < maxl || lr > maxr) {
            if (ll < maxl) {
                liml = Math.max(liml, height[ll]);
                if (height[maxl] > liml) missing = missing + (height[maxl] - liml);
                ll++;
            }

            if (lr > maxr) {
                limr = Math.max(limr, height[lr]);
                if (height[maxr] > limr) missing = missing + (height[maxr] - limr);
                lr--;
            }
        }
        return height[maxl] * height.length - total - missing;
    }

    public int trap2(int[] height) {
        int l = 0;
        int r = height.length - 1;

        int maxl = 0;
        int maxr = 0;
        int ans = 0;

        while (l < r) {
            if (height[l] < height[r]) {
                maxl = Math.max(maxl, height[l]);
                ans += maxl - height[l];
                l++;
            } else {
                maxr = Math.max(maxr, height[r]);
                ans += maxr - height[r];
                r--;
            }
        }

        return ans;
    }

    //
    @Test
    public void No45() {
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {1, 2, 3};
        System.out.println(jump(nums1));
        System.out.println(jump(nums2));
    }

    public int jump(int[] nums) {
        int l = nums.length;
        if (l < 3) return l - 1;

        int time = 1;
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
            time++;
        }

        return time;
    }

    //
    @Test
    public void No48() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate0(matrix);
        rotate1(matrix);
    }

    public void rotate0(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    // 解析里面有个偏向于数学的思路, 挺有意思的, 简单来说, 一次矩阵转置外加一次行或列翻转相当于旋转90度
    // 实现起来, 在代码层面更容易实现
    // 虽然两个方向的转置+翻转都能得到结果，但在代码层面， 确实是 \ 方向的转置比 / 更容易得到相应的i j，所以优秀的解法总是清晰易懂的
    public void rotate1(int[][] matrix) {
        int n = matrix.length - 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j];
                matrix[i][n - j] = temp;
            }
        }
    }

    /**
     * [ 55. 跳跃游戏 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/jump-game/ )
     */
    @Test
    public void No55() {
        int[] nums = {1, 2, 3};
        System.out.println(canJump0(nums));
        System.out.println(canJump1(nums));
    }

    // 终极慢速方法
    public boolean canJump0(int[] nums) {
        return check(0, nums);
    }

    public boolean check(int start, int[] nums) {
        int l = nums.length;
        boolean flag = false;
        if (nums[start] + start >= l - 1 || start == l - 1) {
            return true;
        } else if (nums[start] == 0) {
            return false;
        } else {
            for (int i = start + nums[start]; i > start; i--) {
                flag = flag || check(i, nums);
                if (!flag) nums[i] = 0;
            }
        }

        return flag;
    }


    // 还是不够贪心，把问题想的太复杂了，
    public boolean canJump1(int[] nums) {
        int l = nums.length;
        int max = 0;
        for (int i = 0; i < l; i++) {
            if (max < i) return false;
            if (max > l - 1) return true;
            max = Math.max(max, i + nums[i]);
        }

        return true;
    }

    /**
     * [ 56. 合并区间 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/merge-intervals/ )
     */
    @Test
    public void No56() {
        int[][] intervals = {
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        };
        intervals = merge(intervals);
        for (int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }


    }

    // 可以先进行排序, 快速排序，然后再进行合并
    // 合并时有个关键的技巧，并不是只有while循环可以处理无限的合并，还有栈
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return intervals;
        if (intervals[0].length == 0) return intervals;

        sort(intervals, 0, intervals.length - 1);
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int size = result.size();
            if (intervals[i][0] <= result.get(size - 1)[1]) {
                intervals[i][0] = result.get(size - 1)[0];
                intervals[i][1] = Math.max(result.get(size - 1)[1], intervals[i][1]);
                result.set(size - 1, intervals[i]);
            } else {
                result.add(intervals[i]);
            }
        }

        int[][] xxx = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            xxx[i] = result.get(i);
        }

        return xxx;
    }

    public void sort(int[][] intervals, int start, int end) {
        if (start >= end) return;

        int tail = end;
        for (int i = start + 1; i <= tail; i++) {
            int t0 = intervals[i][0];
            int t1 = intervals[i][1];
            if (intervals[i][0] < intervals[i - 1][0]) {
                intervals[i][0] = intervals[i - 1][0];
                intervals[i][1] = intervals[i - 1][1];
                intervals[i - 1][0] = t0;
                intervals[i - 1][1] = t1;
            } else {
                intervals[i][0] = intervals[tail][0];
                intervals[i][1] = intervals[tail][1];
                intervals[tail][0] = t0;
                intervals[tail][1] = t1;
                tail--;
                i--;
            }
        }

        sort(intervals, start, tail);
        sort(intervals, tail + 1, end);
    }


    /**
     * [ 57. 插入区间 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/insert-interval/ )
     * 两种方法没有本质区别，第二种方法思路更清晰一些，因为是有序、不重叠区间，所以其他的区间不需要考虑合并问题
     * 只有 newInterval 插入后才需要考虑合并问题，所以一共分为三个阶段
     */
    @Test
    public void No57() {

    }

    public int[][] insert1(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0 && newInterval.length == 2) {
            int[][] result = new int[1][2];
            result[0] = newInterval;
            return result;
        } else if (intervals.length == 0 && newInterval.length == 0) {
            int[][] result = new int[1][2];
            return result;
        }
        boolean flag = true;
        int i = 0;
        List<int[]> stack = new ArrayList<>();
        if (newInterval[0] <= intervals[0][0]) {
            stack.add(newInterval);
            flag = false;
        } else {
            stack.add(intervals[0]);
            i++;
        }


        for (; i <= intervals.length; i++) {
            int size = stack.size() - 1;

            if (flag && ((i == intervals.length) || (newInterval[0] >= intervals[i - 1][0] && newInterval[0] <= intervals[i][0]))) {
                if (newInterval[0] <= stack.get(size)[1]) {
                    newInterval[0] = Math.min(newInterval[0], stack.get(size)[0]);
                    newInterval[1] = Math.max(newInterval[1], stack.get(size)[1]);
                    stack.set(size, newInterval);
                } else {
                    stack.add(newInterval);
                    size++;
                }
                flag = false;
            }
            if (i < intervals.length) {
                if (intervals[i][0] <= stack.get(size)[1]) {
                    intervals[i][0] = Math.min(intervals[i][0], stack.get(size)[0]);
                    intervals[i][1] = Math.max(intervals[i][1], stack.get(size)[1]);
                    stack.set(size, intervals[i]);
                } else {
                    stack.add(intervals[i]);
                }
            }
        }

        return stack.toArray(new int[stack.size()][2]);
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {
        List<int[]> stack = new ArrayList<>();
        int l = intervals.length;
        int i = 0;

        while (i < l && intervals[i][0] < newInterval[0]) {
            stack.add(intervals[i++]);
        }

        if (stack.isEmpty() || newInterval[0] > intervals[i - 1][1]) {
            stack.add(newInterval);
        } else {
            intervals[i - 1][1] = Math.max(intervals[i - 1][1], newInterval[1]);
            stack.set(stack.size() - 1, intervals[i - 1]);
        }

        for (; i < l; i++) {
            int size = stack.size() - 1;
            if (intervals[i][0] <= stack.get(size)[1]) {
                intervals[i][0] = Math.min(intervals[i][0], stack.get(size)[0]);
                intervals[i][1] = Math.max(intervals[i][1], stack.get(size)[1]);
                stack.set(size, intervals[i]);
            } else {
                stack.add(intervals[i]);
            }
        }

        return stack.toArray(new int[stack.size()][2]);
    }


    /**
     * [ 59. 螺旋矩阵 II - 力扣（LeetCode） ](https://leetcode-cn.com/problems/spiral-matrix-ii/ )
     */
    @Test
    public void No59() {

    }

    // 矩阵的遍历
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n / 2; i++) {
            int loc = 1;
            if (i != 0) loc = matrix[i][i - 1] - i + 1;
            for (int j = i; j < n - 1 - i; j++) {
                int step = n - 1 - i * 2;
                matrix[i][j] = loc + j;
                matrix[j][n - 1 - i] = loc + 1 * step + j;
                matrix[n - 1 - i][n - 1 - j] = loc + 2 * step + j;
                matrix[n - 1 - j][i] = loc + 3 * step + j;
            }
        }

        if (n % 2 != 0) matrix[n / 2][n / 2] = n * n;

        return matrix;
    }

    /**
     * [ 62. 不同路径 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/unique-paths/ )
     */
    @Test
    public void No62() {

    }

    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        if (m == 2) return n;
        if (n == 2) return m;
        if (m == n) return 2 * uniquePaths(m - 1, n);
        else return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    public int uniquePaths1(int m, int n) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 1;
                } else if (i == 1) {
                    matrix[i][j] = j + 1;
                } else if (j == 1) {
                    matrix[i][j] = i + 1;
                } else {
                    matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
                }
            }
        }
        return matrix[m - 1][n - 1];
    }


    /**
     * [ 63. 不同路径 II - 力扣（LeetCode） ](https://leetcode-cn.com/problems/unique-paths-ii/ )
     */
    @Test
    public void No63() {

    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        if(m==0) return 0;
        int n=obstacleGrid[0].length;
        if(n==0) return 0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(obstacleGrid[i][j]!=1){
                    if(i==0&&j==0){
                        obstacleGrid[i][j]=1;
                    }else if(i==0&&j!=0){
                        obstacleGrid[i][j]=obstacleGrid[i][j-1];
                    }else if(i!=0&&j==0){
                        obstacleGrid[i][j]=obstacleGrid[i-1][j];
                    }else{
                        if(obstacleGrid[i-1][j]!=-1&&obstacleGrid[i][j-1]!=-1){
                            obstacleGrid[i][j]=obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
                        }else if(obstacleGrid[i-1][j]==-1&&obstacleGrid[i][j-1]==-1){
                            obstacleGrid[i][j]=-1;
                        }else if(obstacleGrid[i-1][j]==-1){
                            obstacleGrid[i][j]=obstacleGrid[i][j-1];;
                        }else{
                            obstacleGrid[i][j]=obstacleGrid[i-1][j];
                        }
                    }
                }else{
                    obstacleGrid[i][j]=-1;
                }
            }
        }

        return obstacleGrid[m-1][n-1]==-1?0:obstacleGrid[m-1][n-1];
    }

    /**
     * [ 64. 最小路径和 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/minimum-path-sum/ )
     */
    @Test
    public void No64() {

    }

    public int minPathSum(int[][] grid) {
        int m=grid.length;
        if(m==0)return 0;
        int n=grid[0].length;
        if(n==0) return 0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i!=0&&j!=0){
                    grid[i][j]+=Math.min(grid[i-1][j],grid[i][j-1]);
                }else if(i==0&&j!=0){
                    grid[i][j]+=grid[i][j-1];
                }else if(i!=0&&j==0){
                    grid[i][j]+=grid[i-1][j];
                }
            }
        }

        return grid[m-1][n-1];
    }

    /**
     * [ 73. 矩阵置零 - 力扣（LeetCode） ](https://leetcode-cn.com/problems/set-matrix-zeroes/ )
     */
    @Test
    public void No73() {

    }

    // 尝试一下，使用了 m+n 的空间，时间复杂度为 O(m*n)+O(m+n)*(log_m+n)
    public void setZeroes(int[][] matrix) {
        int m=matrix.length;
        if(m==0) return;
        int n=matrix[0].length;
        if(n==0) return;

        int[] index=new int[m+n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0) {
                    index[i%m]=1;
                    index[j%n+m]=1;
                }
            }
        }

        for(int i=0;i<m+n;i++){
            if(index[i]==1){
                if(i<m) {
                    for(int j=0;j<n;j++){
                        matrix[i][j]=0;
                    }
                }else{
                    for(int j=0;j<m;j++){
                        matrix[j][i-m]=0;
                    }
                }
            }
        }
    }

    /**
     *
     */
    @Test
    public void No74() {

    }

    /**
     *
     */
    @Test
    public void No75() {

    }

    /**
     *
     */
    @Test
    public void No78() {

    }
}