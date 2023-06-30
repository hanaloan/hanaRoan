package com.Service;

import com.DAO.ProductDao;
import com.Model.ProductRes;

import java.sql.SQLException;

public class DisplayProductDetailService {
    private final ProductDao productDao;

    public DisplayProductDetailService() {
        this.productDao = new ProductDao();
    }

    public ProductRes getProductById(int productId) throws SQLException{
        return productDao.getProductById(productId);
    }
}
