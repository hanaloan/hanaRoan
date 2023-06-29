package com.Controller;


import com.Service.UpdateEmployeeAuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateEmpAuth")
public class UpdateEmployeeAuthController extends HttpServlet {
    private UpdateEmployeeAuthService updateEmployeeAuthService;

    @Override
    public void init() throws ServletException {
        super.init();
        updateEmployeeAuthService = new UpdateEmployeeAuthService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer empIdx= Integer.valueOf(req.getParameter("empIdx"));
        String empAuthName=req.getParameter("empAuthName");

        HttpSession session = req.getSession();
        Integer curEmpIdx = (Integer) session.getAttribute("employee_idx");


        try {
            updateEmployeeAuthService.updateEmployeeAuth(empIdx, empAuthName, curEmpIdx);
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
