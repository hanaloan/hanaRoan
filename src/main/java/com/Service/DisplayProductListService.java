package com.Service;

import com.DAO.ProductDao;
import com.Model.DisplayProductListReq;
import com.Model.DisplayProductListRes;

import java.sql.SQLException;

public class DisplayProductListService {
    private final ProductDao productDao;

    public DisplayProductListService(){
        this.productDao = new ProductDao();
    }

    public DisplayProductListRes getProducts(DisplayProductListReq displayProductListReq) throws SQLException{
        return productDao.getProducts(displayProductListReq);
    }
}
