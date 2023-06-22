package com.Service;

import com.DAO.InsertEmployeeDao;
import com.Model.Employee;

public class InsertEmployeeService {
    private InsertEmployeeDao insertEmployeeDao;

    public InsertEmployeeService() {
        insertEmployeeDao= new InsertEmployeeDao();
    }
    public boolean insertEmployee(Employee employee){
        return insertEmployeeDao.insertEmployee(employee);

    }
}
