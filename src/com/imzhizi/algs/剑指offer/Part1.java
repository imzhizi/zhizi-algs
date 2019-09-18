package com.imzhizi.algs.剑指offer;

import com.imzhizi.algs.ListNode;
import com.imzhizi.algs.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Part1 {
    /**
     * [数组中重复的数字_牛客网]( https://www.nowcoder.com/practice/623a5ac0ea5b4e5f95552655361ae0a8 )
     */
    @Test
    public void No3_1() {
        int[] duplication = new int[]{-1};
        System.out.println(duplicate(new int[]{}, 0, duplication));
        System.out.println(duplication[0]);
    }

    boolean duplicate(int[] numbers, int length, int[] duplication) {
        boolean[] k = new boolean[length];
        for (int i = 0; i < k.length; i++) {
            if (k[numbers[i]]) {
                duplication[0] = numbers[i];
                return true;
            }
            k[numbers[i]] = true;
        }
        return false;
    }

    /**
     * [ 二维数组中的查找_牛客网 ]( https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e )
     */
    @Test
    public void No4() {

    }

    boolean Find(int target, int[][] array) {
        int height = array.length;
        int width = array[0].length;
        int i = height;
        int j = 0;
        while (--i >= 0 && j < width) {
            if (array[i][j] < target) {
                j++;
                i = height;
            } else if (array[i][j] == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * [替换空格_牛客网]( https://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423 )
     * 核心思想把一次性当道最终位置
     * 将空格替换为 %20
     */
    @Test
    public void No5() {
        System.out.println("we are happy");


        // 数组B合并到A
        int[] numsA = {1, 3, 5, 7, 0, 0, 0};
        int[] numsB = {2, 4, 6};
        for (int i : 数组合并(numsA, numsB))
            System.out.print(i + " ");
    }

    String replaceSpace(StringBuffer str) {
        int length = str.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                count++;
            }
        }

        char[] chars = new char[length + count * 2];
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ') {
                chars[i + count * 2] = '0';
                chars[i + count * 2 - 1] = '2';
                chars[i + count * 2 - 2] = '%';
                count--;
            } else {
                chars[i + count * 2] = str.charAt(i);
            }
        }
        return new String(chars);
    }

    private int[] 数组合并(int[] numsA, int[] numsB) {
        int b = numsB.length - 1;
        int a = numsA.length - numsB.length - 1;
        int l = numsA.length - 1;

        while (l > -1) {
            if (a > -1 && b > -1) {
                if (numsA[a] > numsB[b]) {
                    numsA[l--] = numsA[a--];
                } else {
                    numsA[l--] = numsB[b--];
                }
            } else if (a < 0) {
                numsA[l--] = numsB[b--];
            } else {
                numsA[l--] = numsA[a--];
            }
        }

        return numsA;
    }

    /**
     * [从尾到头打印链表_牛客网]( https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035 )
     */
    @Test
    public void No6() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        printListFromTailToHead(node1);
        System.out.println();
        从尾到头打印链表(node1);
    }

    ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> stack = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        while (listNode != null) {
            stack.add(listNode.val);
            listNode = listNode.next;
        }

        for (int i = stack.size() - 1; i >= 0; i--) {
            result.add(stack.get(i));
        }

        return result;
    }

    private void 从尾到头打印链表(ListNode head) {
        if (head != null) {
            if (head.next != null)
                从尾到头打印链表(head.next);
            System.out.print(head.val + " ");
        }
    }

    /**
     * [重建二叉树_牛客网]( https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6 )
     */
    @Test
    public void No7() {
        // 前序遍历 中序遍历 重建二叉树
        int[] pres = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] mids = new int[]{4, 2, 5, 1, 6, 3, 7};
        System.out.println(reConstructBinaryTree(pres, mids));
    }

    TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < in.length; i++) {
            inMap.put(in[i], i);
        }
        return reConstruct(pre, 0, pre.length - 1, inMap, 0, in.length - 1);
    }

    TreeNode reConstruct(int[] pre, int p1, int p2, Map<Integer, Integer> inMap, int i1, int i2) {
        if (p1 == p2 || i1 == i2) {
            return new TreeNode(pre[p1]);
        }

        if (p1 > p2 || i1 > i2 || p1 < 0 || i1 < 0 || p2 == pre.length || i2 == inMap.size()) {
            return null;
        }

        TreeNode head = new TreeNode(pre[p1]);
        // p1 是根节点, 那么需要知道哪部分是左子树
        // 也就是找到根在in[]中的位置
        int inroot = inMap.get(pre[p1]);

        int leftlength = inroot - i1;
        int preroot = p1 + leftlength;

        head.left = reConstruct(pre, p1 + 1, preroot, inMap, i1, inroot - 1);

        head.right = reConstruct(pre, preroot + 1, p2, inMap, inroot + 1, i2);

        return head;
    }

    /**
     * [二叉树的下一个结点_牛客网]( https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e )
     */
    @Test
    public void No8() {

    }

    TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.left == null && pNode.right == null) {
            while (pNode.next != null && pNode.next.right == pNode) {
                pNode = pNode.next;
            }
            return pNode.next;
        } else if (pNode.right != null) {
            TreeLinkNode right = pNode.right;
            while (right.left != null) {
                right = right.left;
            }
            return right;
        } else if (pNode.next != null && pNode.next.left == pNode) {
            return pNode.next;
        }
        return null;
    }

    /**
     * [用两个栈实现队列_牛客网]( https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6 )
     */
    @Test
    public void No9() {

    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    void push(int node) {
        stack1.push(node);
        transfer();
    }

    int pop() {
        transfer();
        return stack2.pop();
    }

    // 按需配给
    public void transfer() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    /**
     * [斐波那契数列_牛客网]( https://www.nowcoder.com/practice/c6c7742f5ba7442aada113136ddea0c3 )
     */
    @Test
    public void No10_1() {
        System.out.println(Fibonacci(10));
    }

    int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        int n1 = 0;
        int n2 = 1;

        while (n-- > 1) {
            n2 = n2 + n1;
            n1 = n2 - n1;
        }
        return n2;
    }

    /**
     * [跳台阶_牛客网]( https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4 )
     * [变态跳台阶_牛客网]( https://www.nowcoder.com/practice/22243d016f6b47f2a6928b4313c85387 )
     */
    @Test
    public void No10_2() {
        System.out.println(JumpFloor(10));
    }

    int JumpFloor(int target) {
        if (target <= 1) {
            return target;
        }

        int n1 = 1;
        int n2 = 1;

        while (target-- > 1) {
            n2 = n2 + n1;
            n1 = n2 - n1;
        }

        return n2;
    }

    int JumpFloorII(int target) {
        return 1 << (target - 1);
    }

    /**
     * [旋转数组的最小数字_牛客网]( https://www.nowcoder.com/practice/9f3231a991af4f55b95579b44b7a01ba )
     */
    @Test
    public void No11() {

    }

    int minNumberInRotateArray(int[] array) {
        // 二分查找
        // 找到第一个比最后一个数大的数字
        int head = 0;
        int tail = array.length - 1;
        int mid = (head + tail) / 2;
        int target = array[tail];

        while (head != mid && mid != tail) {
            if (array[mid] < target) {
                // 比目标值小, 向左移动
                tail = mid;
                mid = (head + tail) / 2;
            } else if (array[mid] > target) {
                // 比目标值大, 向右移动
                head = mid;
                mid = (head + tail) / 2;
            }

        }

        return array[mid + 1];
    }

    /**
     * [矩阵中的路径_牛客网]( https://www.nowcoder.com/practice/c61c6999eecb4b8f88a98f66b273a3cc )
     * todo
     */
    @Test
    public void No12() {
        System.out.println(hasPath1("ABCESFCEADEE".toCharArray(), 3, 4, "ABCCED".toCharArray()));
        System.out.println(hasPath1("ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS".toCharArray(),
                5, 8, "SGGFIECVAASABCEHJIGQEMS".toCharArray()));
    }

    boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == str[0]) {
                HashSet<Integer> visited = new HashSet<>();
                visited.add(i);
                if (hasPath(matrix, rows, cols, str, i, 1, visited)) {
                    return true;
                }
                visited.remove(i);
            }
        }
        return false;
    }

    boolean hasPath(char[] matrix, int rows, int cols, char[] str, int loc, int index, HashSet<Integer> visited) {
        if (index == str.length) return true;
        if (loc < 0 || loc >= rows * cols) return false;

        int up = loc - cols;
        if (up >= 0 && !visited.contains(up)) {
            if (matrix[up] == str[index]) {
                visited.add(up);
                if (hasPath(matrix, rows, cols, str, up, index + 1, visited)) {
                    return true;
                } else {
                    visited.remove(up);
                }
            }
        }

        int down = loc + cols;
        if (down < rows * cols && !visited.contains(down)) {
            if (matrix[down] == str[index]) {
                visited.add(down);
                if (hasPath(matrix, rows, cols, str, down, index + 1, visited)) {
                    return true;
                } else {
                    visited.remove(down);
                }
            }
        }

        int left = loc - 1;
        if (loc % cols > 0 && !visited.contains(left)) {
            if (matrix[left] == str[index]) {
                visited.add(left);
                if (hasPath(matrix, rows, cols, str, left, index + 1, visited)) {
                    return true;
                } else {
                    visited.remove(left);
                }
            }
        }

        int right = loc + 1;
        if (loc % cols < cols - 1 && !visited.contains(right)) {
            if (matrix[right] == str[index]) {
                visited.add(right);
                if (hasPath(matrix, rows, cols, str, right, index + 1, visited)) {
                    return true;
                } else {
                    visited.remove(right);
                }
            }
        }

        return false;
    }


    boolean hasPath1(char[] matrix, int rows, int cols, char[] str) {
        for (int i = 0; i < matrix.length; i++) {
            boolean[] visited = new boolean[matrix.length];
            if (matrix[i] == str[0] && hasPath1(matrix, rows, cols, str, i, 0, visited)) {
                return true;
            }
        }
        return false;
    }

    boolean hasPath1(char[] matrix, int rows, int cols, char[] str, int loc, int index, boolean[] visited) {
        if (loc < 0 || loc >= rows * cols || index < 0 || index >= str.length || visited[loc] || matrix[loc] != str[index])
            return false;

        if (index == str.length - 1) return true;

        visited[loc] = true;
        if (hasPath1(matrix, rows, cols, str, loc - cols, index + 1, visited)
                || hasPath1(matrix, rows, cols, str, loc + cols, index + 1, visited)
                || loc % cols > 0 && hasPath1(matrix, rows, cols, str, loc - 1, index + 1, visited)
                || loc % cols != cols - 1 && hasPath1(matrix, rows, cols, str, loc + 1, index + 1, visited)
        ) {
            return true;
        }

        visited[loc] = false;
        return false;
    }

    boolean hasPath2(char[] matrix, int rows, int cols, char[] str) {
        // 失败，由于无法过滤无效格子
        int[] stack = new int[str.length];
        int index = -1; // stack 目前的位置

        while (index != str.length - 1) {
            if (index == -1) {
                index++;
                stack[index] = findFirst(matrix, stack[index], str[index]);
            } else if (stack[index] / cols != 0 && matrix[stack[index] - cols] != ' ' && matrix[stack[index] - cols] == str[index + 1]) {
                stack[++index] = stack[index - 1] - cols;
            } else if (stack[index] / cols != rows - 1 && matrix[stack[index] + cols] != ' ' && matrix[stack[index] + cols] == str[index + 1]) {
                stack[++index] = stack[index - 1] + cols;
            } else if (stack[index] % cols != 0 && matrix[stack[index] - 1] != ' ' && matrix[stack[index] - 1] == str[index + 1]) {
                stack[++index] = stack[index - 1] - 1;
            } else if (stack[index] % cols != cols - 1 && matrix[stack[index] + 1] != ' ' && matrix[stack[index] + 1] == str[index + 1]) {
                stack[++index] = stack[index - 1] + 1;
            } else {
                matrix[stack[index]] = str[index];
                index--;
            }
            if (index >= 0) matrix[stack[index]] = ' ';
            else stack[0]++;
        }

        return true;
    }

    int findFirst(char[] matrix, int start, char cs) {
        for (int i = start; i < matrix.length; i++) {
            if (matrix[i] == cs) return i;
        }
        return -1;
    }

    /**
     * [机器人的运动范围_牛客网]( https://www.nowcoder.com/practice/6e5207314b5241fb83f2329e89fdecc8 )
     */
    @Test
    public void No13() {
        System.out.println(movingCount(10, 1, 100));
    }

    int movingCount(int threshold, int rows, int cols) {
        int count = 0;
        int limit = getLimit(threshold);
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows && i <= limit; i++) {
            for (int j = 0; j < cols && j <= limit - i; j++) {
                if ((getSum(i) + getSum(j)) <= threshold) count++;
            }
        }
        return count;
    }

    int getSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    int getLimit(int threshold) {
        int limit = 0;
        int i = 0;
        threshold++;
        for (; threshold > 9; i++) {
            limit += 9 * Math.pow(10, i);
            threshold -= 9;
        }
        limit += threshold * Math.pow(10, i);
        return limit;
    }

    /**
     * [剪绳子_牛客网]( https://www.nowcoder.com/practice/57d85990ba5b440ab888fc72b0751bf8 )
     */
    @Test
    public void No14() {
        Assert.assertEquals(18, cutRope(8));
        Assert.assertEquals(36, cutRope(10));
    }

    int cutRope(int target) {
        if (target == 2) return 1;
        if (target == 3) return 2;

        int result = 1;
        while (target >= 5) {
            result *= 3;
            target -= 3;
        }
        result *= target;

        return result;
    }

    /**
     * [二进制中1的个数_牛客网]( https://www.nowcoder.com/practice/8ee967e43c2c4ec193b040ea7fbb10b8 )
     * todo
     */
    @Test
    public void No15() {
    }

    int NumberOf1(int n) {
        return 0;
    }

    /**
     * [数值的整数次方_牛客网]( https://www.nowcoder.com/practice/1a834e5e3e1a4b7ba251417554e07c00 )
     */
    @Test
    public void No16() {

    }

    double Power(double base, int exponent) {
        return Math.pow(base, exponent);
    }

    @Test
    public void No17() {

    }

    @Test
    public void No18_1() {

    }

    /**
     * [删除链表中重复的结点_牛客网]( https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef )
     */
    @Test
    public void No18_2() {
        deleteDuplication(new ListNode(1));
    }

    ListNode deleteDuplication(ListNode pHead) {
        ListNode root = new ListNode(0);
        root.next = pHead;
        pHead = root;
        while (pHead.next != null && pHead.next.next != null) {
            ListNode node = pHead.next;
            if (node.val == node.next.val) {
                ListNode next = node.next;
                while (next != null && node.val == next.val) {
                    next = next.next;
                }
                pHead.next = next;
            } else {
                pHead = pHead.next;
            }
        }

        return root.next;
    }

    /**
     * [正则表达式匹配_牛客网]( https://www.nowcoder.com/practice/45327ae22b7b413ea21df13ee7d6429c )
     */
    @Test
    public void No19() {

    }

    boolean match(char[] str, char[] pattern) {
        if (str.length == 0 && pattern.length == 0) return true;
        return matchCore(str, pattern, 0, 0);
    }

    private boolean matchCore(char[] str, char[] pattern, int s, int p) {
        if (s == str.length && p == pattern.length) return true;

        if (s < str.length && p == pattern.length) return false;

        if (pattern.length == 2 && pattern[0] == '.' && pattern[1] == '*') {
            return true;
        }

        if (p + 1 < pattern.length && pattern[p + 1] == '*') {
            if ((s != str.length && pattern[p] == str[s]) || (pattern[p] == '.' && s != str.length)) {
                return matchCore(str, pattern, s, p + 2)//模式后移2，视为x*匹配0个字符
                        || matchCore(str, pattern, s + 1, p + 2)//视为模式匹配1个字符
                        || matchCore(str, pattern, s + 1, p);//*匹配1个，再匹配str中的下一个
            } else {
                return matchCore(str, pattern, s, p + 2);
            }
        }

        if ((s != str.length && pattern[p] == str[s]) || (pattern[p] == '.' && s != str.length)) {
            return matchCore(str, pattern, s + 1, p + 1);
        }
        return false;
    }
}
