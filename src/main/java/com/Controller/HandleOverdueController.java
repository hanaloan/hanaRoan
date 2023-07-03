package com.Controller;

import com.Model.HandleOverdueReq;
import com.Service.HandleOverdueService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/HandleOverdue")
public class HandleOverdueController extends HttpServlet {
    private HandleOverdueService handleOverdueService;

    @Override
    public void init() throws ServletException{
        super.init();
        handleOverdueService = new HandleOverdueService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try{
            int paymentId = Integer.valueOf(request.getParameter("paymentId"));
            HandleOverdueReq handleOverdueReq = new HandleOverdueReq(paymentId);
            handleOverdueService.handleOverdue(handleOverdueReq);
            response.sendRedirect("/loanPayment");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }
}