package com.alibaba.testredis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ZhangPeng
 * @Email ZhangPeng1853093@126.com
 * @date 2020/1/16 - 23:48
 */
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil(){}

    public static JedisPool getJedisPoolInstance()
    {
        if(null == jedisPool)
        {
            synchronized (JedisPoolUtil.class)
            {
                if(null == jedisPool)
                {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxIdle(1000);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100*1000);
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(poolConfig,"Spark",6379);
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisPool jedisPool,Jedis jedis)
    {
        if(null != jedis)
        {
            jedisPool.returnResourceObject(jedis);
        }
    }
}
