package com.imzhizi.algs.剑指LeetCode;

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
     *
     */
    @Test
    public void No13() {

    }


    /**
     *
     */
    @Test
    public void No14() {

    }


    /**
     *
     */
    @Test
    public void No15() {

    }

    /**
     *
     */
    @Test
    public void No16() {

    }

    /**
     *
     */
    @Test
    public void No17() {

    }

    /**
     *
     */
    @Test
    public void No18() {

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
