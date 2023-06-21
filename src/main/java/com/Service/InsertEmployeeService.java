package com.Service;

import com.DAO.EmployeeManagementDao;
import com.DAO.InsertEmployeeDao;
import com.Model.Employee;
import com.Model.EmployeeManagementReq;

public class InsertEmployeeService {
    private InsertEmployeeDao insertEmployeeDAO;

    public InsertEmployeeService() {
        insertEmployeeDAO= new InsertEmployeeDao();
    }
    public void insertEmployee(EmployeeManagementReq employeeManagementReq){
        String empId=employeeManagementReq.getEmpId();
        String empPw=employeeManagementReq.getEmpPw();
        String empName=employeeManagementReq.getEmpId();
        Integer empAuthIdx= employeeManagementReq.getEmpAuthIdx();

        Employee employee=new Employee();
        employee.setEmpId(empId);
        employee.setEmpPw(empPw);
        employee.setEmpName(empName);
        employee.setEmpLevel(empAuthIdx);

        insertEmployeeDAO.insertEmployee(employee);

    }



}
