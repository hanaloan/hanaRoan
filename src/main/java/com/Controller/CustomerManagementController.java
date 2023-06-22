package com.Controller;

import com.Model.CustomerManagement;
import com.Model.CustomerManagementReq;
import com.Service.CustomerManageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CustomerManagement")
public class CustomerManagementController extends HttpServlet {
    private CustomerManageService customerManageService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerManageService = new CustomerManageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CustomerManagementReq customerManagementReq = new CustomerManagementReq();

            String loanType = req.getParameter("loanType");
            customerManagementReq.setLoanType(loanType);

            if (loanType == null || loanType.isEmpty()) {
                customerManagementReq.setLoanType(null);
            }

            List<CustomerManagement> customerManageResDto = customerManageService.getCustomerInfo(loanType, customerManagementReq);

            req.setAttribute("customerManageResDto", customerManageResDto);

            req.getRequestDispatcher("/jsp/CustomerManagement/CustomerManagement.jsp").forward(req, resp);
        } catch (SQLException e) {
            // 예외 발생 시 응답
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            CustomerManagementReq customerManagementReq = new CustomerManagementReq();

            String loanType = req.getParameter("loanType");
            customerManagementReq.setLoanType(loanType);

            // Create Customer Data 액션을 실행
            customerManageService.getCustomerInfo(loanType,customerManagementReq);

            // Create Customer Data 액션이 완료되면 다른 페이지로 리다이렉트 또는 응답할 수 있습니다.
            resp.sendRedirect("/jsp/CustomerManagement/CustomerManagement.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}