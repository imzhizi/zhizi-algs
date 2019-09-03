package com.imzhizi.algs.剑指offer;

import com.imzhizi.algs.ListNode;
import com.imzhizi.algs.TreeNode;
import org.junit.Test;

import java.util.*;

public class Part2 {
    /**
     * [表示数值的字符串_牛客网]( https://www.nowcoder.com/practice/6f8c901d091949a5837e24bb82a731f2 )
     */
    @Test
    public void No20() {

    }

    public boolean isNumeric(char[] str) {

        boolean div = false;
        boolean sci = false;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'e' || str[i] == 'E') {
                if (i == str.length - 1) return false;
                else sci = true;
            } else if (str[i] == '.') {
                if (div) {
                    return false;
                } else {
                    if (sci) return false;
                    else div = true;
                }
            } else if (str[i] == '+' || str[i] == '-') {
                if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E') return false;
            } else if (str[i] < '0' || str[i] > '9') {
                return false;
            }
        }

        return true;
    }


    /**
     * 重排列，使得奇数位于偶数前面
     */
    @Test
    public void No21() {
        int[] pres = new int[]{1, 2, 4, 5, 3, 6, 7};
        reOrderArray(pres);
        System.out.println(Arrays.toString(pres));
    }

    public void reOrderArray(int[] array) {
        // 感觉只能先搞个临时数组存偶数, 然后奇数覆盖式保存
        // 最后把偶数存回去
        int[] even = new int[array.length];
        int[] odd = new int[array.length];
        int e = 0;
        int o = 0;

        for (int i : array) {
            if (i % 2 != 0) {
                odd[o++] = i;
            } else {
                even[e++] = i;
            }
        }

        System.arraycopy(odd, 0, array, 0, o);
        System.arraycopy(even, 0, array, o, e);
    }

    @Test
//        复杂链表的复制
    public void No22() {
    }

    @Test
    //
    public void No23() {
        System.out.println(EntryNodeOfLoop(new ListNode(1)).val);
    }

    // 18ms
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        List<ListNode> list = new ArrayList<>();

        while (pHead != null) {
            for (ListNode node : list) {
                if (node.val == pHead.val) {
                    return pHead;
                }
            }
            list.add(pHead);
            pHead = pHead.next;
        }

        return null;
    }

    // 使用HashSet优化了一丢丢
    public ListNode EntryNodeOfLoop2(ListNode pHead)
    {
        HashSet<Integer> set=new HashSet<>();

        while(pHead!=null){
            if(set.contains(pHead.val)){
                return pHead;
            }
            set.add(pHead.val);
            pHead=pHead.next;
        }

        return null;
    }


    /**
     * [数组中重复的数字_牛客网]( https://www.nowcoder.com/practice/623a5ac0ea5b4e5f95552655361ae0a8 )
     */
    @Test
    public void No23_2() {
        int[] duplication = new int[]{-1};
        System.out.println(duplicate(new int[]{}, 0, duplication));
        System.out.println(duplication[0]);
    }

    public boolean duplicate(int[] numbers, int length, int[] duplication) {
        boolean[] k = new boolean[length];
        for (int i = 0; i < k.length; i++) {
            if (k[numbers[i]]) {
                duplication[0] = numbers[i];
                return true;
            }
            k[numbers[i]] = true;
        }
        return false;
    }

    @Test
//        字符串的排列
    public void No24() {
        char[] str = new char[10];
        String s = String.valueOf(str);
        char[] newStr = str.clone();
    }

    @Test
// 数组中出现次数超过一半的数字
    public void No25() {
    }

    public int MoreThanHalfNum_Solution(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
            if (map.get(array[i]) > length / 2) {
                return array[i];
            }
        }
        return 0;
    }


    @Test
    // 最小的K个数
    public void No26() {
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (k > input.length) return result;

        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] > input[j]) {
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
            result.add(input[i]);
        }
        return result;
    }


    @Test
    // 最大连续子串和
    public void No27() {
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        int[] result = new int[array.length];
        result[0] = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (result[i - 1] > 0) {
                result[i] = array[i] + result[i - 1];
            } else {
                result[i] = array[i];
            }
            if (result[i] > max) max = result[i];
        }

        return max;
    }
}