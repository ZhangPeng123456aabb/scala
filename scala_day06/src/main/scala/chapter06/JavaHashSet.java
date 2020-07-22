package chapter06;

import java.util.HashSet;

public class JavaHashSet {
    public static void main(String[] args) {
        //java中的set没有顺序，不可以重复
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.add("jack");
        hashSet.add("tom");
        hashSet.add("jack");
        hashSet.add("jack2");

        System.out.println(hashSet);
    }
}
