package com.Controller;

import com.Model.Product;
import com.Service.InsertProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/InsertProduct")
public class InsertProductContorller extends HttpServlet {
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
            String productType = req.getParameter("productType");
            String productInfo=req.getParameter("productInfo");
            BigDecimal interestRate= BigDecimal.valueOf(Long.parseLong(req.getParameter("interestRate")));
            BigDecimal overdueInterestRate=BigDecimal.valueOf(Long.parseLong(req.getParameter("overdueInterestRate")));
            BigDecimal limit = BigDecimal.valueOf(Long.parseLong(req.getParameter("limit")));
            Integer period= Integer.valueOf(req.getParameter("period"));
            Integer minCredit= Integer.valueOf(req.getParameter("minCredit"));

            Product product = new Product(productName, productType,productInfo, interestRate, overdueInterestRate, limit, period, minCredit );

            insertProductService.insertProduct(product);
            resp.sendRedirect("/jsp/LoanManagement/LoanManagement");

        }catch (Exception e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}
