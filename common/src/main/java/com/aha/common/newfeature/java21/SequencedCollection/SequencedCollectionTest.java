package com.aha.common.newfeature.java21.SequencedCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * JDK 21 引入了一种新的集合类型：
 *   Sequenced Collections（序列化集合，也叫有序集合），
 *   这是一种具有确定出现顺序（encounter order）的集合（无论我们遍历这样的集合多少次，元素的出现顺序始终是固定的）。
 *   序列化集合提供了处理集合的第一个和最后一个元素以及反向视图（与原始集合相反的顺序）的简单方法。
 *
 *   List 和 Deque 接口实现了SequencedCollection 接口。
 *   这样可以明显的看出 list 都是有序的
 */
public class SequencedCollectionTest {

    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);   // List contains: [1]

        arrayList.addFirst(0);  // List contains: [0, 1]
        arrayList.addLast(2);   // List contains: [0, 1, 2]

        Integer firstElement = arrayList.getFirst();  // 0
        System.out.println(firstElement);
        Integer lastElement = arrayList.getLast();  // 2
        System.out.println(lastElement);

        List<Integer> reversed = arrayList.reversed();
        System.out.println(reversed); // Prints [2, 1, 0]

    }

}
