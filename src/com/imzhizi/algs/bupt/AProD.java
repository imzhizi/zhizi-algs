package com.imzhizi.algs.bupt;

import java.util.Arrays;
import java.util.Scanner;

public class AProD {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int x = input.nextInt();
        int y = input.nextInt();
        int z = input.nextInt();
        int[][] interval = new int[n][2];
        int max = 0;
        for (int i = 0; i < n; i++) {
            interval[i][0] = input.nextInt();
            interval[i][1] = input.nextInt();
            max = max > interval[i][1] ? max : interval[i][1];
        }

        int[] temp = new int[max];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < n; j++) {
                if (i < interval[j][0])
                    temp[i] += x;
                else if (i > interval[j][1])
                    temp[i] += z;
                else
                    temp[i] += y;

            }
        }

        Arrays.sort(temp);
        System.out.println(temp[temp.length - 1]);

        input.close();

    }

}
