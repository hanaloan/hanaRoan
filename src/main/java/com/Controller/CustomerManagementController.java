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

            List<CustomerManagement> customerManageResDto = customerManageService.getCustomerInfo(customerManagementReq);

            req.setAttribute("customerManageResDto", customerManageResDto);

            req.getRequestDispatcher("/jsp/CustomerManagement/CustomerManagement.jsp").forward(req, resp);
        } catch (SQLException e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}