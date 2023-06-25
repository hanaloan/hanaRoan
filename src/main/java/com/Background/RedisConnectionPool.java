//package com.Background;
//
//import com.Service.RedisService;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//public class RedisConnectionPool {
//    private static RedisConnectionPool instance = null;
//    private JedisPool pool;
//
//    private RedisConnectionPool() {
//
//        try {
//            JedisPoolConfig poolConfig = new JedisPoolConfig();
//            poolConfig.setMaxTotal(128);
//            poolConfig.setMaxIdle(128);
//            poolConfig.setMinIdle(16);
//            poolConfig.setTestOnBorrow(true);
//            poolConfig.setTestOnReturn(true);
//            poolConfig.setTestWhileIdle(true);
//            poolConfig.setNumTestsPerEvictionRun(10);
//            poolConfig.setTimeBetweenEvictionRunsMillis(60000);
//
//            URI redisUri = new URI("redis://localhost:6379");
//            pool = new JedisPool(poolConfig, redisUri.getHost(), redisUri.getPort());
//        }catch (URISyntaxException e) {
//            throw new RuntimeException("Invalid Redis URI");
//        }
//    }
//
//    //싱글턴 패턴으로 만든것 -> DTO랑 비슷한 패턴
//    public static synchronized RedisConnectionPool getInstance() {
//        if(instance == null) {
//            instance = new RedisConnectionPool();
//        }
//        return instance;
//    }
//
//    // 단일 커넥션이 아닌 pool 자체를 닫기 그리고 싱크로를 통해 여러 스레드가 동시에 이 메서드를 호출하려할때 한 번에 오직 하나의 스레드만 풀 닫기 처리
//    public static synchronized void closePool() {
//        if(instance != null && instance.pool != null) {
//            instance.pool.close();
//            instance = null;
//        }
//    }
//
//
//    // com/Service/RedisService의 인스턴스 생성하고 반환 -> 여기서 생성한 Jedis객체 이용해서
//    public RedisService getRedisService() {
//        return new RedisService(pool.getResource());
//    }
//
//
//}