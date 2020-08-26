package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> list1 = new MyLinkedList<>();
        System.out.println("---check adding and getting");
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        System.out.println(list1.get(2));
        list1.add(2, "5");
        System.out.println(list1.get(2));
        System.out.println("---check removing");
        List<String> list2 = new MyLinkedList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.remove("4");
        list2.remove(0);
        System.out.println(list2.size());
        System.out.println(list2.get(1));
        System.out.println("---check indexOf");
        List<String> list3 = new MyLinkedList<>();
        list3.add("1");
        list3.add("2");
        list3.add("3");
        list3.add("4");
        System.out.println(list3.indexOf("1"));
        System.out.println(list3.indexOf("4"));
        System.out.println(list3.indexOf("10"));
        System.out.println("---checking iterator");
        for (String s : list3) {
            System.out.println(s);
        }
        System.out.println("---checking array");
        System.out.println(Arrays.toString(list3.toArray()));
        System.out.println(Arrays.toString(list3.subList(1,3).toArray()));
        System.out.println("---checking addingCollection");
        List<String> myList = new MyLinkedList<>();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        myList.add("10");
        myList.add("11");
        myList.add("12");
        myList.add("13");
        myList.add("14");
        myList.addAll( list);
        myList.forEach(System.out::println);
    }
}
