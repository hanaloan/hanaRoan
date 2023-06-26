package com.Controller;

import com.Model.Product;
import com.Service.LoanManagementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        int productId = Integer.parseInt(req.getParameter("productId"));

        System.out.println("Post들어감");

        // Perform delete operation using productId
        try {
            loanManagementService.deleteProduct(productId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Redirect back to the LoanManagement page
        resp.sendRedirect("/LoanManagement");
        System.out.println("Post나감");
    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int productId = Integer.parseInt(req.getParameter("productId"));
//
//        try {
//            loanManagementService.deleteProduct(productId);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        resp.sendRedirect("LoanManagement");
//
//    }
}
