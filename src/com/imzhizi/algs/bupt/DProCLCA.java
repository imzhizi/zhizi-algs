package com.imzhizi.algs.bupt;

import java.util.HashMap;
import java.util.Scanner;

public class DProCLCA {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        for (int i = 0; i < t; i++) {
            int n = input.nextInt();
            HashMap<Integer, Integer> treemap = new HashMap<>();
            for (int j = 0; j < n - 1; j++) {
                int p = input.nextInt();
                int q = input.nextInt();
                treemap.put(q, p);//tree is ready
            }

            int query = input.nextInt();
            for (int j = 0; j < query; j++) {
                int a = input.nextInt();
                int b = input.nextInt();
                while (true) {
                    Integer r = findK(treemap, a, b);
                    if (r != null) {
                        System.out.println(r);
                        break;
                    }
                    b = treemap.get(b);
                }

            }

        }

        input.close();

    }

    static Integer findK(HashMap<Integer, Integer> hm, Integer a, Integer b) {
        if (a == null) return null;
        else if (a == b) return a;
        else {
            a = hm.get(a);
            return findK(hm, a, b);
        }
    }
}
