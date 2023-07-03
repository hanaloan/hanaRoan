package com.Controller;


import com.Service.LoanPaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CreateLoanPayment")
public class PaymentController extends HttpServlet {
    LoanPaymentService loanPaymentService = new LoanPaymentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lendId = request.getParameter("lendId");

        try {
            loanPaymentService.createLoanPayment(Integer.parseInt(lendId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("https://www.hanaroan.shop/CustomerManagement");
    }
}