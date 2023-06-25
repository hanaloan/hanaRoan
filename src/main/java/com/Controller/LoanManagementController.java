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
        try{
            String option1 = req.getParameter("option1") == null || req.getParameter("option1").isEmpty()?
                    "*" : req.getParameter("option1");

            List<Product> loanProductsDto=loanManagementService.selectProducts(option1);
            req.setAttribute("loanProductsDto",loanProductsDto );
            req.getRequestDispatcher("jsp/LoanManagement/LoanManagement.jsp").forward(req, resp);

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
            throw new RuntimeException(e);
        }
    }
}
