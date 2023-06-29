package com.Service;

import com.DAO.UpdateEmployeeAuthDao;

import java.sql.SQLException;

public class UpdateEmployeeAuthService {

    private UpdateEmployeeAuthDao updateEmployeeAuthDao;

    public UpdateEmployeeAuthService(){
        updateEmployeeAuthDao= new UpdateEmployeeAuthDao();
    }

    public void updateEmployeeAuth(Integer empIdx, String employeeAuthName , Integer curEmpIdx) throws SQLException {
        updateEmployeeAuthDao.updateEmployeeAuth(empIdx, employeeAuthName, curEmpIdx);
    }

}
