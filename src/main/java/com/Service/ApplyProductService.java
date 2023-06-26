package com.Service;

import com.DAO.ApplyProductDao;
import com.Model.ApplyProductRes;

import java.sql.SQLException;

public class ApplyProductService {

    private final ApplyProductDao applyProductDao;

    public ApplyProductService() {this.applyProductDao = new ApplyProductDao();}

    public ApplyProductRes getApplyInfo(String productId) throws SQLException {
        return applyProductDao.getApplyInfo(productId);
    }

    public void applyProduct(String loanIdx, String customerIdx, String lendAmount) throws SQLException {
        applyProductDao.applyProduct(loanIdx, customerIdx, lendAmount);
    }
}
