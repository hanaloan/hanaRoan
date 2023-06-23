package com.Controller;

import com.Model.CustomerManagement;
import com.Model.CustomerManagementReq;
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
            CustomerManagementReq request = new CustomerManagementReq();

            String loanType = req.getParameter("loanType");
            request.setLoanType(loanType);

            if (loanType == null || loanType.isEmpty()) {
                request.setLoanType(null);
            }

            List<CustomerManagement> customerManagementList = loanApprovalService.getCustomerInfo(loanType, request);

            req.setAttribute("customerManagementList", customerManagementList);

            req.getRequestDispatcher("/jsp/LoanApproval/LoanApproval.jsp").forward(req, resp);
        } catch (SQLException e) {
            // Handle exceptions and send an appropriate response
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}
