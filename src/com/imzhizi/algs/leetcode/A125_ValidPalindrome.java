package com.imzhizi.algs.leetcode;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 *
 * Example 2:
 * Input: "race a car"
 * Output: false
 * created by zhizi on 2018/6/7
 */
public class A125_ValidPalindrome {
    /**
     * 题目分析：
     * 最开始一直没搞明白题意，一直出现新的符号，除了例子中的“;”、“:”、“.”和“ ”，还有许多别的符号；
     * 所以直接使用了正则表达式进行替换，确实能用，但效率不怎么样。
     *
     * 运行时长：30ms
     *
     * 总结：
     * 其实后来发现\W是指所有非字母、数字的字符，而我只想到了字母，这在方法二里面一度被忽视
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.equals("")) return true;
        s = s.toLowerCase();
        String reg = "\\W";
        s = s.replaceAll(reg, "");
        String rs = new StringBuilder(s).reverse().toString();
        if (s.equals(rs)) return true;
        else return false;
    }

    /**
     * 题目分析：
     * 既然使用正则效率低，那还是要自己手动处理，于是干脆转成char[]逐个进行判断；
     * 就在我写这里时，我突然想到我这个做法仍然可以优化，明天再做好了。
     *
     * 运行时长：10ms
     *
     * 总结：手动还是快啊。
     */
    public static boolean isPalindrome2(String s) {
        if (s == null || s.equals("")) return true;
        s = s.toLowerCase();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] < 48||(cs[i] >57&&cs[i] < 97)|| cs[i] > 122) cs[i] = ' ';
        }
        for (int i = 0,j=cs.length-1; i<=j; i++,j--) {
            while(cs[i]==' '&&i<j)i++;
            while(cs[j]==' '&&i<j)j--;
            if(cs[i]!=cs[j])return false;
        }
        return true;
    }

    /**
     * 题目分析：
     * 对啊，我为什么要多此一举全转化为' '呢，直接进行判断就好了，于是就有了现在的版本；
     * 少了一个循环果然更快了一些
     *
     * 运行时长：8ms
     *
     * 总结：手动还是快啊。
     */
    public static boolean isPalindrome3(String s) {
        if (s == null || s.equals("")) return true;
        s = s.toLowerCase();
        char[] cs = s.toCharArray();
        for (int i = 0,j=cs.length-1; i<=j; i++,j--) {
            while((cs[i] < 48||(cs[i] >57&&cs[i] < 97)|| cs[i] > 122)&&i<j)i++;
            while((cs[j] < 48||(cs[j] >57&&cs[j] < 97)|| cs[j] > 122)&&i<j) j--;
            if(cs[i]!=cs[j]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome3("race a car"));
        System.out.println(isPalindrome3("race a e car"));
        System.out.println(isPalindrome3("a."));
        System.out.println(isPalindrome3("0p"));
        System.out.println(isPalindrome3("ab@a"));
        System.out.println(isPalindrome3("A man, a plan, a canal: Panama"));
    }
}
