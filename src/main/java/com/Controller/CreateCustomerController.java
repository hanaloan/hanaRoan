package com.Controller;

import com.Model.CustomerManagement;
import com.Model.CustomerManagementReq;
import com.Service.CreateCustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CreateCustomer")
public class CreateCustomerController extends HttpServlet {
        private  CreateCustomerService createCustomerService;

        @Override
        public void init() throws ServletException {
            super.init();
            createCustomerService = new CreateCustomerService();
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            // Extract customer data from the request parameters
            String name = req.getParameter("name");
            String contactInfo = req.getParameter("contact_info");
            String cusId = req.getParameter("cus_id");
            String password = req.getParameter("password");

            // Create a CustomerManagementReq object
            CustomerManagementReq customerManagementReq = new CustomerManagementReq();
            customerManagementReq.setName(name);
            customerManagementReq.setContactInfo(contactInfo);
            customerManagementReq.setCusId(cusId);
            customerManagementReq.setPassword(password);

            // Call the service layer to create the customer
            createCustomerService.createCustomer(customerManagementReq);

            // Redirect the user to a success page or provide a success message
            resp.sendRedirect("/jsp/CustomerManagement/CustomerManagement.jsp");
        } catch (SQLException e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}
