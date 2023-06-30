//package com.Service;
//
//import com.Background.RedisConnectionPool;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.Transaction;
//import redis.clients.jedis.exceptions.JedisException;
//
//import java.util.List;
//import java.util.Set;
//
//public class RedisService {
//    private Jedis jedis;
//    private JedisPool pool;
//
//    public RedisService(Jedis jedis, JedisPool pool) {
//        this.jedis = jedis;
//        this.pool = pool;
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
//
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
//
//    public void setPageView(String userId, int value) {
//        while (true) {
//            boolean success = this.setPageViewWithWatch(userId, value);
//            if (success) {
//                break;
//            }
//            // 실패한 경우, 다른 클라이언트가 키 값을 변경하였으므로 다시 시도(동시성 배제)
//        }
//    }
//
//    public boolean setPageViewWithWatch(String userId, int value) {
//        Jedis jedis = null;
//        try {
//            jedis = pool.getResource();
//            jedis.watch(userId);
//            String valueStr = jedis.get(userId);
//            if (valueStr != null) {
//                Transaction t = jedis.multi();
//                t.set(userId, String.valueOf(value));
//                List<Object> result = t.exec();
//                return (result != null && !result.isEmpty());
//            }
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//        return false;
//    }
//
//    // 풀이아닌 풀 안에있는 단일 커넥션 제거
//    public void close() {
//        if(jedis != null) {
//            jedis.close();
//        }
//    }
//}