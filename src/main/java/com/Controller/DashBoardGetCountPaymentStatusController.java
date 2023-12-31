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

@WebServlet("/GetCountPaymentStatus")
public class DashBoardGetCountPaymentStatusController extends HttpServlet {
    DashBoardService dashBoardService = new DashBoardService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Integer> getCountPaymentStatus;
        try {
            getCountPaymentStatus = dashBoardService.getCountPaymentStatus();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(getCountPaymentStatus));
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("An error occurred while retrieving loan type ratio.");
            e.printStackTrace();
        }
    }
}
