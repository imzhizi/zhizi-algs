package com.imzhizi.algs.nowcoder.剑指;

import org.junit.Test;

public class Part7 {
    /**
     * [矩形覆盖_牛客网]( https://www.nowcoder.com/practice/72a5a919508a4251859fb2cfb987a0e6 )
     */
    @Test
    public void No70() {

    }

    public int RectCover(int target) {
        if (target <= 2) {
            return target;
        }

        int n1 = 1;
        int n2 = 2;
        while (target-- > 2) {
            n2 += n1;
            n1 = n2 - n1;
        }

        return n2;
    }
}
