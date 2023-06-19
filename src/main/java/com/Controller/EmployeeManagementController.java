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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String value = request.getParameter("value");

        String param1=request.getParameter("param1");
        String param2=request.getParameter("param2");
        String param3=request.getParameter("param3");
        String param4=request.getParameter("param4");

        System.out.println(param1);
//        String param4=request.getParameter("authority");
//        int param4 = Integer.parseInt(request.getParameter("authority"));

        // Pass the value to the DAO layer and perform necessary database operations
        EmployeeManagementDao.insertEmployee(param1, param2, param3, param4);

        // Redirect or forward to the appropriate JSP page
        response.sendRedirect("jsp/ManageEmployee/EmployeeManagement.jsp");
    }


}
