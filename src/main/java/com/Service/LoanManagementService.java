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

    public List<Product> selectProducts(String option1) throws SQLException{
        return loanManagementDao.selectProducts(option1);
    }



}
