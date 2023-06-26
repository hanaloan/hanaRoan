package com.Controller;

import com.Service.ChangeLoanStatusService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeLoanStatus")
public class ChangeLoanStatusController extends HttpServlet {
    private ChangeLoanStatusService changeLoanStatusService;

    @Override
    public void init() throws ServletException {
        super.init();
        changeLoanStatusService = new ChangeLoanStatusService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lendId = req.getParameter("lendId");
        String loanStatus = req.getParameter("loanStatus");
        String lendPeriod = req.getParameter("lendPeriod");

        try {
            changeLoanStatusService.updateLoanStatus(lendId, loanStatus, lendPeriod);
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Success");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}
