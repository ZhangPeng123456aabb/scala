package com.alibaba.testredis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ZhangPeng
 * @Email ZhangPeng1853093@126.com
 * @date 2020/1/17 - 0:05
 */
public class TestPool {
    public static void main(String[] args) {
        JedisPool jedisPoolInstance = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = null;

        try {
            jedis=jedisPoolInstance.getResource();
            jedis.set("aa","bb");
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            JedisPoolUtil.release(jedisPoolInstance,jedis);
        }
    }
}
