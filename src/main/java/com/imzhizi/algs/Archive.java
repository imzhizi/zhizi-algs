package com.imzhizi.algs;

import com.imzhizi.algs.剑指NowCoder.Main;

import java.util.*;

/**
 * created by zhizi
 * on 3/29/20 21:08
 */
public class Archive {
    /**
     * 百度
     */
    static class s1 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            long n = input.nextLong();
            input.close();
            System.out.println(n * (n - 1) - 1);
        }
    }

    static class s2 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            long[] nums = new long[n];
            long max = Long.MIN_VALUE;
            int index = -1;
            for (int i = 0; i < n; i++) {
                nums[i] = input.nextLong();
                if (max < nums[i]) {
                    max = nums[i];
                    index = i;
                }
                max = Math.max(max, nums[i]);
            }
            input.close();

            int count = 0;
            while (max >= n) {
                if (n > 10) break;
                count++;
                nums[index] -= n;
                max -= n;
                int mi = index;
                for (int i = 0; i < n; i++) {
                    if (i != mi) {
                        nums[i]++;
                    }
                    if (max < nums[i]) {
                        max = nums[i];
                        index = i;
                    }
                }
            }

            System.out.println(count);
        }
    }

    /**
     * 美团
     */
    static class m1 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            int gap = input.nextInt();

            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = input.nextInt();
            }
            input.close();
            Arrays.sort(nums);
            int[][] matrix = new int[n][n];
            System.out.println(lowCost(nums, matrix, 0, n - 1, gap));
        }

        static int lowCost(int[] nums, int[][] matrix, int head, int tail, int gap) {
            if (nums[tail] - nums[head] <= gap && head <= tail) return nums.length - (tail - head + 1);
            if (matrix[head][tail] != 0) return matrix[head][tail];
            matrix[head][tail] = Math.min(lowCost(nums, matrix, head + 1, tail, gap), lowCost(nums, matrix, head, tail - 1, gap));
            return matrix[head][tail];
        }
    }

    static class m2 {
        public static void main(String[] args) {

            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            long m = input.nextLong();
            int[] rooms = new int[n];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                rooms[i] = input.nextInt();
                sum += rooms[i];
            }
            input.close();

            long count = m / sum * n;
            long mod = m % sum;

            int i = 0;
            while (mod >= 0) {
                if (mod >= rooms[i % n]) {
                    mod -= rooms[i % n];
                    count++;
                    i++;
                }
            }
            System.out.println(count);
        }
    }

    /**
     * 网易面试
     */
    static class w1 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) {
                nums[i] = input.nextLong();
            }
            input.close();

            long min = Long.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                long gap = nums[i] - nums[i - 1];
                if (gap <= 0) {
                    System.out.println(-1);
                    return;
                }
                min = Math.min(min, gap);
            }

            for (int i = 1; i < n; i++) {
                if ((nums[i] - nums[i - 1]) % min != 0) {
                    System.out.println(-1);
                    return;
                }
            }

            System.out.println(min);
        }
    }

    static class w3 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            int m = input.nextInt();
            boolean[] virus = new boolean[n];
            virus[input.nextInt()] = true;

            for (int i = 0; i < m; i++) {
                int q = input.nextInt();
                int[] qs = new int[q];
                boolean flag = false;
                for (int j = 0; j < q; j++) {
                    qs[j] = input.nextInt();
                    if (virus[qs[j]]) flag = true;
                }
                if (flag) {
                    for (int j : qs) virus[j] = true;
                }
            }
            input.close();

            int count = 0;
            for (int i = 0; i < n; i++) {
                if (virus[i]) count++;
            }
            System.out.println(count);
        }
    }


    /**
     * 便利蜂
     */
    static class b1 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            List<Long> list = new ArrayList<>();

            long max = Long.MIN_VALUE;
            long sum = 0;

            String str = input.nextLine();
            input.close();
            String[] nums = str.split(",");
            for (String num : nums) {
                list.add(Long.valueOf(num));
                int size = list.size();
                sum += list.get(size - 1);
                if (list.size() > 3) sum -= list.get(size - 4);
                if (list.size() >= 3) max = Math.max(max, sum);
            }

            System.out.println(max);
        }
    }

    static class b3 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            input.close();
            String[] strs = str.split(",");
            int[] nums = new int[strs.length];
            for (int i = 0; i < strs.length; i++) nums[i] = Integer.parseInt(strs[i]);

            int time = 1;
            int l = nums.length;
            if (l < 3) {
                time = l - 1;
            } else {
                for (int i = 0; i < l && nums[i] < l - 1 - i; i++) {
                    int step = 0;
                    int stepI = 0;
                    for (int j = 1; j <= nums[i]; j++) {
                        if (nums[i + j] + j > step) {
                            step = nums[i + j] + j;
                            stepI = i + j;
                        }
                    }

                    i = stepI - 1;
                    if (i < 0) {
                        System.out.println(-1);
                        return;
                    }
                    time++;
                }
            }

            System.out.println(time);
        }
    }

    /**
     * 阿里
     */
    static class a2 {

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();

            int[][] matrix = new int[n + 1][n + 1];
            visited = new boolean[n + 1];

            int m = input.nextInt();
            int x = input.nextInt();
            for (int i = 0; i < m; i++) {
                int r = input.nextInt();
                int c = input.nextInt();
                matrix[r][c] = input.nextInt();
            }
            input.close();

            int max = 0;
            for (int i = 1; i <= n; i++) {
                if (i != x) {
                    max = Math.max(max, longestPath(matrix, i, x) + longestPath(matrix, x, i));
                }
            }
            System.out.println(max);
        }

        static boolean[] visited;

        private static int longestPath(int[][] matrix, int from, int to) {
            if (visited[from]) {
                return -1;
            }
            visited[from] = true;
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < matrix.length; i++) {
                int temp;
                if (i == from || i == to) {
                    temp = matrix[i][to];
                } else {
                    temp = longestPath(matrix, i, to);
                    if (temp > 0) temp += matrix[from][i];
                }
                if (temp > 0) min = Math.min(min, temp);
            }
            visited[from] = false;
            return min;
        }

    }

    /**
     *
     */
    static class wm1 {
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
            Scanner input = new Scanner(System.in);
            String w = input.nextLine();
            String[] ws = w.split(" ");
            int[] weights = new int[ws.length];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = Integer.parseInt(ws[i]);
            }
            int limit = input.nextInt();
            input.close();
            System.out.println(countLeastBoat(weights, limit));
        }


        public static int countLeastBoat(int[] weights, int limit) {
            Arrays.sort(weights);
            int head = 0;
            int tail = weights.length - 1;
            int count = 0;
            while (head <= tail) {
                if (weights[head] + weights[tail] <= limit) {
                    head++;
                }
                tail--;
                count++;
            }
            return count;
        }

    }


    static class wm2 {

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int[][] matrix = new int[6][6];
            for (int i = 1; i < 6; i++) {
                for (int j = 1; j < 6; j++) {
                    matrix[i][j] = input.nextInt();
                }
            }
            input.close();

            dijkstra(matrix, 1);

            for (int i = 2; i < 6; i++) {
                System.out.println(matrix[1][i] == -1 ? 9999 : matrix[1][i]);
            }
        }

        public static void dijkstra(int[][] matrix, int source) {
            int[] dist = new int[matrix.length];
            dist[source]++;
            int min = Integer.MAX_VALUE;
            int transI = 0;
            int minI = 0;

            for (int l = 1; l < matrix.length; l++) {
                for (int i = 1; i < matrix.length; i++) {
                    if (dist[i] == 0) {
                        if (matrix[source][transI] != 0 && matrix[source][transI] != -1 && matrix[transI][i] != -1) {
                            if (matrix[source][i] != -1) {
                                matrix[source][i] = Math.min(matrix[source][i], matrix[source][transI] + matrix[transI][i]);
                            } else {
                                matrix[source][i] = matrix[source][transI] + matrix[transI][i];
                            }
                        }
                        if (matrix[source][i] != -1 && min > matrix[source][i]) {
                            minI = i;
                            min = matrix[source][i];
                        }
                    }
                }
                transI = minI;
                dist[transI]++;
                min = Integer.MAX_VALUE;
            }
        }
    }

    static class Pair {
        int l;
        int w;
        double val;

        public Pair(int l, int w) {
            this.l = l;
            this.w = w;
        }

        public Pair(int l) {
            this.l = l;
        }

        public void setW(int w) {
            this.w = w;
            this.val = this.l * 1.0 / this.w;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "l=" + l +
                    ", w=" + w +
                    '}';
        }
    }

    static class wm3 {
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
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            List<Pair> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(new Pair(input.nextInt(), input.nextInt()));
            }
            input.close();

            list.sort((o1, o2) -> {
                if (o1.l > o2.l) {
                    return 1;
                } else if (o1.l == o2.l) {
                    return o1.w - o2.w;
                } else {
                    return -1;
                }
            });

            int last = 0;
            int count = 1;
            int max = 0;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).l > list.get(last).l) {
                    if (list.get(i).w > list.get(last).w) {
                        count++;
                        last = i;
                    }
                }
            }

            System.out.println(count);
        }
    }

    static class wm4 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            int cap = input.nextInt();

            for (int i = 0; i < n; i++) {
            }

            int count = 0;
            for (int i = 0; i < n; i++) {

            }

            System.out.println(count);

        }
    }


    /**
     * 京东
     */
    static class j1 {
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

    static class k1 {
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

        static class Line {
            Node last;
            Node next;
            long weight = 0;
        }

        static class Node {
            Line last;
            Line left;
            Line right;
        }

//    @Test
//    public void test{
//List<Node> cities = new ArrayList<>();
//        Node node = new Node();
//        Line left = new Line();
//        Line right = new Line();
//        left.last = node;
//        right.last = node;
//        node.left = left;
//        node.right = right;
//        cities.add(node);
//        for (int i = 0; i < n; i++) {
//            int flag = input.nextInt();
//            if (flag == 1) {
//                int from = input.nextInt();
//                int to = input.nextInt();
//                int weight = input.nextInt();
//                if (cities.size() < Math.max(from, to)) {
//                    for (int j = cities.size(); j < Math.max(from, to); j++) {
//                        Node newNode = new Node();
//                        if (j % 2 == 0) {
//                            newNode.last = cities.get(j / 2).left;
//                        } else {
//                            newNode.last = cities.get(j / 2).right;
//                        }
//                        Line nleft = new Line();
//                        Line nright = new Line();
//                        nleft.last = newNode;
//                        nright.last = newNode;
//                        newNode.left = left;
//                        newNode.right = right;
//                    }
//
//
//                }
//
//            } else {
//
//            }
//        }
//    }

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            int m = input.nextInt();
            int[][] map = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = input.nextInt();
                }
            }

            int count = 0;
            for (int r = 1; r < n - 1; r++) {
                for (int c = 1; c < m - 1; c++) {
                    if (map[r][c] == 1 && dip(map, r - 1, c, 't') && dip(map, r + 1, c, 'b') && dip(map, r, c - 1, 'l') && dip(map, r, c + 1, 'r')) {
                        count++;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println(count);
        }

        public static boolean dip(int[][] map, int r, int c, char direct) {
            if (map[r][c] == 0) {
                return true;
            }

            if (map[r][c] == -1) {
                return (map[r - 1][c] == -1) || (map[r][c + 1] == -1);
            }

            if (r <= 0 || c <= 0 || r >= map.length - 1 || c >= map[0].length - 1) return false;

            map[r][c] = -1;

            if (direct == 't') {
                return dip(map, r + 1, c, 't') && dip(map, r, c - 1, 'l') && dip(map, r, c + 1, 'r');
            }
            if (direct == 'b') {
                return dip(map, r - 1, c, 'b') && dip(map, r, c - 1, 'l') && dip(map, r, c + 1, 'r');

            }
            if (direct == 'l') {
                return dip(map, r - 1, c, 't') && dip(map, r + 1, c, 'b') && dip(map, r, c - 1, 'l');

            }
            if (direct == 'r') {
                return dip(map, r - 1, c, 't') && dip(map, r + 1, c, 'b') && dip(map, r, c + 1, 'r');

            }
            return false;
        }
    }


    static class k2 {
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
    }



    static class t1 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                Queue<Integer> queue = new LinkedList<>();
                int q = input.nextInt();
                for (int j = 0; j < q; j++) {
                    String op = input.next();
                    if (op.contains("PU")) {
//                    queue.add(Integer.parseInt(op.substring(op.lastIndexOf(" ") + 1)));
                        queue.add(input.nextInt());
                    } else if (op.contains("PO")) {
                        if (queue.peek() == null) result.add(-1);
                        else queue.poll();
                    } else if (op.contains("TO")) {
                        if (queue.peek() == null) result.add(-1);
                        else result.add(queue.peek());
                    } else if (op.contains("SI")) {
                        result.add(queue.size());
                    } else if (op.contains("CL")) {
                        queue.clear();
                    }
                }
            }
            input.close();

            for (int i : result) {
                System.out.println(i);
            }

        }
    }

    static class t2 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int m = input.nextInt();
            double[] result = new double[m];
            for (int j = 0; j < m; j++) {
                int n = input.nextInt();
                int[][] numA = new int[2][n];
                int[][] numB = new int[2][n];
                for (int i = 0; i < n; i++) {
                    numA[0][i] = input.nextInt();
                    numA[1][i] = input.nextInt();
                }
                for (int i = 0; i < n; i++) {
                    numB[0][i] = input.nextInt();
                    numB[1][i] = input.nextInt();
                }

                double min = Double.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    for (int k = 0; k < n; k++) {
                        min = Math.min(min, dis(numA[0][i], numA[1][i], numB[0][k], numB[1][k]));
                    }
                }
                result[j] = min;
            }

            for (double d : result) {
                System.out.println(String.format("%.3f", d));
            }
        }

        public static double dis(int x1, int y1, int x2, int y2) {
//        System.out.println(x1 + " " + x2 + " " + y1 + " " + y2);
            long x = x1 - x2;
            long y = y1 - y2;
            return Math.sqrt(x * x + y * y);
        }
    }

    static class t4 {

        static class Queue {
            Stack<Integer> stack1 = new Stack<>();
            Stack<Integer> stack2 = new Stack<>();

            void add(int x) {
                stack1.push(x);
            }

            int peek() {
                if (stack2.isEmpty()) {
                    int result = poll();
                    stack2.push(result);
                }
                return stack2.peek();
            }

            int poll() {
                if (stack2.isEmpty()) {
                    while (!stack1.isEmpty()) {
                        stack2.push(stack1.pop());
                    }
                }
                return stack2.pop();
            }
        }

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();

            Queue queue = new Queue();
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String op = input.next();
                if (op.startsWith("ad")) {
                    queue.add(input.nextInt());
                } else if (op.startsWith("po")) {
                    queue.poll();
                } else if (op.startsWith("pe")) {
                    result.add(queue.peek());
                }
            }

            for (int i : result) {
                System.out.println(i);
            }
        }
    }
}