package com.imzhizi.algs;

import java.util.Scanner;

/**
 * created by zhizi
 * on 4/2/20 19:06
 */

public class Main {
    public void tool() {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        input.close();
    }

    public static void main(String[] args) {

    }
}
