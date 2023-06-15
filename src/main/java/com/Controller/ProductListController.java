package com.Controller;

import com.DAO.ProductDao;
import com.Model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductListController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getItems();

        request.setAttribute("products", products);
        request.getRequestDispatcher("jsp/DisplayProduct/ProductList.jsp").forward(request, response);
    }
}
