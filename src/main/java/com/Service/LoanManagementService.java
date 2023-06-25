package com.Service;

import com.DAO.LoanManagementDao;
import com.Model.Product;

import java.sql.SQLException;
import java.util.List;

public class LoanManagementService {

    private final LoanManagementDao loanManagementDao;

    public LoanManagementService(){
        this.loanManagementDao=new LoanManagementDao();
    }



    public List<Product> selectAllProducts() throws SQLException {
        return loanManagementDao.selectAllProducts();
    }

    public List<Product> selectProductsByOption(String option1) throws SQLException {
        return loanManagementDao.selectProductsByOption(option1);
    }
}
