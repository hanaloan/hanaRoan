package com.Controller;

import com.Model.ProductRes;
import com.Service.DisplayProductDetailService;

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
            HttpSession session = request.getSession();
            int productId = Integer.parseInt(request.getParameter("id"));
            ProductRes product = displayProductDetailService.getProductById(productId);

            request.setAttribute("product", product);
            request.setAttribute("customerIdx", session.getAttribute("customer_Idx"));
            request.getRequestDispatcher("jsp/ProductDetail/ProductDetail.jsp").forward(request, response);
        } catch (SQLException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }
}
