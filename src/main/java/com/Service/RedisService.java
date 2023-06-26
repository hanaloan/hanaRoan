//package com.Service;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.exceptions.JedisException;
//
//import java.util.List;
//import java.util.Set;
//
//public class RedisService {
//    private Jedis jedis;
//
//    public RedisService(Jedis jedis) {
//        this.jedis = jedis;
//    }
//
//    public void increasePageView(Integer customer_Idx) {
//        try {
//            jedis.incr(String.valueOf(customer_Idx));
//        } catch (JedisException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    public Integer getPageView(String customer_Idx) {
//        try {
//            String value = jedis.get(customer_Idx);
//            return value == null ? 0 : Integer.parseInt(value);
//        } catch (JedisException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    public Set<String> getAllKeys() {
//        try {
//            return jedis.keys("*");
//        } catch (JedisException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//    public void addToPreList(Integer customer_Idx) {
//        jedis.rpush("PreList", String.valueOf(customer_Idx));
//    }
//
//    //lrange jedis연결후 모든 리스트 조회
//    public List<String> getPreList() {
//        return jedis.lrange("PreList", 0, -1);
//    }
//
//    public void clearPreList() {
//        jedis.del("PreList");
//    }
//
//    // 풀이아닌 풀 안에있는 단일 커넥션 제거
//    public void close() {
//        if(jedis != null) {
//            jedis.close();
//        }
//    }
//}