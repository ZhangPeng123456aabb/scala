package com.alibaba.testredis;

import redis.clients.jedis.Jedis;

/**
 * @author ZhangPeng
 * @Email ZhangPeng1853093@126.com
 * @date 2020/1/16 - 23:36
 */
public class TestMS {
    public static void main(String[] args) {
        Jedis Jedis_M = new Jedis("Spark", 6379);
        Jedis Jedis_S = new Jedis("Spark", 6380);

        Jedis_S.slaveof("Spark",6379);

        Jedis_M.set("class","1902");
        System.out.println(Jedis_S.get("class"));
    }
}
