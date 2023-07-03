package com.Controller;

import com.Model.*;
import com.Service.ApplyProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet("/applyProduct")
public class ApplyProductController extends HttpServlet {
    private ApplyProductService applyProductService;
    @Override
    public void init() throws ServletException {
        super.init();
        applyProductService = new ApplyProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession session = request.getSession();
            String productId = request.getParameter("productId");
            String customerIdx = session.getAttribute("customer_Idx").toString();
            ApplyProductRes productInfo = applyProductService.getApplyProductInfo(productId);
            ApplyCustomerRes customerInfo = applyProductService.getApplyCustomerInfo(customerIdx);
            request.setAttribute("productInfo", productInfo);
            request.setAttribute("customerInfo", customerInfo);
            request.getRequestDispatcher("jsp/ApplyProduct/ApplyProduct.jsp").forward(request, response);
        } catch (SQLException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        try{
            request.setCharacterEncoding("UTF-8");
            if(request.getParameter("customerIdx") != null
                    && request.getParameter("loanIdx") != null
                    && request.getParameter("lendAmount") != null){
                String loanIdx = request.getParameter("loanIdx");
                String customerIdx = request.getParameter("customerIdx");
                String lendAmount = request.getParameter("lendAmount");
                applyProductService.applyProduct(loanIdx, customerIdx, lendAmount);
            }
            response.sendRedirect("/CustomerHome");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}