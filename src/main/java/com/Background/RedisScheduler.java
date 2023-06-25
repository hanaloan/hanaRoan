//package com.Background;
//
//import com.Service.RedisService;
//import java.util.Set;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class RedisScheduler {
//    private RedisService redisService;
//    private Timer timer;
//
//    public RedisScheduler(RedisService redisService) {
//        this.redisService = redisService;
//        this.timer = new Timer();
//    }
//
//    public void start() {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Set<String> allUserIds = redisService.getAllKeys();
//                int totalPageViews = 0;
//                // 모든 key값 돌면서 total pv구하기
//                for (String userId : allUserIds) {
//                    try {
//                        int pageViews = redisService.getPageView(userId);
//                        totalPageViews += pageViews;
//                    } catch (NumberFormatException e) {
//                    }
//                }
//                System.out.println("Total page views: " + totalPageViews);
//                // DB 로직 짜기
//            }
//        }, 0, 3000); // 3초마다 실행
//    }
//}