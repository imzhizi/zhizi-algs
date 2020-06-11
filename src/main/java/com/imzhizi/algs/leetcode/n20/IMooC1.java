package com.imzhizi.algs.leetcode.n20;

import org.junit.Test;

import java.util.HashMap;

/**
 * created by zhizi
 * on 4/27/20 20:35
 * 数组问题
 */
public class IMooC1 {
    /**
     * [283. 移动零 - 力扣（LeetCode）](https://leetcode-cn.com/problems/move-zeroes/ )
     * 时间复杂度：O(2n)、空间复杂度：O(n)
     */
    @Test
    public void Q283() {

    }

    public void moveZeroes(int[] nums) {
        int[] ret = new int[nums.length];
        int index = 0;
        for (int num : nums) {
            if (num != 0) ret[index++] = num;

        }
        for (int i = 0; i < index; i++) {
            nums[i] = ret[i];
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // 优化的方向，减少空间的使用
    public void moveZeroes2(int[] nums) {
        // 循环不变量，zero 指向0，index用来遍历
        int zero = 0, index = 0;
        while (index < nums.length) {
            if (nums[index] != 0) {
                swap(nums, zero++, index);
            }
            index++;
        }
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 思考，最简单的方法肯定是使用辅助空间，遍历时拷贝到复制空间中，然后再拷贝回来
     * 优化的角度也同样是节省掉辅助空间，交换并不像之前一样容易，因为存在着数据的比较
     */
    @Test
    public void Q80() {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        System.out.println(removeDuplicates(nums));
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    // last 表示上一个有效数字的坐标，last-1 表示上上一个有效数字的坐标
    // 每次符合标准的数据的目标位置应该是 last+1
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int last = 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[last] || (nums[i] == nums[last] && nums[i] != nums[last - 1])) {
                nums[++last] = nums[i];
            }
        }
        return last + 1;
    }

    // 使用了更加复杂的设计，但其实在循环中，last和index表示的是同一个值，flag也没必要专程使用变量记录
    // flag 表示前面两个数字是否相等 ==> nums[i] == nums[last-1]
    // last 表示上一个数字的坐标
    // index 表示移动的标的 last+1
    public int removeDuplicates2(int[] nums) {
        if (nums.length <= 2) return nums.length;
        boolean flag = false;
        int last = 1;
        int index = 2;
        if (nums[0] == nums[1]) flag = true;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[last]) {
                flag = false;
                nums[index] = nums[i];
                last = index;
                index++;
            } else {
                if (!flag) {
                    flag = true;
                    nums[index] = nums[i];
                    last = index;
                    index++;
                }
            }
        }

        return index;
    }

    /**
     * [75. 颜色分类 - 力扣（LeetCode）](https://leetcode-cn.com/problems/sort-colors/ )
     */
    @Test
    public void 计数排序() {
        int[] ints = {2, 0, 2, 1, 1, 0};
        sortColors2(ints);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    public void sortColors(int[] nums) {
        int[] buckets = new int[3];
        for (int n : nums) {
            assert n > 0 && n < 3;
            buckets[n]++;
        }

        int step = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i]; j++) {
                nums[j + step] = i;
            }
            step += buckets[i];
        }
    }

    // 优化思路，消耗的空间已经是常数级的了
    // 时间复杂度为O(2n)，如果再优化，只能向O(n)方向努力
    // 就替论题的话，只有三个元素，那么可以把0和2分别移动到数组的两侧，中间留下的就是1
    // 这其实属于三路快排的思想，适用于数组中重复变量较多的时候
    public void sortColors2(int[] nums) {
        // 循环不变量
        // 我们希望[0,head)的元素均为0
        // 我们希望(tail,nums.length - 1]的元素均为2
        // 所以1的范围为 [head,tail]
        int head = 0, tail = nums.length - 1;
        for (int i = 0; i <= tail; i++) {
            if (nums[i] == 2) {
                swap(nums, i--, tail--);
            } else if (nums[i] == 0) {
                swap(nums, i, head++);
            }
        }
    }

    /**
     * [215. 数组中的第K个最大元素 - 力扣（LeetCode）](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/ )
     * 最基本的思路肯定是先排序，然后直接返回k元素，时间复杂度为 O(nlogn)
     * 优化思路，其实不必将元素全部排序，只要确定前k个元素的顺序即可
     * - 后面的其实就不必再管（但这就要依赖于冒泡排序，这种时间复杂度为多少呢），O(n)+O(n-1)+…+O(n-k)，O((n+n-k)*k/2)，基本上还是O(n^2)
     * - 利用快排的思想，甚至不用确定前k位的元素顺序
     * - 我们平常做的快排都是升序排序，面对降序的需求，还是要保证排序算法的可靠性，也就是说依然使用升序排序会减少错误
     */
    @Test
    public void KthElement() {
        int[] ints = {3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(ints, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSortForK(nums, 0, nums.length - 1, k - 1);
    }

    // 循环不变量
    // 我们要在 [i,tail]中把数据分成两部分
    // 遍历时如果大于pivot就不管它，那么小于pivot就需要放在后面
    // [0,last] 都应该是大于 pivot 的，(last,pivot) 就是小于pivot的
    // 第k大意味着k-1位置上的元素，每次我们确定的元素是last+1
    // 如果last+1==k-1，那说明就是我们要找的元素
    public int quickSortForK(int[] nums, int head, int tail, int k) {
        swap(nums, tail, k);

        int last = tail - 1;
        for (int i = head; i <= last; i++) {
            if (nums[i] < nums[tail]) {
                swap(nums, i--, last--);
            }
        }
        swap(nums, last + 1, tail);

        if (last + 1 == k) {
            return nums[k];
        } else if (last + 1 > k) {
            return quickSortForK(nums, head, last, k);
        } else {
            return quickSortForK(nums, last + 2, tail, k);
        }
    }

    /**
     * 双指针
     * 包括对撞指针和滑动窗口两种类型
     */
    /**
     * [盛最多水的容器 - 提交记录 - 力扣 (LeetCode)](https://leetcode-cn.com/submissions/detail/43964371/ )
     */
    @Test
    public void 对撞指针() {

    }

    /**
     * 滑动窗口首先是双指针，然后始终记录最大值/最小值
     */
    @Test
    public void 滑动窗口() {

    }

    /**
     * [209. 长度最小的子数组 - 力扣（LeetCode）](https://leetcode-cn.com/problems/minimum-size-subarray-sum/ )
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;

        int min = nums.length + 1;
        int left = 0, right = 0;
        int sum = nums[0];
        // 循环不变量
        // left始终指向滑动窗口的左端, right指向右端
        // 如果sum<s,right向右, 如果sum>=s,left向右
        while (right < nums.length && left <= right) {
            if (sum >= s) {
                min = Math.min(min, right - left + 1);
                sum -= nums[left];
                left++;
            } else {
                right++;
                if (right < nums.length) sum += nums[right];
            }
        }

        if (min == nums.length + 1) return 0;
        else return min;
    }

    /**
     * [3. 无重复字符的最长子串 - 力扣（LeetCode）](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/ )
     */
    public int lengthOfLongestSubstring(String s) {
        //循环不变量
        // 使用 (left,right]代表这个子串
        // 遍历过程中, 如果不冲突, right++,如果冲突, left指向冲突位置
        // 是否冲突依靠HashMap, 冲突时更新value
        HashMap<Character, Integer> map = new HashMap<>(s.length());
        int left = -1, right = 0;
        int max = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = map.get(c) <= left ? left : map.get(c);
            }
            max = Math.max(max, right - left);
            map.put(c, right++);
        }

        return max;
    }

    /**
     * [76. 最小覆盖子串 - 力扣（LeetCode）](https://leetcode-cn.com/problems/minimum-window-substring/ )
     * todo 进阶题目
     */
    public String minWindow(String s, String t) {

        return "";
    }
}
