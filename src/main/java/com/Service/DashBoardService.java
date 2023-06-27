package com.Service;

import com.DAO.DashBoardDao;

import java.sql.SQLException;

public class DashBoardService {

    private DashBoardDao dashBoardDao;

    public DashBoardService() {
        dashBoardDao = new DashBoardDao();
    }

    public String getLendData() throws SQLException {
        return dashBoardDao.getTotalLendData();
    }

    public String getOverdueLendData() throws SQLException {
        return dashBoardDao.getOverdueLendData();
    }

    public String getCountPendingLends() throws SQLException {
        return dashBoardDao.getCountPendingLends();
    }

    public String getOverduePercentage() throws SQLException {
        return dashBoardDao.getOverduePercentage();
    }
}