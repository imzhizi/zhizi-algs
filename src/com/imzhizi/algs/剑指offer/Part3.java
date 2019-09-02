package com.imzhizi.algs.剑指offer;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Part3 {
    @Test
    public void No31() {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop = {4, 5, 3, 2, 1};
        System.out.println(IsPopOrder2(push, pop));
    }

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        List<Integer> list = Arrays.stream(pushA).boxed().collect(Collectors.toList());
        int i = 0;
        int j = 0;
        while (list.size() != 0 && i < list.size()) {
            if (list.get(i) == popA[j]) {
                list.remove(i);
                i = i - 2;
                j++;
            }
            i++;
        }
        System.out.println(j);
        if (j != pushA.length) {
            return false;
        } else {
            return true;
        }
    }

    public boolean IsPopOrder2(int[] pushA, int[] popA) {
        int[] stack = new int[pushA.length];
        int index = -1;
        int length = pushA.length;
        int push = 0;
        int pop = 0;

        while (push < length) {
            stack[++index] = pushA[push++];
            while (stack[index] == popA[pop]) {
                pop++;
                index--;
            }
        }

        if (pop == length) {
            return true;
        } else {
            return false;
        }
    }
}
