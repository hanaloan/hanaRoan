package com.Controller;

import com.Service.DisplayProductListService;
import com.Model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/productList")
public class ProductListController extends HttpServlet {
    private DisplayProductListService displayProductListService;

    @Override
    public void init() throws ServletException {
        super.init();
        displayProductListService = new DisplayProductListService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String category = request.getParameter("category");
            if (category == null || category.isEmpty()) {category = "*";}

            List<Product> productList = displayProductListService.getProducts(category);
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("jsp/DisplayProduct/ProductList.jsp").forward(request, response);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }

    }
}
