package com.Model;
import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private int id;
    private String name;
    private String description;
    private int minCredit;
    private BigDecimal lendLimit;
    private int loanPeriod;
    private BigDecimal interestRate;
    private Date startDate;
    private Date endDate;

    public Product(int id, String name, String description, int minCredit,
                   BigDecimal lendLimit, int loanPeriod, BigDecimal interestRate,
                   Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minCredit = minCredit;
        this.lendLimit = lendLimit;
        this.loanPeriod = loanPeriod;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public int getMinCredit() {return minCredit;}

    public void setMinCredit(int minCredit) {this.minCredit = minCredit;}

    public BigDecimal getLendLimit() {return lendLimit;}

    public void setLendLimit(BigDecimal lendLimit) {this.lendLimit = lendLimit;}

    public int getLoanPeriod() {return loanPeriod;}

    public void setLendPeriod(int lendPeriod) {this.loanPeriod = lendPeriod;}

    public BigDecimal getInterestRate() {return interestRate;}

    public void setInterestRate(BigDecimal interestRate) {this.interestRate = interestRate;}

    public Date getStartDate() {return startDate;}

    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public Date getEndDate() {return endDate;}

    public void setEndDate(Date endDate) {this.endDate = endDate;}
}
