package com.imzhizi.algs.bupt;

import java.util.ArrayList;
import java.util.Scanner;

public class CProD {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        long startTime = System.currentTimeMillis();
        input.nextLine();
        ArrayList<String> IPs = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            IPs.add(input.nextLine());
        }

        for (int i = 0; i < t; i++) {
            String string = IPs.get(i);
            int dot1 = string.indexOf(".");
            String sub1 = string.substring(0, dot1);
            int a1 = 0;
            try {
                a1 = Integer.parseInt(sub1);
            } catch (NumberFormatException e) {
                System.out.println("No");
                continue;
            }
            if (a1 > 255 || a1 < 0) {
                System.out.println("No");
                continue;
            } else {
                int dot2 = string.indexOf(".", dot1 + 1);//plus 1 for ignore itself
                String sub2 = string.substring(dot1 + 1, dot2);
                int a2 = 0;
                try {
                    a2 = Integer.parseInt(sub2);
                } catch (NumberFormatException e) {
                    System.out.println("No");
                    continue;
                }
                if (a2 > 255 || a2 < 0) {
                    System.out.println("No");
                    continue;
                } else {
                    int dot3 = string.lastIndexOf(".");
                    String sub3 = string.substring(dot2 + 1, dot3);
                    int a3 = 0;
                    try {
                        a3 = Integer.parseInt(sub3);
                    } catch (NumberFormatException e) {
                        System.out.println("No");
                        continue;
                    }
                    if (a3 > 255 || a3 < 0) {
                        System.out.println("No");
                        continue;
                    } else {
                        String sub4 = string.substring(dot3 + 1);
                        int a4 = 0;
                        try {
                            a4 = Integer.parseInt(sub4);
                        } catch (NumberFormatException e) {
                            System.out.println("No");
                            continue;
                        }
                        if (a4 > 255 || a4 < 0) {
                            System.out.println("No");
                            continue;
                        } else
                            System.out.println("Yes");
                    }
                }
            }
        }

        input.close();

        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("执行时间：" + excTime + "s");
    }

}
