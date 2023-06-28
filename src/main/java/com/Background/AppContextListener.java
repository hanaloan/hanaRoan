//package com.Background;
//
//import com.Background.RedisConnectionPool;
//import com.Service.RedisService;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//@WebListener
//public class AppContextListener implements ServletContextListener {
//
//    private RedisScheduler redisScheduler;
//
//    // 웹 앱시작할때 자동시작하는 이벤트 메서드
//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        RedisConnectionPool redisConnectionPool = RedisConnectionPool.getInstance();
//        RedisService redisService = redisConnectionPool.getRedisService();
//        redisScheduler = new RedisScheduler(redisService);
//        redisScheduler.start();
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        if (redisScheduler != null) {
//            redisScheduler.close();
//        }
//        // 웹 애플리케이션이 종료될 때 Redis 커넥션 풀 닫기 -> 동기화처리
//        RedisConnectionPool.closePool();
//    }
//}