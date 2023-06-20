package com.Service;

import com.DAO.CustomerManageDao;
import com.Model.CustomerManageReqDto;
import com.Model.*;

import java.sql.SQLException;
import java.util.List;

public class CustomerManageService {
    private final CustomerManageDao customerManageDao;

    public CustomerManageService() {
        this.customerManageDao = new CustomerManageDao();
    }

    public List<CustomerManageDto> getCustomerInfo(CustomerManageReqDto customerManageReqDto) throws SQLException {
        return customerManageDao.getCustomerInfo(customerManageReqDto);
    }
}