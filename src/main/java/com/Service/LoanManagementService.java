package com.Service;

import com.DAO.LoanManagementDao;
import com.Model.ProductRes;

import java.sql.SQLException;
import java.util.List;

public class LoanManagementService {

    private final LoanManagementDao loanManagementDao;

    public LoanManagementService(){
        this.loanManagementDao=new LoanManagementDao();
    }

    public List<ProductRes> selectAllProducts() throws SQLException {
        return loanManagementDao.selectAllProducts();
    }

    public List<ProductRes> selectProductsByOption(String option1) throws SQLException {
        return loanManagementDao.selectProductsByOption(option1);
    }

    public void deleteProduct(int productId) throws SQLException, ClassNotFoundException {
        loanManagementDao.deleteProduct(productId);
    }
}
