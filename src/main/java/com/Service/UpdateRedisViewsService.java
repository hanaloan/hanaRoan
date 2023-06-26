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

}
