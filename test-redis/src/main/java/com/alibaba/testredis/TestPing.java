package com.alibaba.testredis;

import redis.clients.jedis.Jedis;

/**
 * @author ZhangPeng
 * @Email ZhangPeng1853093@126.com
 * @date 2020/1/16 - 22:42
 */
public class TestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("Spark", 6380);
        System.out.println(jedis.ping());
    }
}
