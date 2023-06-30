//package com.Background;
//
//import com.Model.*;
//import com.Service.RedisService;
//import com.Service.UpdateRedisViewsService;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import java.util.Set;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class RedisScheduler {
//    private RedisService redisService;
//    private final UpdateRedisViewsService updateRedisViewsService;
//    private Timer timer;
//
//    public RedisScheduler(RedisService redisService) {
//        updateRedisViewsService = new UpdateRedisViewsService();
//        this.redisService = redisService;
//        this.timer = new Timer();
//    }
//
//    public void start() {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                List<String> preList = redisService.getPreList();
//                if (preList != null && !preList.isEmpty()) {
//                    try {
//                        int totalPageViews = 0;
//                        Set<String> allUserIds = redisService.getAllKeys();
//                        RedisViewsReq redisPageViewsReq = new RedisViewsReq();
//
//                        for (String customerIdx : allUserIds) {
//                            try {
//                                Integer pageViews = redisService.getPageView(customerIdx);
//                                if (pageViews != null) {
//                                    System.out.println("지금 바뀐 cus_idx : " + customerIdx + "이고 " + pageViews + " PV값은 DB에 더해집니다.");
//                                    // RedisViewsReq 객체에 값 추가
//                                    redisPageViewsReq.addPageView(Integer.parseInt(customerIdx), pageViews);
//                                    totalPageViews += pageViews;
//                                    // Redis의 값을 초기화
//                                    redisService.setPageView(customerIdx, 0);
//                                }
//                            } catch (NumberFormatException e) {
//                                System.out.println("Error in number format: " + e.getMessage());
//                            } catch (Exception e) {
//                                System.out.println("Error while processing customer id: " + customerIdx + ", error: " + e.getMessage());
//                            }
//                        }
//
//                        redisPageViewsReq.setTotalPageViews(totalPageViews);
//                        System.out.println("totalpageViews = " + totalPageViews);
//
//                        updateRedisViewsService.updatePageViewsService(redisPageViewsReq);
//                        updateRedisViewsService.insertUniqueVisitorsService(redisPageViewsReq);
//                        updateRedisViewsService.updateTotalViewsService(redisPageViewsReq);
//
//                    } catch (SQLException e) {
//                        System.out.println("Error updating page views: " + e.getMessage());
//                    } finally {
//                        redisService.clearPreList();
//                    }
//                }
//            }
//        }, 0, 3000); // 3초마다 실행
//    }
//
//
//    public void close() {
//        timer.cancel();
//        timer.purge();
//    }
//
//}