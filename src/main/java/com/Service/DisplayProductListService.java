package com.Service;

import com.DAO.ProductDao;
import com.Model.Product;
import java.sql.SQLException;
import java.util.List;

public class DisplayProductListService {
    private final ProductDao productDao;

    public DisplayProductListService(){
        this.productDao = new ProductDao();
    }

    public List<Product> getProducts(String category) throws SQLException{
        return productDao.getProducts(category);
    }
}
