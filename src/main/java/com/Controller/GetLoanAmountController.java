package com.Controller;

import com.Service.GetLendDataService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/GetTotalLoanAmount")
public class GetLoanAmountController  extends HttpServlet {
    GetLendDataService getLendDataService = new GetLendDataService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loanAmount;
        try {
            loanAmount = getLendDataService.getLendData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"loanAmount\": \"" + loanAmount + "\"}");
    }
}
