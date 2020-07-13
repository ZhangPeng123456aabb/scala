package com.baizhi.quickstart.hbase;

import java.util.HashMap;

/**
 * @author ZhangPeng
 * @Email ZhangPeng1853093@126.com
 * @date 2020/1/14 - 22:51
 */
public class DebugTest {
    public static void main(String[] args) {
        for (int i = 0; i <100 ; i++) {
            System.out.println("i = " + i);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age","12");
        map.put("School","TsingHua");
        map.put("Major","compute");

        String name = map.get("name");
        System.out.println("name = " + name);

        map.remove("Major");
        System.out.println(map);
    }
}
