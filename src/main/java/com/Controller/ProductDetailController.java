package com.Controller;

import com.Service.DisplayProductDetailService;
import com.Model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/productDetail")
public class ProductDetailController extends HttpServlet {
    private DisplayProductDetailService displayProductDetailService;

    @Override
    public void init() throws ServletException {
        super.init();
        displayProductDetailService = new DisplayProductDetailService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = displayProductDetailService.getProductById(productId);

            request.setAttribute("product", product);
            request.getRequestDispatcher("jsp/DisplayProduct/ProductDetail.jsp").forward(request, response);
        } catch (SQLException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }
}
