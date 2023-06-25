package com.Service;

import com.DAO.UpdateEmployeeAuthDao;

public class UpdateEmployeeAuthService {

    private UpdateEmployeeAuthDao updateEmployeeAuthDao;

    public UpdateEmployeeAuthService(){
        updateEmployeeAuthDao= new UpdateEmployeeAuthDao();
    }

    public void updateEmployeeAuth(Integer empIdx, String employeeAuthName){
        System.out.println("여기는 update 서비스");
        updateEmployeeAuthDao.updateEmployeeAuth(empIdx, employeeAuthName);
    }

}
