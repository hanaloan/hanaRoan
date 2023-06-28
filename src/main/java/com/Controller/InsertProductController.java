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
import java.sql.SQLException;

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
        System.out.println("doPost 왔음");


        try{
            String productName=req.getParameter("productName");
            String productTypeName = req.getParameter("productTypeName");
            String productInfo=req.getParameter("productInfo");

            BigDecimal interestRate = new BigDecimal(req.getParameter("interestRate"));
            BigDecimal overdueInterestRate = new BigDecimal(req.getParameter("overdueInterestRate"));
            BigDecimal limit = new BigDecimal(req.getParameter("limit"));
            Integer period= Integer.valueOf(req.getParameter("period"));
            Integer minCredit= Integer.valueOf(req.getParameter("minCredit"));

            Product product = new Product(productName, productTypeName,productInfo, interestRate, overdueInterestRate, limit, period, minCredit );
            insertProductService.insertProduct(product);
            System.out.println("컨트롤러 끝");
            resp.sendRedirect("/jsp/LoanManagement/LoanManagement.jsp");
            System.out.println("컨트롤러에서 이동함");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while inserting the product.");
        }
    }
}
