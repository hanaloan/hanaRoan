package com.Controller;

import com.Model.EmployeeRes;
import com.Service.InsertEmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InsertEmployee")
public class InsertEmployeeController extends HttpServlet {

    private InsertEmployeeService insertEmployeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        insertEmployeeService = new InsertEmployeeService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            String empId=req.getParameter("empId");
            String empPw = req.getParameter("empPw");
            String empName=req.getParameter("empName");
            String empAuth=req.getParameter("empAuthorityName");

            EmployeeRes employee  = new EmployeeRes(empId, empPw, empName,empAuth);
            insertEmployeeService.insertEmployee(employee);
            resp.sendRedirect("/EmployeeManagement");
        } catch (Exception e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}
