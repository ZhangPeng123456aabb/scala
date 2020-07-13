package com.alibaba.testredis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author ZhangPeng
 * @Email ZhangPeng1853093@126.com
 * @date 2020/1/16 - 22:56
 */
public class TestAPI {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("Spark", 6380);
        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");
        System.out.println(jedis.get("k1"));
        Set<String> keys = jedis.keys("*");

        keys.stream().forEach(System.out::println);
    }
}
