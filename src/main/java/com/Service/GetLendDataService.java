package com.Service;

import com.DAO.GetLendDataDao;

import java.sql.SQLException;

public class GetLendDataService {

    private GetLendDataDao getLendDataDao;

    public GetLendDataService() {
        getLendDataDao = new GetLendDataDao();
    }

    public String getLendData() throws SQLException {
        return getLendDataDao.getLendData();
    }
}