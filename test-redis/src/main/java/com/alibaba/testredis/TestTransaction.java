package com.alibaba.testredis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author ZhangPeng
 * @Email ZhangPeng1853093@126.com
 * @date 2020/1/16 - 23:12
 */
public class TestTransaction {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("Spark", 6380);
        Transaction ts = jedis.multi();

        ts.set("k4","v44");
        ts.set("k5","v55");

        ts.exec();
//        ts.discard();
    }
}
