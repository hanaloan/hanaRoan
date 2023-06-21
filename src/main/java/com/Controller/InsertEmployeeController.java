package com.Controller;

import com.Model.EmployeeManagementReq;
import com.Service.CreateCustomerService;
import com.Service.InsertEmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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
        try {
            String empId=req.getParameter("empId");
            String empPw = req.getParameter("empPw");
            String empName=req.getParameter("empName");
            Integer empAuth=req.getIntHeader("empAuth");

            EmployeeManagementReq employeeManagementReq  = new EmployeeManagementReq();
            employeeManagementReq.setEmpId(empId);
            employeeManagementReq.setEmpPw(empPw);
            employeeManagementReq.setEmpName(empName);
            employeeManagementReq.setEmpAuthIdx(empAuth);

            insertEmployeeService.insertEmployee(employeeManagementReq);

//            resp.sendRedirect("/jsp/ManageEmployee/EmployeeManagement.jsp");

        } catch (Exception e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}
