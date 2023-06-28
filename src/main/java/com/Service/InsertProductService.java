package com.Service;

import com.DAO.InsertProductDao;
import com.Model.Product;
import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class InsertProductService {
    private InsertProductDao insertProductDao;

    public InsertProductService(){
        insertProductDao = new InsertProductDao();
    }

    public void insertProduct(Product product){
        System.out.println("서비스 왔음");
        insertProductDao.insertProduct(product);
        System.out.println("서비스 나옴");
    }

}