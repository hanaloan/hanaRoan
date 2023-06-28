package com.Controller;

import com.Service.DashBoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/GetCountPendingLends")
public class DashBoardGetPendingController extends HttpServlet {
    DashBoardService dashBoardService = new DashBoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getCountPendingLends;
        try {
            getCountPendingLends = dashBoardService.getCountPendingLends();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"getCountPendingLends\": \"" + getCountPendingLends + "\"}");
    }
}