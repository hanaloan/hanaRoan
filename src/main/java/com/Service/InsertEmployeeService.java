package com.Service;

import com.DAO.EmployeeManagementDao;
import com.DAO.InsertEmployeeDao;
import com.Model.Employee;
import com.Model.EmployeeManagementReq;
import com.Model.EmployeeManagementRes;

public class InsertEmployeeService {
    private InsertEmployeeDao insertEmployeeDao;

    public InsertEmployeeService() {
        insertEmployeeDao= new InsertEmployeeDao();
    }
    public void insertEmployee(Employee employee){
//        String empId=employeeManagementReq.getEmpId();
//        String empPw=employeeManagementReq.getEmpPw();
//        String empName=employeeManagementReq.getEmpId();
//        String empAuthName= employeeManagementReq.getEmpAuthName();
//
//        System.out.println(empId);
//        System.out.println(empPw);
//        System.out.println(empName);
//        System.out.println(empAuthName);
//
//        Employee employee=new Employee();
//        employee.setEmpId(empId);
//        employee.setEmpPw(empPw);
//        employee.setEmpName(empName);
//        employee.setEmpLevelName(empAuthName);

        insertEmployeeDao.insertEmployee(employee);

    }



}
