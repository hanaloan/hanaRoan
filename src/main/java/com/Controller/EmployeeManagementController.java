package com.Controller;

import com.DAO.EmployeeManagementDao;
import com.Model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmployeeManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeManagementDao empDao=new EmployeeManagementDao();
        List<Employee> employees = empDao.selectEmployees();

        req.setAttribute("employees", employees);
        req.getRequestDispatcher("jsp/ManageEmployee/EmployeeManagement.jsp").forward(req, resp);

    }



}
