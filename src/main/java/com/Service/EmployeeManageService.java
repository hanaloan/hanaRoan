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

    public EmployeeManageService() {
        this.employeeManagementDao = new EmployeeManagementDao();
    }

    public List<Employee> selectEmployees() throws SQLException {
        return employeeManagementDao.selectEmployees();
    }

//    public Employee currentEmployee(String cur_name) {
//
//
//    }




    }
