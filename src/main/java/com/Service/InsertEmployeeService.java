package com.Service;

import com.DAO.InsertEmployeeDao;
import com.Model.EmployeeRes;

import java.sql.SQLException;

public class InsertEmployeeService {
    private InsertEmployeeDao insertEmployeeDao;

    public InsertEmployeeService() {
        insertEmployeeDao= new InsertEmployeeDao();
    }
    public void insertEmployee(EmployeeRes employee) throws SQLException {
        insertEmployeeDao.insertEmployee(employee);

    }
}
