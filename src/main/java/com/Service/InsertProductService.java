package com.Service;

import com.DAO.InsertProductDao;
import com.Model.Product;

public class InsertProductService {
    private InsertProductDao insertProductDao;

    public InsertProductService(){
        insertProductDao = new InsertProductDao();
    }

    public void insertProduct(Product product){
        insertProductDao.insertProduct(product);
    }

}
