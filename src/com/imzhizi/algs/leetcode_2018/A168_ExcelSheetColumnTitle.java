package com.imzhizi.algs.leetcode_2018;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * For example:
 *  1 -> A
 *  2 -> B
 *  ...
 *  26 -> Z
 *  27 -> AA
 *
 * Example 1:
 *  Input: 1
 *  Output: "A"
 *
 * Example 2:
 *  Input: 701
 *  Output: "ZY"
 * created by zhizi on 2018/7/16
 */
public class A168_ExcelSheetColumnTitle {
    /**
     * 题目分析：
     * 本来以为会是导入 Excel 文件什么的复杂题目，结果其实是一道进制转换题目，相当于是求 n 的26进制表示
     * 仔细做了之后发现并不是简单地进制转换，因为不存在 0 的缘故，每次需要进位的时候都需要4下借一位，如 52 就不是 B0，而是 AZ
     * 使用了 nums[] 保存每一位的结果，然后通过 char 得到结果
     *
     * 运行时长：0ms
     *
     * 总结：
     * 遇到这种题还是要仔细地分析题目，找到根本点，如果不加思考地暴力解决，往往浪费很多时间
     */
    public static String convertToTitle(int n) {
        int[] nums = new int[10];
        int i=0;
        while(n>26){
            nums[i]+=n%26;
            if(nums[i]==0){
                nums[i]=26;
                nums[i+1]--;
            }
            i++;
            n=n/26;
        }
        nums[i]+=n;
        String title = "";
        for (int j = 0; nums[j] != 0; j++) {
            title = (char) (nums[j] + 64) + title;
        }
        return title;
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(26));
        System.out.println(convertToTitle(27));
        System.out.println(convertToTitle(52));
        System.out.println(convertToTitle(701));
    }
}
