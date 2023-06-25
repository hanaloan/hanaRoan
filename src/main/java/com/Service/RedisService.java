//package com.Service;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.exceptions.JedisException;
//
//import java.util.Set;
//
//public class RedisService {
//    private JedisPool jedisPool;
//
//    public RedisService(String host) {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxTotal(100); // 최대 커넥션 수
//        poolConfig.setMaxIdle(10); // 유휴 상태에서 유지하는 최대 커넥션 수
//        poolConfig.setMinIdle(5); // 유휴 상태에서 유지하는 최소 커넥션 수
//        poolConfig.setMaxWaitMillis(3000); // 커넥션 풀에서 사용 가능한 커넥션을 기다리는 최대 시간 (밀리초)
//
//        this.jedisPool = new JedisPool(poolConfig, host);
//    }
//
//    public void increasePageView(Integer customer_Idx) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            jedis.incr(String.valueOf(customer_Idx));
//        } catch (JedisException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    public int getPageView(String customer_Idx) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            String value = jedis.get(customer_Idx);
//            return value == null ? 0 : Integer.parseInt(value);
//        } catch (JedisException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    public Set<String> getAllKeys() {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.keys("*");
//        } catch (JedisException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//}