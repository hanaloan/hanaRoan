package com.Controller;

import com.Model.CustomerManagement;
import com.Model.CustomerManagementReq;
import com.Model.LoanApprovalRes;
import com.Service.LoanApprovalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/LoanApproval")
public class LoanApprovalController extends HttpServlet {
    private LoanApprovalService loanApprovalService;

    @Override
    public void init() throws ServletException {
        super.init();
        loanApprovalService = new LoanApprovalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String loanStatus = req.getParameter("loanStatus");

            List<LoanApprovalRes> loanApprovalResList;

            if (loanStatus != null && loanStatus.equals("pending")) {
                loanApprovalResList = loanApprovalService.getPendingLoanApprovalRes();
            } else {
                loanApprovalResList = loanApprovalService.loanApprovalRes();
            }

            req.setAttribute("loanApprovalResList", loanApprovalResList);

            req.getRequestDispatcher("/jsp/LoanApproval/LoanApproval.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}
