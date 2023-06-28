package com.Service;

import com.DAO.InsertEmployeeDao;
import com.Model.Employee;

import java.sql.SQLException;

public class InsertEmployeeService {
    private InsertEmployeeDao insertEmployeeDao;

    public InsertEmployeeService() {
        insertEmployeeDao= new InsertEmployeeDao();
    }
    public void insertEmployee(Employee employee) throws SQLException {
        insertEmployeeDao.insertEmployee(employee);

    }
}
