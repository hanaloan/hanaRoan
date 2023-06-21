package com.Service;

import com.DAO.CustomerManagementDao;
import com.Model.CustomerManagementReq;
import com.Model.*;

import java.sql.SQLException;
import java.util.List;

public class CustomerManageService {
    private final CustomerManagementDao customerManagementDao;

    public CustomerManageService() {
        this.customerManagementDao = new CustomerManagementDao();
    }

    public List<CustomerManagement> getCustomerInfo(String loanTypeName, CustomerManagementReq customerManagementReq) throws SQLException {
        return customerManagementDao.getCustomerInfo(loanTypeName, customerManagementReq);
    }

}