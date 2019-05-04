package com.imzhizi.algs.LeetCode.LeetCode2018;

/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 *
 * Example 1:
 * Input: 4
 * Output: 2
 *
 * Example 2:
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 * the decimal part is truncated, 2 is returned.
 * created by zhizi on 2018/5/2
 */
public class A69_SqrtX {
    // https://1-im.guokr.com/formula/768b411ac2ba3b8e23a2e85f2dfc565ecf2d13b9.svg
    // https://www.guokr.com/question/461510/
    /**
     * 函数描述：
     * 求一个数字的算术平方根，这我是不会的，但我猜想是存在算法的，果不其然
     * 我使用的属于比较传统的 —— 牛顿迭代法求开方，据说这个算法来源于牛顿-拉弗森方法
     * 这个方法是一个数学方法，大致来说当你要解f(x)=x^2-a，那么根就是x轴与f(x)的交点
     * 而通过一次一次地求取交点附近点的切线与x轴的垂点，让垂点不断地逼近交点，此时垂点就越接近于根值
     * 运行时长：38 ms
     *
     * 总结：
     * 如果还是不理解，可以查看相关链接，我觉得果壳那篇解释的比较清楚
     */
    public static int mySqrt(int x) {
        if(x==1)return 1;
        if(x==2)return 1;
        if(x==3)return 1;
        double temp = 1.0;
        temp = 0.5 * (temp + x / temp);
        while ((temp * temp) - x >=1) {
            temp = 0.5 * (temp + x / temp);
        }
        return (int)temp;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(1));
        System.out.println(mySqrt(2));
        System.out.println(mySqrt(3));
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(5));
        System.out.println(mySqrt(6));
        System.out.println(mySqrt(7));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(9));
        System.out.println(mySqrt(10));
    }
}
