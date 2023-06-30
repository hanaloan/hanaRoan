package com.Service;

import com.DAO.ApplyProductDao;
import com.Model.ApplyCustomerRes;
import com.Model.ApplyProductRes;

import java.sql.SQLException;

public class ApplyProductService {

    private final ApplyProductDao applyProductDao;

    public ApplyProductService() {this.applyProductDao = new ApplyProductDao();}

    public ApplyProductRes getApplyProductInfo(String productId) throws SQLException {
        return applyProductDao.getApplyProductInfo(productId);
    }

    public ApplyCustomerRes getApplyCustomerInfo(String customerIdx) throws SQLException {
        return applyProductDao.getApplyCustomerInfo(customerIdx);
    }

    public void applyProduct(String loanIdx, String customerIdx, String lendAmount) throws SQLException {
        applyProductDao.applyProduct(loanIdx, customerIdx, lendAmount);
    }
}
