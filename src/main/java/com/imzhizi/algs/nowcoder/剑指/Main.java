package com.imzhizi.algs.nowcoder.剑指;

import java.util.HashMap;
import java.util.HashSet;
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

        HashMap<String, HashSet<String>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String site = input.nextLine();
            String[] domains = site.split("/");
            if (domains.length == 2 && !map.containsKey(domains[1])) {
                map.put(domains[1], new HashSet<>());
            } else {
                map.get(domains[1]).add(site.substring(7 + domains[1].length()));
            }
        }

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int n = 10000;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += j;
            }
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//        Queue<Integer> queue = new LinkedList<>();
//        List<Integer> result = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            int q = input.nextInt();
//            for (int j = 0; j < q; j++) {
//                String op = input.next();
//                if (op.contains("PU")) {
////                    queue.add(Integer.parseInt(op.substring(op.lastIndexOf(" ") + 1)));
//                    queue.add(input.nextInt());
//                } else if (op.contains("PO")) {
//                    if (queue.peek() == null) result.add(-1);
//                    else queue.poll();
//                } else if (op.contains("TO")) {
//                    if (queue.peek() == null) result.add(-1);
//                    else result.add(queue.peek());
//                } else if (op.contains("SI")) {
//                    result.add(queue.size());
//                } else if (op.contains("CL")) {
//                    queue.clear();
//                }
//            }
//        }
//        input.close();
//
//        for (int i : result) {
//            System.out.println(i);
//        }
//
//    }
}