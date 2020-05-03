package com.imzhizi.algs.剑指赛码;

import java.util.HashMap;
import java.util.Scanner;

/**
 * created by zhizi
 * on 4/10/20 20:00
 */

public class Main {
    public void tool() {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        input.close();
        String[] strs = str.split(",");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        boolean[] res = new boolean[n];

        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < 12; j++) {
                int temp = input.nextInt();
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }
            res[i] = makeup(map);
        }
        input.close();

        for (int i = 0; i < n; i++) {
            System.out.println(res[i] ? "POSSIBLE" : "IMPOSSIBLE");
        }
    }

    public static boolean makeup(HashMap<Integer, Integer> map) {
        if (map.size() == 3) {
            for (int key : map.keySet()) {
                if (map.get(key) != 4) return false;
            }
            return true;
        } else if (map.size() == 2) {
            for (int key : map.keySet()) {
                if (map.get(key) != 8 && map.get(key) != 4) return false;
            }
            return true;
        } else if (map.size() == 1) {
            for (int key : map.keySet()) {
                if (map.get(key) != 12) return false;
            }
            return true;
        }
        return false;
    }
}