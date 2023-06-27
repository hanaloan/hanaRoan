package com.Controller;

import com.Model.CustomerManagement;
import com.Model.CustomerManagementReq;
import com.Service.LoanApprovalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/DashboardPending")
public class DashBoardGetPendingController extends HttpServlet {
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

            String loanStatus = "pending";

            List<CustomerManagement> customerManagementList = loanApprovalService.getCustomerInfo(null,loanStatus, request);

            HttpSession session = req.getSession(); // Create session
            session.setAttribute("PendingList", customerManagementList);

            req.setAttribute("PendingList", customerManagementList);

            req.getRequestDispatcher("/jsp/DashBoard/DashBoard.jsp").forward(req, resp);
        } catch (SQLException e) {
            // Handle exceptions and send an appropriate response
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}