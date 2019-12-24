package com.imzhizi.algs.剑指offer;

/**
 * [复杂链表的复制_牛客网]( https://www.nowcoder.com/practice/f836b2c43afc4b35ad6adc41ec941dba )
 * [ 独立类文件 ]( https://github.com/imzhizi/zhizi-algs/blob/master/src/com/imzhizi/algs/%E5%89%91%E6%8C%87offer/RandomListNode.java )
 */
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
