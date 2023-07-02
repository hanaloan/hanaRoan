package com.Service;

import com.DAO.ProductDao;
import com.Model.DisplayProductDetailReq;
import com.Model.DisplayProductDetailRes;
import com.Model.ProductRes;

import java.sql.SQLException;

public class DisplayProductDetailService {
    private final ProductDao productDao;

    public DisplayProductDetailService() {
        this.productDao = new ProductDao();
    }

    public DisplayProductDetailRes getProductDetail(DisplayProductDetailReq productDetailReq) throws SQLException{
        return productDao.getProductDetail(productDetailReq);
    }
}
