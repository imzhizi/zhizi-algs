package com.imzhizi.algs.leetcode.imooc;

import org.junit.Test;

import java.util.*;

/**
 * created by zhizi
 * on 5/2/20 08:24
 * 查找问题
 */
public class IMooC2 {
    /**
     * [349. 两个数组的交集 - 力扣（LeetCode）](https://leetcode-cn.com/problems/intersection-of-two-arrays/ )
     */
    @Test
    public void Q349() {

    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int n : nums1)
            set.add(n);

        for (int n : nums2) {
            if (set.contains(n)) {
                result.add(n);
            }
        }

        int[] ret = new int[result.size()];
        int i = 0;
        for (int n : result) ret[i++] = n;

        return ret;
    }

    /**
     * [350. 两个数组的交集 II - 力扣（LeetCode）](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/ )
     */
    @Test
    public void Q350() {

    }

    // 排序时间复杂度为 nlogn + mlogm
    // 统计的时间复杂度为 (m,n)log(m,n)
    // 输出结果 (m,n)
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> result = new ArrayList<>();

        int i1 = 0, i2 = 0;
        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] == nums2[i2]) {
                result.add(nums1[i1]);
                i1++;
                i2++;
            } else if (nums1[i1] > nums2[i2]) {
                i2++;
            } else {
                i1++;
            }
        }

        int[] ret = new int[result.size()];
        for (int i = 0; i < result.size(); i++) ret[i] = result.get(i);
        return ret;
    }

    // 时间复杂度 m+n
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(Math.min(nums1.length, nums2.length));
        List<Integer> result = new ArrayList<>(Math.min(nums1.length, nums2.length));
        for (int n : nums1) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }

        for (int n : nums2) {
            if (map.containsKey(n)) {
                result.add(n);

                if (map.get(n) == 1) map.remove(n);
                else map.put(n, map.get(n) - 1);
            }
        }

        int[] ret = new int[result.size()];
        for (int i = 0; i < result.size(); i++) ret[i] = result.get(i);
        return ret;
    }


    /**
     * [242. 有效的字母异位词 - 力扣（LeetCode）](https://leetcode-cn.com/problems/valid-anagram/ )
     * 还是利用了哈希的思路，O(n)的时间复杂度，但评价不高
     */
    @Test
    public void Q242() {

    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] countS = new int[26];
        int[] countT = new int[26];
        for (int i = 0; i < s.length(); i++) {
            countS[s.charAt(i) - 'a']++;
            countT[t.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (countS[i] != countT[i]) {
                return false;
            }
        }

        return true;
    }

    // 可以只用一个数组来监控
    // 解约那么一丢丢内存
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * [202. 快乐数 - 力扣（LeetCode）](https://leetcode-cn.com/problems/happy-number/ )
     */
    @Test
    public void Q202() {
    }

    static Set<Integer> set = new HashSet<>();

    public boolean isHappy(int n) {
        if (n == 1) {
            set = new HashSet<>();
            return true;
        }

        if (set.contains(n)) return false;
        else set.add(n);

        int sum = 0;
        while (n > 0) {
            int mod = n % 10;
            sum += mod * mod;
            n = n / 10;
        }
        return isHappy(sum);
    }

    /**
     * [290. 单词规律 - 力扣（LeetCode）](https://leetcode-cn.com/problems/word-pattern/ )
     */
    @Test
    public void Q290() {

    }

    // 想用 String 的 intern() 来降低比较的成本
    // 没想到 intern() 的成本非常高，效率很低，和采用equals比较一样慢
    public boolean wordPattern(String pattern, String str) {
        String[] group = str.split(" ");
        if (pattern.length() != group.length) return false;

        for (int i = 0; i < group.length; i++) {
            group[i] = group[i].intern();
        }

        for (int i = 0; i < pattern.length(); i++) {
            for (int j = i + 1; j < pattern.length(); j++) {
                if (pattern.charAt(i) == pattern.charAt(j)) {
                    if (!group[i].equals(group[j])) return false;
                } else {
                    if (group[i].equals(group[j])) return false;
                }
            }
        }

        return true;
    }

    public boolean wordPattern2(String pattern, String str) {
        String[] group = str.split(" ");
        if (pattern.length() != group.length) return false;

        for (int i = 0; i < pattern.length(); i++) {
            for (int j = i + 1; j < pattern.length(); j++) {
                if (pattern.charAt(i) == pattern.charAt(j)) {
                    if (!group[i].equals(group[j])) return false;
                } else {
                    if (group[i].equals(group[j])) return false;
                }
            }
        }
        return true;
    }

    // 其实非常粗暴的哈希做法，100%beat
    // 通过哈希把两边都转换成了最朴实的格式, 然后直接比较
    public boolean wordPattern3(String pattern, String str) {
        String[] strs = str.split(" ");
        int length = strs.length;
        if (pattern.length() != length) return false;

        Map<String, Integer> map = new HashMap<>();
        int[] exp = new int[length];
        int countS = 1;

        for (int i = 0; i < length; i++) {
            if (!map.containsKey(strs[i])) {
                map.put(strs[i], countS++);
            }
            exp[i] = map.get(strs[i]);
        }

        int[] chars = new int[26];
        int[] stats = new int[length];
        int countP = 1;

        for (int i = 0; i < length; i++) {
            int c = pattern.charAt(i) - 'a';
            if (chars[c] == 0) {
                chars[c] = countP++;
            }
            stats[i] = chars[c];
        }

        for (int i = 0; i < length; i++) {
            if (exp[i] != stats[i]) return false;
        }

        return true;
    }

    /**
     * [205. 同构字符串 - 力扣（LeetCode）](https://leetcode-cn.com/problems/isomorphic-strings/ )
     * 又是一道非常重型的哈希题目，使用两个map来维持映射关系，如果后继的对应关系和之前的不符，说明不是同构
     */
    @Test
    public void Q205() {

    }

    // 仅仅 beat 18 %
    // 如何优化呢？时间复杂度是对字符串的遍历 O(n)
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> mapS = new HashMap<>(s.length());
        Map<Character, Character> mapT = new HashMap<>(s.length());

        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if (mapS.containsKey(cs) && mapT.containsKey(ct)) {
                if (mapS.get(cs) != ct || mapT.get(ct) != cs) {
                    return false;
                }
            } else if (!mapS.containsKey(cs) && !mapT.containsKey(ct)) {
                mapS.put(cs, ct);
                mapT.put(ct, cs);
            } else {
                return false;
            }
        }

        return true;
    }

    // 同样的思路，改用数组实现哈希之后快很多，可能是维护HashMap的成本太高？
    public boolean isIsomorphic2(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] mapS = new char[256];
        char[] mapT = new char[256];

        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if (mapS[cs] != 0 && mapT[ct] != 0) {
                if (mapS[cs] != ct || mapT[ct] != cs) {
                    return false;
                }
            } else if (mapS[cs] == 0 && mapT[ct] == 0) {
                mapS[cs] = ct;
                mapT[ct] = cs;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * [451. 根据字符出现频率排序 - 力扣（LeetCode）](https://leetcode-cn.com/problems/sort-characters-by-frequency/ )
     * 一些内容需要明确
     * 看起来字符集似乎是大小写字母, 同时大小写敏感
     * 遇到过几次 key,score 结构, 根据score排序的题目
     * 事实上字符集是全体ASCII字符
     */
    @Test
    public void Q451() {
        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("eeeee"));
    }

    // 对于这种问题，唯一的思路就是构造类
    static class Node {
        char c;
        int v;

        public Node(char c, int v) {
            this.c = c;
            this.v = v;
        }
    }

    // 还行，beat 87%
    // 这种占用内存非常多，一种可行的思路是先用普通数组统计，统计结束后再转换成对象排序
    public String frequencySort(String s) {
        Node[] nodes = new Node[256];
        for (int i = 0; i < s.length(); i++) {
            int loc = s.charAt(i);
            if (nodes[loc] == null) {
                nodes[loc] = new Node(s.charAt(i), 1);
            } else {
                nodes[loc].v++;
            }
        }

        Arrays.sort(nodes, (n1, n2) -> {
            if (n1 == null && n2 == null) return 0;
            if (n1 == null) return 1;
            if (n2 == null) return -1;
            return n2.v - n1.v;
        });

        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (nodes[index] != null) {
            while (nodes[index].v > 0) {
                sb.append(nodes[index].c);
                nodes[index].v--;
            }
            index++;
        }

        return sb.toString();
    }

    /**
     * [1. 两数之和 - 力扣（LeetCode）](https://leetcode-cn.com/problems/two-sum/ )
     * 可以使用对撞指针求解，也可以利用哈希(有点特殊处理)
     */
    @Test
    public void 和问题() {

    }

    /**
     * [15. 三数之和 - 力扣（LeetCode）](https://leetcode-cn.com/problems/3sum/ )
     * 思路还是固定一个点，将剩下两个点转换成两数之和问题
     * 排序时间复杂度为O(nlogn)
     * 对撞指针两数求和的时间复杂度为O(n), 元素的遍历之后进行对撞是O(n*(n-2))，所以时间复杂度为O(n^2)
     */
    @Test
    public void Q15() {

    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int head = i + 1, tail = nums.length - 1;
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            while (head < tail) {
                if (nums[i] + nums[head] + nums[tail] == 0) {
                    if (ret.isEmpty() || (!ret.get(ret.size() - 1).get(1).equals(nums[head]) || !ret.get(ret.size() - 1).get(2).equals(nums[tail]))) {
                        List<Integer> temp = new ArrayList<>(list);
                        temp.add(nums[head]);
                        temp.add(nums[tail]);
                        ret.add(temp);
                    }
                    head++;
                    tail--;
                } else if (nums[i] + nums[head] + nums[tail] > 0) {
                    tail--;
                } else {
                    head++;
                }
            }
        }

        return ret;
    }

    /**
     * [16. 最接近的三数之和 - 力扣（LeetCode）](https://leetcode-cn.com/problems/3sum-closest/ )
     */
    @Test
    public void Q16() {

    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
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

    /**
     * [18. 四数之和 - 力扣（LeetCode）](https://leetcode-cn.com/problems/4sum/ )
     * 四数之和，难道是双对撞指针吗？
     */
    @Test
    public void Q18() {

    }

    // O(n^3)
    // 不是很快
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for (int ll = 0; ll < nums.length - 3; ll++) {
            if (ll > 0 && nums[ll] == nums[ll - 1]) continue;
            for (int rr = nums.length - 1; rr > ll + 2; rr--) {
                if (rr < nums.length - 1 && nums[rr] == nums[rr + 1]) continue;

                List<Integer> list = new ArrayList<>();
                list.add(nums[ll]);
                list.add(nums[rr]);

                int left = ll + 1;
                int right = rr - 1;

                while (left < right) {
                    if (nums[ll] + nums[rr] + nums[left] + nums[right] == target) {
                        List<Integer> temp = new ArrayList<>(list);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        ret.add(temp);
                        left++;
                        right--;
                        while (nums[left] == nums[left - 1] && left < right) left++;
                        while (nums[right] == nums[right + 1] && left < right) right--;
                    } else if (nums[ll] + nums[rr] + nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }

            }
        }
        return ret;
    }

    /**
     * [454. 四数相加 II - 力扣（LeetCode）](https://leetcode-cn.com/problems/4sum-ii/ )
     * O(n^2)
     */
    @Test
    public void Q454() {

    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : C) {
            for (int i : D) {
                int sum = value + i;
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }

        int count = 0;
        for (int value : A) {
            for (int i : B) {
                int sum = -(value + i);
                if (map.containsKey(sum)) {
                    count += map.get(sum);
                }
            }
        }

        return count;
    }

    /**
     * [49. 字母异位词分组 - 力扣（LeetCode）](https://leetcode-cn.com/problems/group-anagrams/ )
     */
    @Test
    public void Q49() {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String s = correctOrder(str);
            if (map.containsKey(s)) {
                map.get(s).add(str);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(str);
                map.put(s, temp);
            }
        }

        return new ArrayList<>(map.values());
//        // 虽然以下方法比较繁琐，但在leetcode上实测性能更好，beat 98%
//        List<List<String>> ret=new ArrayList<>();
//        for(List<String> list:map.values())ret.add(list);
//        return ret;
    }

    public String correctOrder(String str) {
        char[] cs = str.toCharArray();
        Arrays.sort(cs);
        return new String(cs);
    }

    /**
     * [447. 回旋镖的数量 - 力扣（LeetCode）](https://leetcode-cn.com/problems/number-of-boomerangs/ )
     */
    @Test
    public void Q447() {

    }

    // 小优化也是很有意义的，不要忽略小优化
    // 95% beat
    public int numberOfBoomerangs(int[][] points) {
        int total = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    int length = getLength(points[i][0], points[i][1], points[j][0], points[j][1]);
                    if (map.containsKey(length)) {
                        total += map.get(length) * 2;
                        map.put(length, map.get(length) + 1);
                    } else {
                        map.put(length, 1);
                    }
                }
            }
        }

        return total;
    }

    // 思路就是对其他结点到一个结点的距离进行统计，距离相同的放一组
    // 非常一般的实现，25% beat
    // 时间复杂度应该是O(n^2), 空间复杂度为n*Map
    public int numberOfBoomerangs2(int[][] points) {
        int total = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    int length = getLength(points[i][0], points[i][1], points[j][0], points[j][1]);
                    if (map.containsKey(length)) {
                        map.put(length, map.get(length) + 1);
                    } else {
                        map.put(length, 1);
                    }
                }
            }

            for (Integer count : map.values()) {
                if (count > 1) total += count * (count - 1);
            }
        }

        return total;
    }

    public int getLength(int x1, int y1, int x2, int y2) {
        int x = x1 - x2;
        int y = y1 - y2;
        return x * x + y * y;
    }

    /**
     * [219. 存在重复元素 II - 力扣（LeetCode）](https://leetcode-cn.com/problems/contains-duplicate-ii/ )
     * 滑动窗口+哈希表
     */
    @Test
    public void Q219() {
    }

    // 精简优化版
    // 如果窗口的长度是一个固定值，那么其实一个
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < nums.length; right++) {
            if (map.put(nums[right], right) != null) return true;
            if (map.size() > k) map.remove(nums[right - k]);
        }
        return false;
    }

    //这种题有一个特征就是使用哈希表的同时需要使用窗口下界对哈希值的有效性进行验证
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 1, right = 0;
        for (; right <= k && right < nums.length; right++) {
            if (map.put(nums[right], right) != null) return true;
        }

        while (right < nums.length) {
            if (map.containsKey(nums[right]) && map.get(nums[right]) >= left) {
                return true;
            }
            map.put(nums[right], right);
            right++;
            left++;
        }
        return false;
    }

    /**
     * [220. 存在重复元素 III - 力扣（LeetCode）](https://leetcode-cn.com/problems/contains-duplicate-iii/ )
     */
    @Test
    public void Q220() {

    }

    // 6% beat, 无敌慢
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int target, length = nums.length;
        for (int left = 0, right = k < length ? k : length - 1; left < length - 1; left++, right++) {
            target = left + 1;
            while (target <= right && target < length) {
                if (Math.abs((long) nums[left] - nums[target++]) <= t) return true;
            }
        }
        return false;
    }

    // 更典型的滑动窗口
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int target, length = nums.length;
        for (int left = 0, right = 1; right < length; right++) {
            if (right > k) left++;
            target = left;
            while (target < right) {
                if (Math.abs((long) nums[right] - nums[target++]) <= t) return true;
            }
        }
        return false;
    }

}
