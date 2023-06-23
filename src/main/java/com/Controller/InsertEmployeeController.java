package com.Controller;

import com.Model.Employee;
import com.Model.EmployeeManagementReq;
import com.Model.EmployeeManagementRes;
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
        resp.setCharacterEncoding("UTF-8");
        try {
            String empId=req.getParameter("empId");
            String empPw = req.getParameter("empPw");
            String empName=req.getParameter("empName");
            String empAuth=req.getParameter("empAuthorityName");


            Employee employee  = new Employee(empId, empPw, empName,empAuth);

            System.out.println("Post 들어옴");

            insertEmployeeService.insertEmployee(employee);
//            if(success){
//                System.out.println("성공!");
//                resp.sendRedirect("EmployeeManagement.jsp");
//            }
            System.out.println("넘어가기전");
            resp.sendRedirect("/jsp/ManageEmployee/EmployeeManagement.jsp");
//            resp.sendRedirect("/jsp/ManageEmployee/EmployeeManagement.jsp");
//            req.getRequestDispatcher("/jsp/ManageEmployee/EmployeeManagement.jsp").forward(req, resp);
            System.out.println("넘어간 후");

        } catch (Exception e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}
