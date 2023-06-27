package com.Service;

import com.DAO.CustomerManagementDao;
import com.Model.CustomerRes;

import java.sql.SQLException;
import java.util.List;

public class CustomerManagementService {
    private static CustomerManagementDao customerManagementDao;

    public CustomerManagementService() {
        customerManagementDao = new CustomerManagementDao();
    }

    public static List<CustomerRes> getCustomerInfo() throws SQLException {
        return customerManagementDao.getCustomerInfo();
    }
}
