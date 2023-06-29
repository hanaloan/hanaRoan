package com.Controller;

import com.Model.Product;
import com.Service.LoanManagementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/LoanManagement")
public class LoanManagementController extends HttpServlet {
    private LoanManagementService loanManagementService;

    @Override
    public void init() throws ServletException {
        super.init();
        loanManagementService = new LoanManagementService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option1 = req.getParameter("option1");

        List<Product> loanProductsDto;

        if (option1 == null || option1.isEmpty() || option1.equals("전체")){
            try {
                loanProductsDto=loanManagementService.selectAllProducts();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                loanProductsDto = loanManagementService.selectProductsByOption(option1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        req.setAttribute("loanProductsDto",loanProductsDto );
        req.getRequestDispatcher("jsp/LoanManagement/LoanManagement.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdParam = req.getParameter("productId");
        if (productIdParam == null || productIdParam.isEmpty()) {
            throw new IllegalArgumentException("Invalid productId");
        }

        int productId = Integer.parseInt(productIdParam);

        // productId를 사용하여 삭제 작업 수행
        try {
            loanManagementService.deleteProduct(productId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 대출 관리 페이지로 다시 리디렉션
        resp.sendRedirect("/LoanManagement");
    }

}
