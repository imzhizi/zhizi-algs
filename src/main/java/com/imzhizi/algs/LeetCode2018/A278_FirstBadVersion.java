package com.imzhizi.algs.LeetCode2018;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * Example:
 * Given n = 5, and version = 4 is the first bad version.
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 *
 * Then 4 is the first bad version.
 *
 * created by zhizi on 2018/11/29
 */
public class A278_FirstBadVersion {
    static int firstBad=1;

    /**
     * 题目分析：
     * 这道题看起来很复杂, 其实本质上是一种二分查找, 但跟二分查找不同的点在于 isBadVersion 是优化点
     * 要尽量减少 isBadVersion 的调用, 所以优化的重点就放在 isBadVersion 的调用次数
     *
     * 运行时长：13ms / 51%
     *
     * 总结：在 n 非常大的时候, 计算中间值时会超 int, 此时使用 (end-begin)/2 是个小技巧
     *
     */
    static int firstBadVersion(int n) {
        return binarySearch(1,n);
    }

    static int binarySearch(int begin, int end){
        if(begin>=end) return end;
        int mid= begin + (end-begin)/2;
        if(isBadVersion(mid)){
            return binarySearch(begin,mid-1);
        }else{
            return binarySearch(mid+1,end);
        }
    }

    /**
     * 题目分析：
     * 无
     *
     * 运行时长：13ms / 51%
     *
     * 总结：
     * 原来不必使用递归, 循环即可, 只可惜没什么优化效果
     */
    static int firstBadVersion2(int n) {
        int begin=1;
        while(begin<n){
            int mid= begin + (n-begin)/2;
            if (isBadVersion(mid))
                n=mid;
            else
                begin=mid+1;
        }
        return n;
    }


    static boolean isBadVersion(int n){
        if (n<firstBad) return false;
        else return true;
    }

    public static void main(String[] args) {
//        firstBad=1702766719;
//        System.out.println(firstBadVersion(2126753390));
//        System.out.println(firstBadVersion2(2126753390));

        firstBad=3;
        System.out.println(firstBadVersion2(6));
        System.out.println(firstBadVersion2(5));

        firstBad=4;
        System.out.println(firstBadVersion2(6));
        System.out.println(firstBadVersion2(5));

    }
}
