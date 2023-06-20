package com.Model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerManagementRes {
    private List<CustomerManagement> customerManagement;

    public List<CustomerManagement> getCustomerManagement() {
        return customerManagement;
    }

    public void setCustomerManagement(List<CustomerManagement> customerManagement) {
        this.customerManagement = customerManagement;
    }
}