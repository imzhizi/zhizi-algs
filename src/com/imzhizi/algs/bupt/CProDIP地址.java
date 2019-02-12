package com.imzhizi.algs.bupt;

import java.util.Scanner;

public class CProDIP地址 {
    static boolean checkNum(String[] s) {// return correct
        for (int j = 0; j < s.length; j++) {
            if (s[j].isEmpty()) return false;
            char[] c = s[j].toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] > '9' || c[i] < '0')
                    return false;
            }
        }
        return true;
    }

    static boolean checkRegion(String[] s) {
        for (int j = 0; j < 4; j++) {
            int n = Integer.parseInt(s[j]);
            if (n < 0 || n > 255) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        input.nextLine();

        for (int i = 0; i < t; i++) {
            String ipString = input.nextLine();
            String ip[] = ipString.split("\\.");

            if (ipString.endsWith(".") || ipString.startsWith(".")) {
                System.out.println("No");
            } else if (ip.length == 4 && checkNum(ip) && checkRegion(ip)) {
                System.out.println("Yes");
            } else
                System.out.println("No");
        }
        input.close();
    }
}
