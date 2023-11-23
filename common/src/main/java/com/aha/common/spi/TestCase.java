package com.aha.common.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class TestCase {

    public static void main(String[] args) {

        System.out.println(System.getProperties());

        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
            Search search =  iterator.next();
            search.searchDoc("hello world");
        }
    }

}
