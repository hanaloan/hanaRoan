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
//            Employee empAuth=employeeManageService.currentEmployee("asdasdasd");
//            req.setAttribute("empAuth", empAuth.getEmpLevel());


            List<Employee> employeeManageResDto = employeeManageService.selectEmployees();

            req.setAttribute("employees", employeeManageResDto);
            req.getRequestDispatcher("jsp/ManageEmployee/EmployeeManagement.jsp").forward(req, resp);




        } catch (SQLException e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
            throw new RuntimeException(e);
        }
//        EmployeeManagementDao empDao=new EmployeeManagementDao();


        //asdasdasd 로 로그인 했다 치고
//        Employee empAuth=empDao.currentEmployee("asdasdasd");
//        req.setAttribute("empAuth", empAuth.getEmpLevel());

//        List<Employee> employees = empDao.selectEmployees();
//        req.setAttribute("employees", employees);

//        req.getRequestDispatcher("jsp/ManageEmployee/EmployeeManagement.jsp").forward(req, resp);

    }



}
