package com.Controller;

import com.DAO.PaymentDao;
import com.Model.Payment;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/loanPayment")
public class LoanPaymentController extends HttpServlet {
    private PaymentDao paymentDao;

    @Override
    public void init() {
        paymentDao = new PaymentDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Payment> paymentList = paymentDao.getAllPayment();
            request.setAttribute("paymentList", paymentList);
            request.getRequestDispatcher("jsp/LoanPayment/LoanPayment.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}