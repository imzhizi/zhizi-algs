package com.imzhizi.algs.笔试.nowcoder;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * created by zhizi
 * on 4/14/20 10:09
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] matrix = new int[6][6];
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                matrix[i][j] = input.nextInt();
            }
        }
        input.close();

        dijkstra(matrix, 1);

        for (int i = 2; i < 6; i++) {
            System.out.println(matrix[1][i]==-1?9999:matrix[1][i]);
        }
    }
    @Test
    public void test(){
        Queue<Integer> list=new LinkedList<>();
        list.offer(1);
        list.offer(2);
        list.offer(3);
        System.out.println(list.poll());
    }

    public static void dijkstra(int[][] matrix, int source) {
        int[] dist = new int[matrix.length];
        dist[source]++;
        int min = Integer.MAX_VALUE;
        int transI = 0;
        int minI = 0;

        for (int l = 1; l < matrix.length; l++) {
            for (int i = 1; i < matrix.length; i++) {
                if (dist[i] == 0) {
                    if (matrix[source][transI] != 0 && matrix[source][transI] != -1 && matrix[transI][i] != -1) {
                        if (matrix[source][i] != -1) {
                            matrix[source][i] = Math.min(matrix[source][i], matrix[source][transI] + matrix[transI][i]);
                        } else {
                            matrix[source][i] = matrix[source][transI] + matrix[transI][i];
                        }
                    }
                    if (matrix[source][i] != -1 && min > matrix[source][i]) {
                        minI = i;
                        min = matrix[source][i];
                    }
                }
            }
            transI = minI;
            dist[transI]++;
            min = Integer.MAX_VALUE;
        }
    }
}
