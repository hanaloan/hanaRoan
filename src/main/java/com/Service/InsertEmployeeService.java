//package com.Service;
//
//import com.DAO.EmployeeManagementDao;
//import com.DAO.InsertEmployeeDAO;
//import com.Model.Employee;
//import com.Model.EmployeeManagementReq;
//
//public class InsertEmployeeService {
//    private InsertEmployeeDAO insertEmployeeDAO;
//
//    public InsertEmployeeService() {
//        insertEmployeeDAO= new InsertEmployeeDAO();
//    }
//    public void insertEmployee(EmployeeManagementReq employeeManagementReq){
//        String empId=employeeManagementReq.getEmpId();
//        String empPw=employeeManagementReq.getEmpPw();
//        String empName=employeeManagementReq.getEmpId();
//        Integer empAuthIdx= employeeManagementReq.getEmpAuthIdx();
//
//        Employee employee=new Employee();
//        employee.setEmpId(empId);
//        employee.setEmpPw(empPw);
//        employee.setEmpName(empName);
//        employee.setEmpLevel(empAuthIdx);
//
//        insertEmployeeDAO.insertEmployee(employee);
//
//    }
//
//
//
//}
