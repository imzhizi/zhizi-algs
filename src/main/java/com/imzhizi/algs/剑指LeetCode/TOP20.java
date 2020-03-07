package com.imzhizi.algs.剑指LeetCode;

import com.imzhizi.algs.base.ListNode;
import org.junit.Test;

/**
 * created by zhizi
 * on 2/28/20 14:28
 */
public class TOP20 {

    /**
     * [面试题11. 旋转数组的最小数字 - 力扣（LeetCode）](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/ )
     */
    @Test
    public void No11() {

    }

    public int minArray(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return numbers[i + 1];
            }
        }
        return numbers[0];
    }

    public int minArray2(int[] numbers) {
        int h = 0;
        int t = numbers.length - 1;
        while (h < t) {
            if (numbers[h] <= numbers[h + 1]) {
                h++;
            } else {
                return numbers[h + 1];
            }
            if (numbers[t] >= numbers[t - 1]) {
                t--;
            } else {
                return numbers[t];
            }
        }
        return numbers[0];
    }

    // 二分查找
    // 关键要明白自己的end-case
    public int minArray3(int[] numbers) {
        int h = 0;
        int t = numbers.length - 1;

        while (h < t) {
            int m = (h + t) / 2;
            if (numbers[m] < numbers[t]) {
                t = m;
            } else if (numbers[m] > numbers[t]) {
                h = m + 1;
            } else {
                t--;
            }

        }
        return numbers[t];
    }


    /**
     * [面试题12. 矩阵中的路径 - 力扣（LeetCode）](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/ )
     */
    @Test
    public void No12() {

    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m == 0) return false;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (exist(board, i, j, word, 0, m, n)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, int r, int c, String word, int loc, int m, int n) {
        int length = word.length() - 1;
        if (word.charAt(loc) == board[r][c]) {
            if (loc == length) {
                return true;
            } else if (loc < length) {
                char temp = board[r][c];
                //上
                if (r > 0) {
                    if (board[r - 1][c] != ' ') {
                        board[r][c] = ' ';
                        if (exist(board, r - 1, c, word, loc + 1, m, n)) {
                            return true;
                        } else {
                            board[r][c] = temp;
                        }
                    }
                }
                // 下
                if (r < m - 1) {
                    if (board[r + 1][c] != ' ') {
                        board[r][c] = ' ';
                        if (exist(board, r + 1, c, word, loc + 1, m, n)) {
                            return true;
                        } else {
                            board[r][c] = temp;
                        }
                    }
                }
                // 左
                if (c > 0) {
                    if (board[r][c - 1] != ' ') {
                        board[r][c] = ' ';
                        if (exist(board, r, c - 1, word, loc + 1, m, n)) {
                            return true;
                        } else {
                            board[r][c] = temp;
                        }
                    }
                }
                // 右
                if (c < n - 1) {
                    if (board[r][c + 1] != ' ') {
                        board[r][c] = ' ';
                        if (exist(board, r, c + 1, word, loc + 1, m, n)) {
                            return true;
                        } else {
                            board[r][c] = temp;
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * [面试题13. 机器人的运动范围 - 力扣（LeetCode）](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/ )
     * todo
     */
    @Test
    public void No13() {

    }


    /**
     * [面试题14- I. 剪绳子 - 力扣（LeetCode）](https://leetcode-cn.com/problems/jian-sheng-zi-lcof/ )
     */
    @Test
    public void No14I() {

    }

    public int cuttingRope(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        int result = 1;
        while (n >= 5) {
            result *= 3;
            n -= 3;
        }
        return result * n;
    }

    /**
     * [面试题14- II. 剪绳子 II - 力扣（LeetCode）](https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/ )
     * todo 大数处理
     */
    @Test
    public void No14II() {

    }

    public int cuttingRopeII(int n) {
        if (n < 4) return n - 1;

        long result = 1;
        long mod = (int) 1e9 + 7;

        while (n > 4) {
            result *= 3;
            result %= mod;
            n -= 3;
        }

        return (int) (result * n % mod);
    }

    public int cuttingRopeII2(int n) {
        if (n < 4) return n - 1;

        int result = 1;
        int mod = (int) 1e9 + 7;

        while (n > 4) {
            if (result * 2 > mod) {
                int temp = result;
                result = temp * 2 - mod;
                result += temp;
            } else {
                result *= 3;
            }
            result %= mod;
            n -= 3;
        }

        int temp = result;
        result = (2 * temp) % mod;
        result += ((n - 2) * temp) % mod;
        return result % mod;
    }


    /**
     * [面试题15. 二进制中1的个数 - 力扣（LeetCode）](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/ )
     * 位运算
     */
    @Test
    public void No15() {

    }

    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }

        return count;
    }

    public int hammingWeight2(int n) {
        int count = 0;
        boolean flag = false;

        if (n < 0) {
            n = ~n;
            flag = true;
        }

        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>= 1;
        }

        return flag ? 32 - count : count;
    }

    /**
     *
     */
    @Test
    public void No16() {

    }

    public double myPow(double x, int n) {
        if(n==0||x==1.0) return 1;
        if(x==0) return 0;

        double result=1.0;
        long b=n;

        if(b < 0) {
            x = 1 / x;
            b = -b;
        }

        while(b > 0) {
            if((b & 1) == 1) result *= x;
            x *= x;
            b >>= 1;
        }
        return result;
    }

    /**
     * [面试题17. 打印从1到最大的n位数 - 力扣（LeetCode）](https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/ )
     * 索然无味
     */
    @Test
    public void No17() {

    }

    public int[] printNumbers(int n) {
        int l=(int)Math.pow(10,n)-1;
        int[] result=new int[l];
        for(int i=0;i<l;i++){
            result[i]=i+1;
        }

        return result;
    }


    /**
     * [面试题18. 删除链表的节点 - 力扣（LeetCode）](https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/ )
     */
    @Test
    public void No18() {

    }

    public ListNode deleteNode(ListNode head, int val) {
        if(head==null) return null;
        ListNode result=new ListNode(0);
        ListNode node=result;
        result.next=head;

        while(node.next!=null){
            if(node.next.val==val){
                node.next=node.next.next;
                break;
            }
            node=node.next;
        }

        return result.next;
    }

    /**
     *
     */
    @Test
    public void No19() {

    }

    /**
     *
     */
    @Test
    public void No20() {

    }
}
