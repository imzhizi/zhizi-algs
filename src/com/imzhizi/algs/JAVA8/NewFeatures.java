package com.imzhizi.algs.JAVA8;

import com.imzhizi.algs.ListNode;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class NewFeatures {
    private List<Integer> list = new ArrayList<>();
    private Random random = new Random();

    @Before
    public void before() {
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }
    }


    @Test
    public void 参数化函数() {
        list.forEach((Integer integer) -> System.out.println(integer % 9));
        list.forEach((Integer integer) -> System.out.println(integer));
        list.forEach(System.out::println);
    }

    /**
     * Predicate 为谓词，也就是一个只产生 true、false 的函数
     */
    @Test
    public void 谓词传递() {
        filterNumbers(list, (integer) -> integer > 50).forEach(System.out::println);
        filterNumbers(list, integer -> integer > 50).forEach(System.out::println);
    }

    // 更函数值只是为了体验 Predicate 的用法，实际上都用 stream 自带的 filter 去了
    List<Integer> filterNumbers(List<Integer> list, Predicate<Integer> p) {
        List<Integer> result = new ArrayList<>();
        list.forEach(integer -> {
            if (p.test(integer)) result.add(integer);
        });

        return result;
    }

    @Test
    public void 更好的过滤器() {
        List<Integer> integers1 = filterNumbers(list, integer -> integer > 50);
        List<Integer> integers2 = list.stream().filter(integer -> integer > 50).collect(toList());
    }


    @Test
    public void 排序() {
        List<ListNode> nodes = Arrays.asList(new ListNode(2), new ListNode(7), new ListNode(4), new ListNode(1));
        nodes.sort((ListNode a,ListNode b)->a.val-b.val);
        nodes.sort(Comparator.comparingInt(ListNode::getVal));
        nodes.forEach((ListNode node) -> System.out.print(node.val + " "));
    }
}
