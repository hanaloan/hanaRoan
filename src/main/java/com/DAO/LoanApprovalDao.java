package com.DAO;

import com.Model.*;
import com.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanApprovalDao {

    private static final String BASE_QUERY = "SELECT c.customer_idx, c.name, ll.lend_idx, lt.loan_type_name, lp.loan_name, lend_period, ll.start_date, ll.end_date, loan_amount, ll.loan_status, lpm.repayment_idx, lpm.payment_amount, lpm.payment_status\n, lp.loan_interest_rate\n" +
            "FROM hanaroDB.customers AS c\n" +
            "LEFT JOIN hanaroDB.loan_lend AS ll ON c.customer_idx = ll.customer_idx\n" +
            "LEFT JOIN hanaroDB.loan_products AS lp ON ll.loan_idx = lp.loan_idx\n" +
            "LEFT JOIN hanaroDB.loan_types AS lt ON lp.loan_type_id = lt.loan_type_idx\n" +
            "LEFT JOIN hanaroDB.loan_payments AS lpm ON ll.lend_idx = lpm.loan_lend_idx\n" +
            "WHERE ll.lend_idx IS NOT NULL AND ll.loan_status IN ('pending', 'denied', 'approved')";

    public List<LoanApprovalRes> loanApprovalResList() throws SQLException {
        List<LoanApprovalRes> loanApprovalResList = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(BASE_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int cusIdx = rs.getInt("customer_idx");
                String name = rs.getString("name");
                int lendIdx = rs.getInt("lend_idx");
                String loanTypeName = rs.getString("loan_type_name");
                String loanName = rs.getString("loan_name");
                int loanPeriod = rs.getInt("lend_period");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                Long loanAmount = rs.getLong("loan_amount");
                String loanStatus = rs.getString("loan_status");
                int repaymentIdx = rs.getInt("repayment_idx");
                Long paymentAmount = rs.getLong("payment_amount");
                String paymentStatus = rs.getString("payment_status");
                float loanInterestRate = rs.getFloat("loan_interest_rate");

                LoanApprovalRes loanApprovalRes = new LoanApprovalRes();
                loanApprovalRes.setCusIdx(cusIdx);
                loanApprovalRes.setName(name);
                loanApprovalRes.setLendIdx(lendIdx);
                loanApprovalRes.setLoanTypeName(loanTypeName);
                loanApprovalRes.setLoanName(loanName);
                loanApprovalRes.setLoanPeriod(loanPeriod);
                loanApprovalRes.setStartDate(startDate);
                loanApprovalRes.setEndDate(endDate);
                loanApprovalRes.setLoanAmount(loanAmount);
                loanApprovalRes.setLoanStatus(loanStatus);
                loanApprovalRes.setRepaymentIdx(repaymentIdx);
                loanApprovalRes.setPaymentAmount(paymentAmount);
                loanApprovalRes.setPaymentStatus(paymentStatus);
                loanApprovalRes.setLoanInterestRate(loanInterestRate);

                loanApprovalResList.add(loanApprovalRes);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return loanApprovalResList;
    }

    public List<LoanApprovalRes> getPendingLoanApprovalResList() throws SQLException {
        List<LoanApprovalRes> loanApprovalResList = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(BASE_QUERY + " AND ll.loan_status = 'pending'");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int cusIdx = rs.getInt("customer_idx");
                String name = rs.getString("name");
                int lendIdx = rs.getInt("lend_idx");
                String loanTypeName = rs.getString("loan_type_name");
                String loanName = rs.getString("loan_name");
                int loanPeriod = rs.getInt("lend_period");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                Long loanAmount = rs.getLong("loan_amount");
                String loanStatus = rs.getString("loan_status");
                int repaymentIdx = rs.getInt("repayment_idx");
                Long paymentAmount = rs.getLong("payment_amount");
                String paymentStatus = rs.getString("payment_status");
                float loanInterestRate = rs.getFloat("loan_interest_rate");

                LoanApprovalRes loanApprovalRes = new LoanApprovalRes();
                loanApprovalRes.setCusIdx(cusIdx);
                loanApprovalRes.setName(name);
                loanApprovalRes.setLendIdx(lendIdx);
                loanApprovalRes.setLoanTypeName(loanTypeName);
                loanApprovalRes.setLoanName(loanName);
                loanApprovalRes.setLoanPeriod(loanPeriod);
                loanApprovalRes.setStartDate(startDate);
                loanApprovalRes.setEndDate(endDate);
                loanApprovalRes.setLoanAmount(loanAmount);
                loanApprovalRes.setLoanStatus(loanStatus);
                loanApprovalRes.setRepaymentIdx(repaymentIdx);
                loanApprovalRes.setPaymentAmount(paymentAmount);
                loanApprovalRes.setPaymentStatus(paymentStatus);
                loanApprovalRes.setLoanInterestRate(loanInterestRate);

                loanApprovalResList.add(loanApprovalRes);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return loanApprovalResList;
    }
}