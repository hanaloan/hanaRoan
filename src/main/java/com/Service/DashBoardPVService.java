package com.Service;

import com.DAO.DashBoardPVDao;
import com.Model.*;

import java.sql.SQLException;

public class DashBoardPVService {

    private final DashBoardPVDao dashBoardPVDao;

    public DashBoardPVService() { dashBoardPVDao = new DashBoardPVDao(); }

    public DashBoardPVRes selectTotalPVUVService() throws SQLException {

        DashBoardPVRes dashBoardPVRes = dashBoardPVDao.getTotalPageViews();
        return dashBoardPVRes;
    }



}
