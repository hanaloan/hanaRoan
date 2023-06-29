package com.Controller;

import com.Model.DashBoardPVRes;
import com.Service.DashBoardPVService;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DashBoardGetPV")
public class DashBoardGetPVController extends HttpServlet {
    private final DashBoardPVService dashBoardPVService;
    private final Gson gson = new Gson();

    public DashBoardGetPVController() {
        dashBoardPVService = new DashBoardPVService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            DashBoardPVRes dashBoardPVRes = dashBoardPVService.selectTotalPVUVService();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(dashBoardPVRes));
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
            response.getWriter().write("An error occurred while retrieving loan type ratio.");
            e.printStackTrace(); // 에러 로그 출력
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
