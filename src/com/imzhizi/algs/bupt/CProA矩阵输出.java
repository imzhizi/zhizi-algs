package com.imzhizi.algs.bupt;

import java.util.Scanner;

public class CProA矩阵输出 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        int[] require = new int[40];
        for (int i = 0; i < t * 2; i++)
            require[i] = input.nextInt();//接收所有结果

        for (int i = 0; i < t; i++) {
            int n = require[2 * i];
            int m = require[2 * i + 1];

            for (int k = 0; k < m; k++)
                System.out.print("#");
            System.out.println();

            for (int j = 0; j < n - 2; j++) {
                if (m != 1)
                    System.out.print("#");
                for (int p = 0; p < m - 2; p++)
                    System.out.print("*");
                System.out.println("#");
            }
            if (n != 1) {
                for (int k = 0; k < m; k++)
                    System.out.print("#");
                System.out.println();
            }

        }

        input.close();
    }

}
