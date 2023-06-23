package com.Model;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class Payment {
    private int paymentId;
    private String customerName;
    private String productType;
    private String productName;
    private Date lendStartDate;
    private Date paymentDueDate;
    private BigDecimal dueBalance;
    private String passedDate;
    private String paymentStatus;
}
