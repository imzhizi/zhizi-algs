package com.imzhizi.algs.语言基础;

import com.imzhizi.algs.base.LRUCache;
import org.junit.Test;

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
     * get 方法就没什么特别的了，跟 put 方法的查找过程类似
     * <p>
     * 具体如何扩容、数据迁移
     * HashMap 要求容量必须是2的幂，DEFAULT_INITIAL_CAPACITY = 16，扩容时 newCapacity = oldCapacity << 1
     * 确定好容量后，就要数据迁移，理论上数据的迁移需要重新计算 hash 才能得到新的 index，但这样性能太差，
     * 根据 hash & oldCap == 0 判断数据放到原位置还是平移 oldCap 长度的位置，无论是否冲突均适用，为什么可以这样做？
     * 可以理解的是对象的 hash 是永远不会变的，而且长度为32位
     * cap 位数总是小于等于32的，碰巧一个对象和 cap 发生计算的位结果都是 0 才会平移
     * 对象移动后时 newIndex = (oldCap - 1) & hash + OldCap
     * 而通过 get 访问时 newIndex = (newCap - 1) & hash = (OldCap + OldCap - 1) & hash
     * 相当于给原来的 index 左端加一位 1，刚好相当于 index + oldCap
     * <p>
     * TREEIFY_THRESHOLD = 8
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
     * 同时允许使用 null 值和 null 键，在结点增删查改方面、hash、index 计算、resize等都和HashMap没有什么区别
     * accessOrder 规定了记录顺序是插入顺序还是访问顺序，插入顺序即数据输入顺序，访问顺序是访问后将对象调至末尾
     * 主要的特点是使用 HashMap 的同时拥有可预测的迭代顺序
     * <p>
     * 如何做到保持元素顺序呢？
     * 主要是基于双向链表，LinkedHashMap 中使用 LinkedHashMap.Entry<K,V> 作为其结点实现
     * Entry<K,V> 则继承自 HashMap.Node<K,V>，在Node基础上，增加了 before 和 after
     * LinkedHashMap 构成成分还包括 head<K,V>、tail<K,V>，用于标记头结点和尾结点
     * <p>
     * 除此之外，还存在内部类 LinkedKeySet、LinkedValues、LinkedEntrySet
     */
    @Test
    public void 有序哈希映射() {
        Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put(1, null);
        linkedHashMap.put(3, null);
        linkedHashMap.put(5, null);
        System.out.println(linkedHashMap);
        linkedHashMap.get(3);
        System.out.println(linkedHashMap);
    }


    /**
     * 可以使用 LinkedHashMap 实现 LRUCache
     * 含有 map = new LinkedHashMap<K, V>(hashTableCapacity, hashTableLoadFactor, true);
     * true 表示 accessOrder，使 LinkedHashMap 记录访问顺序
     * 含有 cacheSize - 在缓存中最多可以保存多少对象（实际使用时要考虑 loadFactor）
     * 需要使用 synchronized 方法来包裹方法，保证 LinkedHashMap 的线程安全
     * <p>
     * 如何实现淘汰机制？
     * 一方面，HashMap 的 put 方法执行后会调用 afterNodeInsertion 方法
     * 这个方法在 HashMap 中为空，但在 LinkedHashMap 中被重写，执行后会检查 evict（是否执行淘汰机制）
     * 若执行淘汰机制，就需要判断当前是否需要淘汰一些年老结点 - 调用 removeEldestEntry 方法
     * 但 LinkedHashMap 中该方法返回值始终为 false，因此需要重写
     * 若结果为 true，即需要淘汰年老结点，会调用 removeNode(hash(key), key, null, false, true); 方法
     * <p>
     * 另一方面，HashMap 的 get 方法执行后会调用 afterNodeAccess 方法
     * 此方法在 HashMap 中同样为空并在 LinkedHashMap 中被重写，执行的结果是「被访问的结点被移动到链表末尾」
     */
    @Test
    public void LRUCache() {
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("1", "one"); // 1
        System.out.println(cache.getMap());
        cache.put("2", "two"); // 1 2
        System.out.println(cache.getMap());
        cache.put("3", "three"); // 1 2 3
        System.out.println(cache.getMap());
        cache.put("4", "four"); // 2 3 4
        System.out.println(cache.getMap());
        if (cache.get("2") == null) // move to last
            throw new Error(); // 3 4 2
        System.out.println(cache.getMap());
        cache.put("5", "five"); // 4 2 5
        System.out.println(cache.getMap());
        cache.put("4", "second four"); // 2 5 4
        System.out.println(cache.getMap());
    }


    /**
     * HashTable 已不建议在新代码中使用
     * Hashtable 和 HashMap 的功能非常相似，HashMap 主要是实现 Map<k,v> 接口，它则继承自已经弃用的 Dictionary 类
     * Hashtable 是线程安全的, 任一时间只有一个线程能写 Hashtable，但并发性不如 ConcurrentHashMap
     * 和 hashMap 的结构类似，通过内部类 Entry 存储数据，同样使用「拉链法」进行冲突控制，同样设计了 loadFactor 来进行 rehash
     * initialCapacity 默认为 11，loadFactor 默认为 0.75
     * 同样使用了 modCount，使用 count 计算 usedEntry 的数量
     * Hashtable 的 key 和 value 都不允许为 null，而 HashMap 进行了特殊处理
     * <p>
     * put 方法略有不同
     * 在发生冲突需要建立链表时，HashTable 会选择将新结点放在链表的首位
     * <p>
     * index 计算方法有所不同
     * 使用 (key.hashCode() & 0x7FFFFFFF) % tab.length 来计算 index
     * 在 rehash 后进行数据迁移时，每个对象的 index 都需要重新计算
     * <p>
     * HashTable 比较纯粹一些，HashMap 为了它的小弟们 —— HashSet、LinkedHashMap 做了许多支持
     */
    @Test
    public void 哈希表() {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        hashtable.put(3, 3);
        hashtable.put(1, 3);
        hashtable.put(4, 3);
        System.out.println(hashtable);
    }

    /**
     * TreeMap
     * 继承自 AbstractMap，实现了 NavigableMap 接口
     * TreeMap 可以在记录数据的同时保持内部数据的有序性，主要基于红黑树实现
     * 构成成分 root<K,V>、size、comparator、modCount
     * 其中 root<K,V> 属于内部类，实现了 Map 中的内部接口 Entry<K,V>
     * Entry<K,V> 拥有 k、v、left、right、parent、color
     * comparator 定义了 TreeMap 如何维护数据的顺序
     * <p>
     * 使用红黑树能够使树具有不错的平衡性
     * 在进行 get 时，执行二分查找，保持在 log(n) 水平
     * 在进行 put、remove 时，对树执行修改之外，还需要再平衡 fixAfterInsertion()、fixAfterDeletion()
     * <p>
     * 如何保持有序呢？类似于中序遍历？
     * 在进行遍历的时候，使用 successor() 方法，这个方法能够对树进行中序遍历
     * 通过 getCeilingEntry(key) 可以获得最接近于 key 的对象
     * <p>
     * 关于红黑树如何保持平衡，见笔记 [ 红黑树笔记 ](https://www.cnblogs.com/imzhizi/p/red-black-tree.html)
     */
    @Test
    public void 树型映射() {
        Map<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(1, 1);
        treeMap.put(3, 1);
        treeMap.put(7, 1);
        treeMap.put(2, 1);
        treeMap.put(4, 1);
        System.out.println(treeMap); // 1 2 3 4 7
    }


    /**
     * HashSet
     * 实现 Set 接口，Set 继承自 Collection 接口，表示无重复元素的集合，因此必然是哈希的
     * 事实上，HashSet 内部实例化一个 HashMap 实现了哈希效果，所以特性都和 HashMap 一致
     * 像初始容量、负载因子 loadFactor 的设计
     * <p>
     * 但作为一个集合，Set 只关心 HashMap 中的 key，value 使用任意一个对象占位即可
     * 这个占位对象就是 private static final Object PRESENT
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

    /**
     * 就像 HashSet 基于 HashMap 实现
     * treeSet 是基于 TreeMap 实现，构成成分也类似
     * 包含用于存储数据的 NavigableMap<E,Object> m
     * 包含占位对象 static finalObject PRESENT
     * 几乎所有方法都来自于 TreeMap
     */
    @Test
    public void 树集合() {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(5);
        System.out.println(treeSet);
    }
}
