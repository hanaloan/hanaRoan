package com.Service;

import com.DAO.CustomerManagementDao;
import com.DAO.EmployeeManagementDao;
import com.Model.CustomerManagement;
import com.Model.CustomerManagementReq;
import com.Model.Employee;
import com.Model.EmployeeManagementReq;

import java.sql.SQLException;
import java.util.List;

public class EmployeeManageService {

    private final EmployeeManagementDao employeeManagementDao;

    //생성자
    public EmployeeManageService() {
        this.employeeManagementDao = new EmployeeManagementDao();
    }

    //직원들 정보 표로 가져오기
    public List<Employee> selectEmployees() throws SQLException {
        return employeeManagementDao.selectEmployees();
    }

    //현재 직원 정보 가져오기
    public Employee currentEmployee(Integer cur_idx) {
        return employeeManagementDao.currentEmployee(cur_idx);
    }

    //직원 추가
    public void insertEmployeeInfo(EmployeeManagementReq employeeManagementReq) {
        employeeManagementDao.insertEmployeeInfo(employeeManagementReq);
    }
}
