//package com.Background;
//
//import com.Service.RedisService;
//
//import javax.servlet.ServletContextEvent;
//import java.util.List;
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
//
//                //최신 pv 업댓된 pk 구하는 로직
//                List<String> PreList = redisService.getPreList();
//                for (String customer_Idx : PreList) {
//                    try {
//                        Integer currentPageViews = redisService.getPageView(customer_Idx);
//                        System.out.println("바뀐 pk는 "+customer_Idx+"이고 pv는 "  + currentPageViews);
//
//                    } catch (NumberFormatException e) {
//                        System.out.println(e);
//                    }
//                }
//
//                redisService.clearPreList();
//
//                // 토탈 pk 구하는 로직
//                Set<String> allUserIds = redisService.getAllKeys();
//                int totalPageViews = 0;
//                // 모든 key값 돌면서 total pv구하기
//                for (String userId : allUserIds) {
//                    try {
//                        Integer pageViews = redisService.getPageView(userId);
//                        totalPageViews += pageViews;
//
//                    } catch (NumberFormatException e) {
//                        System.out.println(e);
//                    }
//                }
//                System.out.println("Total page views: " + totalPageViews);
//                // DB 로직 짜기
//
//
//            }
//        }, 0, 3000); // 3초마다 실행
//    }
//
//    public void close() {
//        timer.cancel();
//        timer.purge();
//    }
//}