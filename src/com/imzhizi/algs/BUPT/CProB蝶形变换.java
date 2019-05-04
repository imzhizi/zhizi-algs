package com.imzhizi.algs.BUPT;

import java.util.ArrayList;
import java.util.Scanner;

public class CProB蝶形变换 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<int[]> result = new ArrayList<int[]>();//keep results
        int group = input.nextInt();
        for (int i = 0; i < group; i++) {
            int pow = input.nextInt();
            int length = (int) Math.pow(2, pow);
            int[] r = new int[length];
            for (int j = 0; j < length; j++)
                r[j] = input.nextInt();//array is ready

            r = split(r, length);
            result.add(r);
        }

        for (int[] r : result) {
            for (int i = 0; i < r.length - 1; i++) {
                System.out.print(r[i] + " ");
            }
            System.out.println(r[r.length - 1]);
        }


        input.close();

    }

    public static int[] split(int[] r, int length) {
        int l = length / 2;
        int[] odd = new int[l];
        int[] even = new int[l];
        for (int i = 0; i < l; i++) {
            even[i] = r[i * 2];
            odd[i] = r[i * 2 + 1];
        }
        if (l != 2) {
            even = split(even, l);
            odd = split(odd, l);
        }
        for (int i = 0; i < l; i++)
            r[i] = even[i];
        for (int i = 0; i < l; i++)
            r[l + i] = odd[i];
        return r;
    }

}
