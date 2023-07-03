package com.Controller;

import com.Model.DeductDueBalanceReq;
import com.Service.DeductBalanceService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeductBalance")
public class DeductBalanceController extends HttpServlet {
    private DeductBalanceService deductBalanceService;

    @Override
    public void init() throws ServletException {
        super.init();
        deductBalanceService = new DeductBalanceService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try{
            int paymentId = Integer.valueOf(request.getParameter("paymentId"));
            DeductDueBalanceReq deductDueBalanceReq = new DeductDueBalanceReq(paymentId);
            deductBalanceService.deductBalance(deductDueBalanceReq);
            response.sendRedirect("https://www.hanaroan.shop/loanPayment");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }
}