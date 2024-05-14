package com.aha.common.newfeature.java21.SequencedCollection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * SequencedMap 接口继承了 Map接口，
 * 提供了在集合两端访问、添加或删除键值对、
 * 获取包含 key 的 SequencedSet、包含 value 的 SequencedCollection、包含 entry（键值对） 的 SequencedSet以及获取集合的反向视图的方法。
 *
 * SortedMap 和LinkedHashMap 实现了SequencedMap 接口。
 */
public class SequencedMapTest {

    public static void main(String[] args) {

        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        // 1=One
        System.out.println(map.firstEntry());
        // 3=Three
        System.out.println(map.lastEntry());

        //{1=One, 2=Two, 3=Three}
        System.out.println(map);

        Map.Entry<Integer, String> first = map.pollFirstEntry();   //1=One
        Map.Entry<Integer, String> last = map.pollLastEntry();    //3=Three

        System.out.println(map);  //{2=Two}

        map.putFirst(1, "One");     //{1=One, 2=Two}
        map.putLast(3, "Three");    //{1=One, 2=Two, 3=Three}

        System.out.println(map);  //{1=One, 2=Two, 3=Three}
        System.out.println(map.reversed());   //{3=Three, 2=Two, 1=One}

    }

}
