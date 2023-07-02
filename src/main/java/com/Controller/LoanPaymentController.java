package com.Controller;

import com.Model.PaymentReq;
import com.Model.PaymentRes;
import com.Service.LoanPaymentService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

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

            PaymentReq paymentReq = new PaymentReq(option1, option2);
            PaymentRes paymentRes = loanPaymentService.getPayments(paymentReq);
            request.setAttribute("paymentList", paymentRes);
            request.setAttribute("authType", session.getAttribute("authType"));
            request.getRequestDispatcher("jsp/LoanPayment/LoanPayment.jsp").forward(request, response);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }
}