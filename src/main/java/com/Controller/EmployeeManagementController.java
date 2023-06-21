package com.Controller;

import com.DAO.EmployeeManagementDao;
import com.Model.*;
import com.Service.CustomerManageService;
import com.Service.EmployeeManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/EmployeeManagement")
public class EmployeeManagementController extends HttpServlet {

    private EmployeeManageService employeeManageService;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeManageService = new EmployeeManageService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
//            EmployeeManagementReq employeeManagementReq = new EmployeeManagementReq();


            //asdasdasd 로 로그인 했다 치고
            Employee empAuth=employeeManageService.currentEmployee("asdasdasd");
            req.setAttribute("empName", empAuth.getEmpName()); //값은 아무값도 없고 로직도 없지만 임시로 해놓음
            req.setAttribute("empAuth", empAuth.getEmpLevel());


            List<Employee> employeeManageResDto = employeeManageService.selectEmployees();

            req.setAttribute("employeeManageResDto", employeeManageResDto);
            req.getRequestDispatcher("jsp/ManageEmployee/EmployeeManagement.jsp").forward(req, resp);

        } catch (SQLException e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 요청 파라미터 추출
        String empId = req.getParameter("empId");
        String empPw = req.getParameter("empPw");
        String empName = req.getParameter("empName");
        int empAuth = Integer.parseInt(req.getParameter("empAuth"));

//        EmployeeManagementReq employeeManagementReq = new EmployeeManagementReq(empId, empPw, empName,empAuth );
//
//        try {
//            //여기부터 작성할 예정
//        } catch (SQLException e) {
//            // 예외 발생 시 응답
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().println("Internal server error");
//        }
    }
}
