package com.Service;

import com.DAO.ProductDao;
import com.Model.Product;

import java.sql.SQLException;

public class DisplayProductDetailService {
    private final ProductDao productDao;

    public DisplayProductDetailService() {
        this.productDao = new ProductDao();
    }

    public Product getProductById(int productId) throws SQLException{
        return productDao.getProductById(productId);
    }
}
