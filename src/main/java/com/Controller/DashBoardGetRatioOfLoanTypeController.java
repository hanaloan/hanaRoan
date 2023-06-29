package com.Controller;

import com.Service.DashBoardService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/GetRatioOfLoanType")
public class DashBoardGetRatioOfLoanTypeController extends HttpServlet {
    DashBoardService dashBoardService = new DashBoardService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> getRatioOfLoanType;
        try {
            getRatioOfLoanType = dashBoardService.getRatioOfLoanType();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(getRatioOfLoanType));
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
            resp.getWriter().write("An error occurred while retrieving loan type ratio.");
            e.printStackTrace(); // 에러 로그 출력
        }
    }
}
