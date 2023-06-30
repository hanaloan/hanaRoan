package com.Service;

import com.DAO.UpdateRedisViewsDao;
import com.Model.*;

import java.sql.SQLException;

public class UpdateRedisViewsService {

    private final UpdateRedisViewsDao updateRedisViewsDao;

    public UpdateRedisViewsService() { updateRedisViewsDao = new UpdateRedisViewsDao(); }

    public void updatePageViewsService(RedisViewsReq redisViewsReq) throws SQLException {

        updateRedisViewsDao.updatePageViews(redisViewsReq);
    }

    public void insertUniqueVisitorsService(RedisViewsReq redisViewsReq) throws SQLException {
        updateRedisViewsDao.insertUniqueVisitors(redisViewsReq);
    }

    public void updateTotalViewsService(RedisViewsReq redisViewsReq) throws SQLException{

        RedisViewsRes redisViewsRes = updateRedisViewsDao.checkDuplicationTotalViewsDate(redisViewsReq);

        // false라면(없다면) 새로 생성 ->  date랑, total_views
        if (redisViewsRes.getIsDateExist().booleanValue() == false){
            updateRedisViewsDao.insertTotalPageViews(redisViewsReq);
        }else{// true라면 업데이트
            System.out.println("total_page_views 갱신 : " + redisViewsReq.getTotalPageViews());
            updateRedisViewsDao.updateTotalPageViews(redisViewsReq);
        }

    }

}
