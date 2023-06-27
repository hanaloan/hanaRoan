package com.Service;

import com.DAO.DashBoardDao;

import java.sql.SQLException;
import java.util.Map;

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

    public Map<String, Integer> getCountPaymentStatus() throws SQLException {
        return dashBoardDao.getCountPaymentStatus();
    }

    public Map<String, String> getRatioOfLoanType() throws SQLException {
        return dashBoardDao.getRatioOfLoanType();
    }
}