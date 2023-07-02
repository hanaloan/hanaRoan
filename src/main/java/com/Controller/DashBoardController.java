package com.Controller;

import com.Service.DashBoardService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/DashBoard")
public class DashBoardController extends HttpServlet {
    DashBoardService dashBoardService = new DashBoardService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, String> getRatioOfLoanType = dashBoardService.getRatioOfLoanType();
            request.setAttribute("ratioOfLoanType", getRatioOfLoanType);

            String loanAmount = dashBoardService.getLendData();
            request.setAttribute("loanAmount", loanAmount);

            String overdueLoanAmount = dashBoardService.getOverdueLendData();
            request.setAttribute("overdueLoanAmount", overdueLoanAmount);

            String getOverduePercentage = dashBoardService.getOverduePercentage();
            request.setAttribute("overduePercentage", getOverduePercentage);

            String getCountPendingLends = dashBoardService.getCountPendingLends();
            request.setAttribute("countPendingLends", getCountPendingLends);

            request.getRequestDispatcher("/jsp/DashBoard/DashBoard.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}