package com.imzhizi.algs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleTest {

    @Test
    public void queue(){
        Queue<String> queue=new LinkedList<>();
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
