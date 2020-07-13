package com.baizhi.quickstart.hbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Hello {

    private int num;

    public static void main(String[] args) {
        int k=25;
        getList();
        System.out.println("args = [" + args + "]");
        System.out.println("Hello.main");
        int num = 10;
        System.out.println("num = " + num);
        System.out.println(num);
        String[] arr1 = new String[]{"Tom","LiHua","Marry","Pony"};
        /*for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);

        }*/
        for (String s : arr1) {
            System.out.println(s);
        }
        for (int i = 0; i < arr1.length; i++) {
            String s = arr1[i];
            System.out.println(arr1[i]);
        }
    }

    public static void getList() {
        ArrayList<String> arr = new ArrayList<>();
        if (arr != null) {
            System.out.println("arr = " + arr);
        }
        if (arr == null) {
            System.out.println("arr = " + arr);
        }
        //region Description
        arr.add("张朋");
        arr.add("张海坡");
        arr.forEach(System.out::println);
        for (String s : arr) {
            System.out.println("s = " + s);
        }
        for (int i = 0; i < arr.size(); i++) {
            System.out.println("i = " + i);
        }
        for (int i = arr.size() - 1; i >= 0; i--) {
            System.out.println("i = " + i);
        }
        //endregion
        System.out.println("Hello");
        Hello hello = new Hello();
        hello.method();
    }

    public void method(){
        num = 0;
        System.out.println("Hello World");
    }

}
class Student{
    //region Description
    private int id;
    private String name;
    private String sex;
    FileInputStream in;
    private static final int i=0;
    public static final int k=23;
    public static final String kk="JJ";
    //endregion
    {
        try {
            in = new FileInputStream("name.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}