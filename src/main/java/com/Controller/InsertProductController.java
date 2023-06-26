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
            System.out.println(productName);
            System.out.println(productTypeName);
            String productInfo=req.getParameter("productInfo");
            BigDecimal interestRate= BigDecimal.valueOf(Long.parseLong(req.getParameter("interestRate")));
            BigDecimal overdueInterestRate=BigDecimal.valueOf(Long.parseLong(req.getParameter("overdueInterestRate")));
            BigDecimal limit = BigDecimal.valueOf(Long.parseLong(req.getParameter("limit")));
            Integer period= Integer.valueOf(req.getParameter("period"));
            Integer minCredit= Integer.valueOf(req.getParameter("minCredit"));

            Product product = new Product(productName, productTypeName,productInfo, interestRate, overdueInterestRate, limit, period, minCredit );
            insertProductService.insertProduct(product);
            System.out.println("컨트롤러 끝");
            resp.sendRedirect("/jsp/LoanManagement/LoanManagement.jsp");
            System.out.println("컨트롤러에서 이동함");
        }catch (Exception e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}
