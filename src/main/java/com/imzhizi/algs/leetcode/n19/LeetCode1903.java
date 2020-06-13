package com.imzhizi.algs.leetcode.n19;

import com.imzhizi.algs.common.ListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * created by zhizi on 2019-03-04
 */
public class LeetCode1903 {
    /**
     * 题目：[ Remove Nth Node From End of List - LeetCode ]( https://leetcode.com/problems/remove-nth-node-from-end-of-list/ )
     *
     * 分析：
     *
     * 总结：
     */

    /* 思路一：5ms - 100% - */
    @Test
    public void test19() {
        Assert.assertEquals(null, removeNthFromEnd(new ListNode(1), 1));
    }

    ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode move = node;
        ListNode result=node;

        for (int i = 0; i < n; i++) {
            move = move.next;
        }

        while (null != move.next) {
            move = move.next;
            node = node.next;
        }

        node.next = node.next.next;

        return result.next;
    }

    /**
     * 题目：[ Unique Email Addresses - LeetCode ]( https://leetcode.com/problems/unique-email-addresses/ )
     *
     * 分析：
     *
     * 总结：
     */

    /* 思路一：10ms - 99% - */
    int numUniqueEmails(String[] emails) {
        HashSet<String> emailSet = new HashSet<>();

        for (int i = 0; i < emails.length; i++) {

            StringBuilder email=new StringBuilder(emails[i]);

            int at=email.indexOf("@");
            int plus=email.indexOf("+");
            int point=email.indexOf(".");
            if (plus>-1&&plus<at){
                email.delete(plus,at);
                at=email.indexOf("@");
            }

            while (point>-1&&point<at){
                email.delete(point,point+1);
                point=email.indexOf(".");
                at=email.indexOf("@");
            }

            emailSet.add(email.toString());

        }

        return emailSet.size();
    }


    @Test
    public void test929() {
        String[] emails=new String[]{"test.email+alex@LeetCode.com","test.e.mail+bob.cathy@LeetCode.com","testemail+david@lee.tcode.com"};

        System.out.println(numUniqueEmails(emails));
    }


    /**
     * 题目：[ Groups of Special-Equivalent Strings - LeetCode ]( https://leetcode.com/problems/groups-of-special-equivalent-strings/ )
     *
     * 分析：这道题比较绕，首先是字符串是否equivalent的判定，其次是过滤掉不同但却equivalent的字符串
     */

    /* 思路一：168ms - 5% - */
    // 第一种思路非常朴实，首先一个函数判断是否equivalent，然后从字符串数组中依次取出相比较，只要不equivalent则放入HashSet
    private int numSpecialEquivGroups(String[] A) {
        Set<String> strs = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (!equivalent(A[i], A[j])) {
                    strs.add(A[i]);
                }else {
                    A[j]=A[i];
                }
            }
        }
        strs.add(A[A.length-1]);

        return strs.size();
    }


    private boolean equivalent(String A, String B) {
        int[] even = new int[26];
        int[] odd = new int[26];

        for (int i = 0; i < A.length(); i++) {
            if (i % 2 != 0) {
                odd[A.charAt(i) - 'a']++;
                odd[B.charAt(i) - 'a']--;
            } else {
                even[A.charAt(i) - 'a']++;
                even[B.charAt(i) - 'a']--;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (odd[i] != 0 || even[i] != 0) return false;
        }
        return true;
    }

    /* 思路二：13ms - 60% - */
    // 思路二比较特别，但也很容易理解，是把不同的字符串全部进行了翻译（26位的int数组），转化成了统一的字符串，这是再添加到 HashSet 中
    private int numSpecialEquivGroups2(String[] A) {
        Set<String> set= new HashSet<>();
        for (String s: A){
            int[] odd= new int[26];
            int[] even= new int[26];
            for (int i=0; i<s.length(); i++){
                if (i%2==1) odd[s.charAt(i)-'a']++;
                else even[s.charAt(i)-'a']++;
            }
            set.add(Arrays.toString(odd) + Arrays.toString(even));
        }
        return set.size();
    }

    // todo waiting for best solution
    private int numSpecialEquivGroups3(String[] A) {
       return 0;
    }


        @Test
    public void test893() {
        String[] strs1=new String[]{"a","b","c","a","c","c"};
        String[] strs2=new String[]{"abc","acb","bac","bca","cab","cba"};
        String[] strs3=new String[]{"aa","bb","ab","ba"};
        System.out.println(numSpecialEquivGroups(strs1));
        System.out.println(numSpecialEquivGroups(strs2));
        System.out.println(numSpecialEquivGroups(strs3));
        System.out.println(numSpecialEquivGroups2(strs1));
        System.out.println(numSpecialEquivGroups2(strs2));
        System.out.println(numSpecialEquivGroups2(strs3));
    }



    /**
     * 题目：[ Rotated Digits - LeetCode ]( https://leetcode.com/problems/rotated-digits/ )
     *
     * 分析：必须含有2、5、6、9，不能含有无效数字3、4、7，逐个逐位判断
     *
     * 总结：
     */

    /* 思路一：5ms - 100% - */
    public int rotatedDigits(int N) {
        int good = 0;
        for (int i = 2; i <= N; i++) {
            boolean rotated = false;
            boolean invalid = false;
            int bit;
            int n = i;
            while (n > 1) {
                bit = n % 10;
                n = n / 10;
                switch (bit) {
                    case (2):
                    case (5):
                    case (6):
                    case (9):
                        rotated = true;
                        break;
                    case (3):
                    case (4):
                    case (7):
                        invalid = true;
                        break;
                }
            }
            if (rotated && !invalid) {
                good++;
            }
        }
        return good;
    }

    @Test
    public void test788() {
        System.out.println(rotatedDigits(10));
    }


    /**
     * 题目：
     *
     * 分析：
     *
     * 总结：
     */

    /* 思路一：5ms - 100% - */


    @Test
    public void test() {

    }




}
