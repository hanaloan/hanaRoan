package com.Controller;

import com.Model.ProductRes;
import com.Service.InsertProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/InsertProduct")
public class InsertProductController extends HttpServlet {
    private InsertProductService insertProductService;

    @Override
    public void init() throws ServletException {
        super.init();
        insertProductService = new InsertProductService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try{
            String productName=req.getParameter("productName");
            String productTypeName = req.getParameter("productTypeName");
            String productInfo=req.getParameter("productInfo");
            BigDecimal interestRate = new BigDecimal(req.getParameter("interestRate"));
            BigDecimal overdueInterestRate = new BigDecimal(req.getParameter("overdueInterestRate"));
            BigDecimal limit = new BigDecimal(req.getParameter("limit"));
            Integer period= Integer.valueOf(req.getParameter("period"));
            Integer minCredit= Integer.valueOf(req.getParameter("minCredit"));

            ProductRes product = new ProductRes(productName, productTypeName,productInfo, interestRate, overdueInterestRate, limit, period, minCredit );
            insertProductService.insertProduct(product);
            resp.sendRedirect("/LoanManagement");
        } catch (Exception e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}