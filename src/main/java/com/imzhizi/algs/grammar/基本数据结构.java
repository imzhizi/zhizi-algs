package com.imzhizi.algs.grammar;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

/**
 * created by zhizi
 * on 3/7/20 16:02
 */
public class 基本数据结构 {

    /**
     * 通过 Arrays 创建 final list，无法修改
     */
    @Test
    public void 序列() {
        List<Integer> list = Arrays.asList(3, 1, 2, 4);
        Collections.sort(list);
        System.out.println(list);
    }

    /**
     * 朴实的 ArrayList
     * 线程不安全
     * 继承自 AbstractList，实现了 List、RandomAccess 接口
     * <p>
     * 本身基于对象数组 elementData[]
     * 设有当前容量 size 和继承于 AbstractList 的 modCount
     * 设有默认容量 DEFAULT_CAPACITY = 10
     * 设有最大容量 MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8
     * 设有静态变量 空数组、默认长度数组
     * 如果构造时不传参，那么直接使用默认数组，如果构造时容量为0，那么直接食用空数组
     * <p>
     * 如何扩容
     * 首先确认是否为空list，若为空可以直接扩充为默认长度
     * 扩容会改变数组结构所以需要 modCount++
     * 然后通过 grow 方法完成扩容
     * 扩容后的长度 newCapacity = oldCapacity + (oldCapacity >> 1)，相当于原来容量的1.5倍
     * 最后进行数据迁移，过程也不复杂，创建长度为新容量的数组，然后将原来的数据拷贝进来即可
     * <p>
     * 扩容中，方法间始终传递 minCapacity，minCapacity 为size+1
     * ensureCapacity(int minCapacity) grow(int minCapacity)
     * 若 newCapacity 过大、过小时，进行相应的边界处理
     * <p>
     * 对数组使用了 transient 标记，该标记意思是在对象序列化的时候会忽略该字段
     * 忽略的话如何做到序列化呢？有私有的 writeObject readObject 做更精细的控制
     * <p>
     * modeCount？
     * 每次 add、remove 数据时，都需要检查数组容量，避免溢出，还要 modCount++ 记录
     * modeCount 是为了记录 list 结构改变次数，借以实现 fail-fast 机制(似乎用于并发控制)
     * 具有函数 checkForComodification() 用于检查使用中 list 是否被改动过 (ArrayList.this.modCount != this.modCount)
     * 如果不相等，说明 list 在其他线程中可能被修改，出现并发错误
     * <p>
     * 关于 subList，是如何实现的呢？
     * 当我们调用 list.subList(fromIndex, toIndex) 时，可以获取一个新的 list
     * 这个 list 还是原来的 ArrayList 吗？并不，它是一个被称为 SubList 的内部类
     * SubList 并不拥有自己的数据，而是持有了ArrayList的引用
     * 同时还拥有 parentOffset、offset、size、modCount 这些 metadata
     */
    @Test
    public void 线性表() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(4);
        System.out.println(arrayList);
    }

    /**
     * 链表 LinkedList
     * 线程不安全、 双向链表
     * 继承自 AbstractSequentialList，实现了 List、Deque 接口
     * 其中 AbstractSequentialList 继承自 AbstractList，实现了顺序访问序列的一些基本方法
     * 而 Deque 则定义了双向队列的基本方法，既可以作为栈、又可以用作队列
     * <p>
     * 因为是链表，显然 LinkedList 并不是最小结点，真正的最小结点为内部类 Node
     * 设有内部变量 first、last，用作链表的头指针、尾指针
     * 设有当前容量 size 和继承于 AbstractList 的 modCount
     * <p>
     * 增加元素就是链表的扩容，一些基本的指针操作
     * 查找时会从两端一起查找，制定插入位置时会计算从哪端插入比较快
     * <p>
     * 由于只能顺序访问，所以查找效率较差，但作为队列、栈倒也可以用用
     * <p>
     * 和 ArrayList 一样，使用 modeCount、checkForComodification() 进行并发检测
     */
    @Test
    public void 链表() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(3);
        linkedList.add(1);
        linkedList.add(4);
        System.out.println("list peek " + linkedList.peek());
    }

    /**
     * 队列 LinkedList
     * 由于 LinkedList 实现了 Deque 接口，而 Deque 继承了 Queue
     * 所以 Queue 类型可以通过 LinkedList 实例化
     */
    @Test
    public void 队列() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(3);
        queue.add(1);
        queue.add(4);
        System.out.println(queue.peek());

    }

    /**
     * 双向队列 ArrayDeque
     * 其实 LinkedList 也是 Deque 的一个实现
     * 但还有另外一个实现就是 ArrayDeque
     * 线程不安全，不允许插入空值
     * 基于数组实现了无容量限制，自动扩容
     * 继承自 AbstractCollection，实现了 Deque
     * AbstractCollection 实现了 Collection，对于很多方法都有默认的实现
     * <p>
     * 设有对象数组 elements[]
     * 设有头指针 head、尾指针 tail
     * <p>
     * 提供了双端的插入、删除
     * 既然不是链表，那么指针如何计算呢？head、tail 默认为0
     * addFirst
     * head = (head - 1) & (elements.length - 1)
     * head = -1 & 15 = 11111111111111111111111111111111 & 1111 = 1111 = 15
     * head = 14 & 15 = 1110 & 1111 = 1110 = 14
     * head = 13 & 15 = 1101 & 1111 = 1101 = 13
     * addLast
     * tail = (tail + 1) & (elements.length - 1) = 1
     * tail = 0
     * tail = 1 & 15 = 1 & 1111 = 1 = 1
     * tail = 2 & 15 = 10 & 1111 = 10 = 2
     * 所以在实现层面，ArrayDeque 的head是尾部，tail却在头部，从两端向中间不断移动
     * 一旦 head==tail，说明需要扩容
     * <p>
     * ArrayDeque 要求容量必须是 2 的幂，最小容量 MIN_INITIAL_CAPACITY = 8，默认容量是 16
     * 可以在构造函数直接传入元素数量进行初始化
     * 若元素数量不为2的幂，需要通过 calculateSize() 计算，保证为2的幂
     * <p>
     * 如果需要扩容
     * 会执行 doubleCapacity 来使容量翻倍，然后通过 System.arraycopy() 迁移
     * 具体如何拷贝呢？
     * 由于 ArrayDeque 中 head 和 tail 的奇特构造，扩容后迁移时，会把原来的两端合并全部拷贝到新队列的左侧
     * System.arraycopy(elements, p, a, 0, r); head 从数组的最前端开始拷贝
     * System.arraycopy(elements, 0, a, r, p); tail 紧跟着前面的数据拷贝
     * 接着 head 变为 0，一旦 addFirst 就再一次来到数组的末端，而 tail 则从拷贝来数据的位置逐个 addLast
     * <p>
     * ArrayDeque 没有使用 modCount 机制来进行并发检查，而是通过比较指针位置实现，但无法防范污读问题？
     */
    @Test
    public void 双向队列() throws NoSuchFieldException, IllegalAccessException {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(3);
        deque.addFirst(1);
        deque.addLast(4);

        Class<ArrayDeque> dequeClass = ArrayDeque.class;
        Field head = dequeClass.getDeclaredField("head");
        head.setAccessible(true);
        System.out.println("head " + head.get(deque));

        Field tail = dequeClass.getDeclaredField("tail");
        tail.setAccessible(true);
        System.out.println("tail " + tail.get(deque));

    }

    /**
     * 栈 Stack
     * *** Vector 和 Stack 都已经不推荐使用，如果想使用栈，可以使用 ArrayDeque ***
     * <p>
     * Stack 继承自 Vector，主要功能都来自于 Vector
     * Vector 继承自 AbstractList，实现了 List、RandomAccess
     * Vector 可以通过索引随机访问，能够根据对象的增删而扩容或收缩
     * 和 ArrayList 的主要不同是 Vector 线程安全
     * 跟 ArrayList 类似，默认容量是 10
     * <p>
     * 主要方法都使用 synchronized 标注，保证线程安全
     * 扩容时使用 capacityIncrement 来标记步长
     * 如果未定义 capacityIncrement，则直接将容量翻倍
     * <p>
     * 同样使用 modCount 实现 fail-fast 机制
     */
    @Test
    public void 栈() {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        System.out.println("stack peek " + stack.peek());
    }

    /**
     * 线程安全的线性表 synchronizedList
     * 可以使用 Collections.synchronizedList 来包裹 List，得到线程安全的 List
     * 依赖内部类 SynchronizedList 保证同步
     * SynchronizedRandomAccessList 是实现了 RandomAccess 接口的 SynchronizedList
     */
    @Test
    public void 同步线性表() {
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        synchronizedList.add(3);
        synchronizedList.add(1);
        synchronizedList.add(4);
        System.out.println(synchronizedList);
    }

    /**
     * 基于优先队列 PriorityQueue
     * 可以实现大根堆、小根堆
     */
    @Test
    public void 堆() {
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
        smallHeap.offer(3);
        smallHeap.offer(1);
        smallHeap.offer(4);

        PriorityQueue<Integer> bigHead = new PriorityQueue<>((o1, o2) -> o2 - o1);
        bigHead.offer(3);
        bigHead.offer(1);
        bigHead.offer(4);

        System.out.println("small-head peek " + smallHeap.peek());
        System.out.println("big-head peek " + bigHead.peek());
    }
}
