package com.Model;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerManageResDto {
    private List<CustomerManageDto> customerManageDto;

    public List<CustomerManageDto> getCustomerManageDto() {
        return customerManageDto;
    }

    public void setCustomerManageDto(List<CustomerManageDto> customerManageDto) {
        this.customerManageDto = customerManageDto;
    }
}