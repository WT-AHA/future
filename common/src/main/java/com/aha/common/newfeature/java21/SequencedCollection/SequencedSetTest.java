package com.aha.common.newfeature.java21.SequencedCollection;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * SortedSet 和 LinkedHashSet 实现了SequencedSet接口。
 *
 * 所以 SortedSet 和 LinkedHashSet 是有序的
 */
public class SequencedSetTest {

    public static void main(String[] args) {

        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(List.of(1, 2, 3));

        Integer firstElement = linkedHashSet.getFirst();   // 1
        System.out.println(firstElement);
        Integer lastElement = linkedHashSet.getLast();    // 3
        System.out.println(lastElement);

        linkedHashSet.addFirst(0);  //List contains: [0, 1, 2, 3]
        linkedHashSet.addLast(4);   //List contains: [0, 1, 2, 3, 4]

        System.out.println(linkedHashSet.reversed());

    }

}
