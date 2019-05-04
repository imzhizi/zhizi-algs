package com.imzhizi.algs.BUPT;

import java.util.Scanner;

public class DProLCA3 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        for (int i = 0; i < t; i++) {
            int n = input.nextInt();
            int[][] tree = new int[n + 1][n + 1];
            for (int j = 1; j < n; j++) {
                int x = input.nextInt();
                int y = input.nextInt();
                tree[y][x] = 1;
            } // tree is ready

            int m = input.nextInt();
            for (int j = 0; j < m; j++) {
                int aa = input.nextInt();
                int bb = input.nextInt();

                if (aa == bb) {
                    System.out.println(aa);
                    continue;
                }
                int a = aa > bb ? aa : bb;
                int b = aa > bb ? bb : aa;
                int f = 0;

                for (int k = 0; k < tree.length; k++) {
                    f = findF(tree[a]);
                    if (f == b) {
                        System.out.println(b);
                        break;
                    } else if (f > b) {
                        a = f;
                    } else if (f < b) {
                        a = b;
                        b = f;
                    }
                }
            }
        }
        input.close();
    }

    static int findF(int[] s) {
        for (int i = 1; i < s.length; i++) {
            if (s[i] == 1)
                return i;
        }
        return 0;
    }
}
