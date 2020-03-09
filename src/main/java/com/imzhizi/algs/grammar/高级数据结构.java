package com.imzhizi.algs.grammar;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

/**
 * created by zhizi
 * on 3/9/20 09:35
 */
public class 高级数据结构 {
    /**
     * HashMap<k,v>
     * 线程不安全，性能好，无序
     * 构成成分 table[]、size、threshold、loadFactor、modCount、capacity、TREEIFY_THRESHOLD
     * <p>
     * 主要通过 table[] 来储存数据，table中的每个结点都是一个 Node<k,v>
     * Node 是一个内部类，包含 final-k，v, final-hash 和 next
     * 可见每一个 Node 对象都是一个链表结点，table[] 就是一个头结点数组
     * <p>
     * 负载因子 loadFactor 用于判断 HashMap 是否需要 resize
     * 具体来说，loadFactor 表示当前 size 与 table[] 长度的比值，也就是当前数据桶的上座率
     * 一旦上座率超过了 loadFactor，意味着 table[] 中哈希冲突已经比较频繁，需要 resize
     * threshold 就是 capacity * loadFactor
     * modCount 主要用于 fail-fast 机制，之前已经讲过
     * <p>
     * put 方法的实现
     * 1. 对 key 的 hashCode 执行 hash()，保证所有的 null 都存在 0 位，然后将 hashcode 的低16位和 hashcode 的高16位异或
     * return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
     * 设计者认为这种方法开销低，而且可以有效减少碰撞，但这样得到的仍然不是 table[] 中的 index
     * 2. index = (capacity - 1) & hash，也就是将容量和 hash 做按位与操作，保证不会溢出的同时获得一个 index
     * 3. 根据 table[index] 的情况，存在几种情况
     * 如果 table[index] 为空，那么直接 newNode 即可，如果不为空，意味着更新结点/冲突
     * 如果 table[index] equals key，说明是要更新结点，直接改变 table[index] 的 value 即可
     * 如果 table[index] not equals key，说明是冲突了，就要在该结点的后续链表中查找需要更新结点还是新增结点
     * 接下来需要判断 table[index] 的类型，是 TreeNode 还是 Node，TreeNode 意味着是红黑树，Node 则意味着是链表
     * 如果是链表，就要逐个 equals 查找结点，存在则更新，不存在就需要新增结点，如果结点数量大于 TREEIFY_THRESHOLD，就要执行 treeifyBin() 转化为红黑树
     * 如果是红黑树，则需要二分查找结点，存在则更新，不存在就需要新增结点
     * TreeNode 是 HashMap 的内部类，继承自 LinkedHashMap.Entry<k,v>，Entry<k,v> 继承自 Node<k,v>
     * 含有 final-k，v, final-hash、next、before、after、prev、parent、left、right、red，具体见 LinkedHashMap
     * <p>
     * get 方法就没什么特别的了，跟put方法的查找过程类似
     * <p>
     * 具体如何扩容、数据迁移
     * 容量层面 newCapacity = oldCapacity << 1，接下来进行数据迁移，理论上数据的迁移需要重新计算 hash 才能得到新的 index
     * 根据 e.hash & oldCap == 0 判断数据放到原位置还是平移 oldCap 长度的位置，无论是否冲突均适用，为什么可以这样做？
     * 可以理解的是对象的 hash 是永远不会变的，而且长度为32位
     * cap 位数总是小于等于32的，碰巧一个对象和 cap 发生计算的位结果都是 0 才会平移
     * 对象移动后 newIndex 是 (oldCap - 1) & hash + OldCap
     * 而计算 newIndex = (newCap - 1) & hash = (OldCap + OldCap - 1) & hash
     * <p>
     * <p>
     * TREEIFY_THRESHOLD = 8
     * DEFAULT_INITIAL_CAPACITY = 16
     * MAXIMUM_CAPACITY = 1 << 30
     * DEFAULT_LOAD_FACTOR = 0.75f
     */
    @Test
    public void 哈希映射() throws NoSuchFieldException, IllegalAccessException {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 32; i++) {
            hashMap.put(i, i + 1);
        }
        System.out.println(hashMap);
    }

    /**
     * LinkedHashMap
     * 主要继承自 HashMap
     */
    @Test
    public void 有序哈希映射() {
        Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();

    }


    /**
     * HashTable
     * Hashtable是遗留类, 很多功能与HashMap相似, 不同的是它继承自 Dictionary 类, 是线程安全的, 任一时间只有一个线程能写 Hashtable
     * 但并发性不如 ConcurrentHashMap, 因为ConcurrentHashMap引入了分段锁, 所以Hashtable不建议在新代码中使用
     */
    @Test
    public void 哈希表() {

    }

    /**
     * TreeMap
     * 继承自 AbstractMap，实现了 NavigableMap 接口
     */
    @Test
    public void 树型映射() {
        Map<Integer, Integer> treeMap = new TreeMap<>();

    }


    /**
     * HashSet < Set
     * Set 继承自 Collection 接口，表示无重复元素的集合，因此必然是哈希的
     * 事实上，HashSet 内部实例化一个 HashMap 实现了哈希效果，所以特性都和 HashMap 一致
     * 像初始容量、负载因子 loadFactor 的设计
     * <p>
     * 但作为一个集合，Set 只关心 HashMap 中的 key，value 使用任意一个对象占位即可
     * 这个占位对象就是 private static final Object PRESENT = new Object();
     * <p>
     * 在 add() 的时候存在一些特别情况，对于 HashMap 而言，put() 既承担新增，也承担更新的工作
     * 但对于 Set 而言，不存在更新，所以为 Set add 一个已经存在的对象时，HashMap 会尝试更新该对象
     * 根据 HashMap put 方法的定义，如果之前不存在该 key，会返回 null，如果存在，则会返回之前的 value
     * 所以通过判断 HashMap put 的结果，返回 true/false
     */
    @Test
    public void 哈希集合() {
        Set<Integer> hashSet = new HashSet<>();
        System.out.println("set.add(3) " + hashSet.add(3));
        System.out.println("set.add(1) " + hashSet.add(1));
        System.out.println("set.add(3) " + hashSet.add(3));
        System.out.println(hashSet);
    }

    /**
     * LinkedHashSet
     * 方法主要继承自 HashSet，但却能够做到保持输入元素的有序
     * 究竟是如何做到的呢？查看源码可以看到 LinkedHashSet 内部代码是非常少的
     * 从构造方法中发现了端倪，里面出现了一个 true，这个 true 被称为 dummy
     * public LinkedHashSet(int initialCapacity, float loadFactor) { super(initialCapacity, loadFactor, true); }
     * super 指的不是别人，就是 HashSet，再看 HashSet 的构造方法，通过 dummy 重载方法竟然偷梁换柱把 HashMap 变成了 LinkedHashMap
     * 其他的前端方法仍然不变，但 base 已经不同了
     */
    @Test
    public void 有序哈希集合() {
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(3);
        linkedHashSet.add(1);
        linkedHashSet.add(4);
        System.out.println(linkedHashSet);
    }

    @Test
    public void 树集合() {
        Set<Integer> treeSet = new TreeSet<>();


    }
}
