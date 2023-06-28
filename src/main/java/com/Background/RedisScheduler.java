package com.Background;

import com.Model.*;
import com.Service.RedisService;
import com.Service.UpdateRedisViewsService;

import java.sql.SQLException;
import java.util.List;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class RedisScheduler {
    private RedisService redisService;
    private final UpdateRedisViewsService updateRedisViewsService;
    private Timer timer;

    public RedisScheduler(RedisService redisService) {
        updateRedisViewsService = new UpdateRedisViewsService();
        this.redisService = redisService;
        this.timer = new Timer();
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                //최신 pv 업댓된 pk 구하는 로직
                List<String> preList = redisService.getPreList();
                if (preList != null && !preList.isEmpty()) {
                    try {
                        RedisViewsReq redisPageViewsReq = new RedisViewsReq();
                        for (String customerIdx : preList) {
                            Integer currentPageViews = redisService.getPageView(customerIdx);
                            if (currentPageViews != null) {
                                System.out.println("지금 바뀐 cus_idx : " + customerIdx + "이고 pv는 " + currentPageViews + "로 바뀌었습니다.");
                                // RedisViewsReq 객체에 값 추가
                                redisPageViewsReq.addPageView(Integer.parseInt(customerIdx), currentPageViews);
                            }else {
                                System.out.println("Invalid data for user: " + customerIdx);
                            }
                        }

                        updateRedisViewsService.updatePageViewsService(redisPageViewsReq);
                        updateRedisViewsService.insertUniqueVisitorsService(redisPageViewsReq);

                    } catch (NumberFormatException e) {
                        System.out.println("Error updating page views: " + e.getMessage());
                    } catch (SQLException e) {
                        System.out.println("Error updating page views: " + e.getMessage());
                    } finally {
                        redisService.clearPreList();
                    }
                }

                // 토탈 pk 구하는 로직
                Set<String> allUserIds = redisService.getAllKeys();
                int totalPageViews = 0;
                // 모든 key값 돌면서 total pv구하기
                for (String userId : allUserIds) {
                    try {
                        Integer pageViews = redisService.getPageView(userId);
                        if (pageViews != null) {
                            totalPageViews += pageViews;
                        }
                    } catch (NumberFormatException e) {
//                        System.out.println("Invalid data for user: " + userId);
                        continue;
                    }
                }
                try {
                    RedisViewsReq redisPageViewsReq = new RedisViewsReq();
                    redisPageViewsReq.setTotalPageViews(totalPageViews);
                    updateRedisViewsService.updateTotalViewsService(redisPageViewsReq);
                } catch (SQLException e) {
                    System.out.println("Error updating total page views: " + e.getMessage());
                }
            }
        }, 0, 3000); // 3초마다 실행
    }
    public void close() {
        timer.cancel();
        timer.purge();
    }


}