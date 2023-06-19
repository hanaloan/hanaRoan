package com.Controller;

import com.DAO.ProductDao;
import com.Model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProductList")
public class ProductListController {
    private ProductDao productDao;

    public void init() {
        productDao = new ProductDao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDao.getItems();

        request.setAttribute("products", products);
        request.getRequestDispatcher("jsp/DisplayProduct/ProductList.jsp").forward(request, response);
    }
}
