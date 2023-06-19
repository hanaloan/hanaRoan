package com.Controller;

import com.DAO.ProductDao;
import com.Model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/ProductDetail")
public class ProductDetailController extends HttpServlet {
    private ProductDao productDao;

    public void init() {
        productDao = new ProductDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productDao.getItemById(productId);

        request.setAttribute("product", product);
        request.getRequestDispatcher("jsp/DisplayProduct/ProductDetail.jsp").forward(request, response);
    }
}
