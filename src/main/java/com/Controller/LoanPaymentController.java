package com.Controller;

import com.Model.Payment;
import com.Service.LoanPaymentService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/loanPayment")
public class LoanPaymentController extends HttpServlet {
    private LoanPaymentService loanPaymentService;

    @Override
    public void init()throws ServletException {
        super.init();
        loanPaymentService = new LoanPaymentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String option1 = request.getParameter("option1") == null || request.getParameter("option1").isEmpty()?
                    "*" : request.getParameter("option1");
            String option2 = request.getParameter("option2") == null || request.getParameter("option2").isEmpty()?
                    "*" : request.getParameter("option2");
            List<Payment> paymentList = loanPaymentService.getPayments(option1, option2);
            request.setAttribute("paymentList", paymentList);
            request.setAttribute("authType", session.getAttribute("authType"));
            request.getRequestDispatcher("jsp/LoanPayment/LoanPayment.jsp").forward(request, response);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        try {
            if (request.getParameter("paymentId") != null) {
                String paymentId = request.getParameter("paymentId");
                if (request.getParameter("paymentStatus") != null) {
                    loanPaymentService.deductBalance(paymentId);
                } else if (request.getParameter("passedDate") != null) {
                    loanPaymentService.handleOverdue(paymentId);
                }
            }
            response.sendRedirect("loanPayment");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }
}