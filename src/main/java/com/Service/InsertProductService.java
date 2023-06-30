package com.Service;

import com.DAO.InsertProductDao;
import com.Model.ProductRes;
import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class InsertProductService {
    private InsertProductDao insertProductDao;

    public InsertProductService(){
        insertProductDao = new InsertProductDao();
    }

    public void insertProduct(ProductRes product) throws SQLException {
        insertProductDao.insertProduct(product);
    }

}