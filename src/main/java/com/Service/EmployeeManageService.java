package com.Service;

import com.DAO.CustomerManagementDao;
import com.DAO.EmployeeManagementDao;
import com.Model.CustomerManagement;
import com.Model.CustomerManagementReq;
import com.Model.EmployeeManagementReq;
import com.Model.EmployeeRes;

import java.sql.SQLException;
import java.util.List;

public class EmployeeManageService {

    private final EmployeeManagementDao employeeManagementDao;

    //생성자
    public EmployeeManageService() {
        this.employeeManagementDao = new EmployeeManagementDao();
    }

    //직원들 정보 표로 가져오기
    public List<EmployeeRes> selectEmployees() throws SQLException {
        return employeeManagementDao.selectEmployees();
    }

    //현재 직원 정보 가져오기
    public EmployeeRes currentEmployee(Integer cur_idx) throws SQLException {
        return employeeManagementDao.currentEmployee(cur_idx);
    }


}