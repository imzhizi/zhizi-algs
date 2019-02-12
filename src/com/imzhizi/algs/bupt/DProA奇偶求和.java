package com.imzhizi.algs.bupt;

import java.util.Scanner;

public class DProA奇偶求和 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        long[] result = new long[t * 2];
        for (int i = 0; i < t; i++) {
            int n = input.nextInt();
            long even = 0;
            long odd = 0;
            for (int j = 0; j < n; j++) {
                int temp = input.nextInt();
                if (temp % 2 == 0) even += temp;
                else odd += temp;
            }
            result[i * 2] = odd;
            result[i * 2 + 1] = even;
        }

        for (int i = 0; i < t; i++) {
            System.out.println(result[i * 2] + " " + result[i * 2 + 1]);
        }

        input.close();

    }
}
