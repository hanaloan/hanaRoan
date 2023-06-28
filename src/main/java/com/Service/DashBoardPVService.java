package com.Service;

import com.DAO.DashBoardPVDao;
import com.DAO.UpdateRedisViewsDao;
import com.Model.*;

import java.sql.SQLException;

public class DashBoardPVService {

    private final DashBoardPVDao dashBoardPVDao;

    public DashBoardPVService() { dashBoardPVDao = new DashBoardPVDao(); }

    public DashBoardPVRes selectTotalPVUVService(DashBoardPVReq dashBoardPVReq) throws SQLException {

        DashBoardPVRes dashBoardPVRes = dashBoardPVDao.getTotalPageViews(dashBoardPVReq);
        return dashBoardPVRes;
    }



}
