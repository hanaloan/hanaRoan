package com.Controller;

import com.Model.CustomerRes;
import com.Service.CustomerManagementService;

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
    private CustomerManagementService customerManagementService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerManagementService = new CustomerManagementService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<CustomerRes> customerResList = customerManagementService.getCustomerInfo();

            req.setAttribute("customerResList", customerResList);

            req.getRequestDispatcher("/jsp/CustomerManagement/CustomerManagement.jsp").forward(req, resp);
        } catch (SQLException e) {
            // 예외 발생 시 응답
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}