package com.Service;

import com.DAO.CustomerDAO;
import com.Model.Customer;
import com.Model.CustomerManagementReq;

import java.sql.SQLException;

public class CreateCustomerService {
    private CustomerDAO customerDAO;

    public CreateCustomerService() {
        customerDAO = new CustomerDAO(); // Instantiate the CustomerDAO
    }
    public void createCustomer(CustomerManagementReq customerManagementReq) throws SQLException {

        String name = customerManagementReq.getName();
        String contactInfo = customerManagementReq.getContactInfo();
        String cusId = customerManagementReq.getCusId();
        String password = customerManagementReq.getPassword();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setContactInfo(contactInfo);
        customer.setCusId(cusId);
        customer.setPassword(password);

        customerDAO.createCustomer(customer);
    }
}
