package com.Controller;

import com.DAO.EmployeeManagementDao;
import com.Model.*;
import com.Service.EmployeeManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            HttpSession session = req.getSession();
            Integer employeeIdx = (Integer) session.getAttribute("employee_idx");

            //현재 관리자의 정보 가져오기
            Employee cur_employee=employeeManageService.currentEmployee(employeeIdx);
            req.setAttribute("empName", cur_employee.getEmpName()); //값은 아무값도 없고 로직도 없지만 임시로 해놓음
            req.setAttribute("empAuthName", cur_employee.getEmpLevelName());
            req.setAttribute("empIdx", cur_employee.getEmpIdx());

            //모든 직원 데이터 가져오기
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

}