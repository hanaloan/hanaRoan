package com.Controller;

import com.Model.DashBoardPVReq;
import com.Model.DashBoardPVRes;
import com.Service.DashBoardPVService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DashBoardGetPV")
public class DashBoardGetPVController extends HttpServlet {
    private final DashBoardPVService dashBoardPVService;

    public DashBoardGetPVController() {
        dashBoardPVService = new DashBoardPVService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String date = request.getParameter("date");

            DashBoardPVReq dashBoardPVReq = new DashBoardPVReq(date);
            DashBoardPVRes dashBoardPVRes = dashBoardPVService.selectTotalPVUVService(dashBoardPVReq);

            System.out.println(dashBoardPVRes.getOneWeekTotalPV());
            System.out.println(dashBoardPVRes.getMinPVValue());
            System.out.println(dashBoardPVRes.getMaxPVValue());

            System.out.println(dashBoardPVRes.getOneWeekUV());
            System.out.println(dashBoardPVRes.getMinUVValue());
            System.out.println(dashBoardPVRes.getMaxUVValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
