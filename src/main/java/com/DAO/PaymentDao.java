package com.DAO;

import com.Model.Payment;
import com.utils.DatabaseConnector;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {
    public List<Payment> getAllPayment() throws SQLException {
        List<Payment> paymentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT lp.repayment_idx, c.name, lpr.loan_type_id, lpr.loan_name, ll.start_date, ll.end_date, lp.payment_amount\n" +
                    "FROM loan_payments lp\n" +
                    "JOIN loan_lend ll ON lp.loan_lend_idx = ll.lend_idx\n" +
                    "JOIN loan_products lpr ON ll.loan_idx = lpr.loan_idx\n" +
                    "JOIN customers c ON ll.customer_idx = c.customer_idx\n" +
                    "WHERE ll.loan_status = 'approved'";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("repayment_idx"));
                payment.setCustomerName(rs.getString("name"));
                String loanType = getLoanType(rs.getInt("loan_type_id"));
                payment.setProductType(loanType);
                payment.setProductName(rs.getString("loan_name"));
                payment.setLendStartDate(rs.getDate("start_date"));
                payment.setPaymentDueDate(rs.getDate("end_date"));
                payment.setDueBalance(rs.getBigDecimal("payment_amount"));
                payment.setPassedDate(getPassedDate(rs.getString("end_date")));
                paymentList.add(payment);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        System.out.println(paymentList.size());
        return paymentList;
    }

    public String[] getPaymentOption(String option1, String option2){
        String[] retVal = new String[2];
        switch(option1){
            case "전세":
                retVal[0] = "1";
                break;
            case "월세":
                retVal[0] = "2";
                break;
            case "담보":
                retVal[0] = "3";
                break;
            default:
                retVal[0] = "*";
                break;
        }
        switch(option2){
            case "미납중":
                retVal[1] = "overdue";
                break;
            case "상환중":
                retVal[1] = "in progress";
                break;
            case "상환 완료":
                retVal[1] = "paid";
                break;
            default:
                retVal[1] = "*";
                break;
        }
        return retVal;
    }

    public String getLoanType(int loanTypeId){
        String retVal = "";
        switch(loanTypeId){
            case 1:
                retVal = "전세";
                break;
            case 2:
                retVal = "월세";
                break;
            case 3:
                retVal = "담보";
                break;
        }
        return retVal;
    }

    public String getPassedDate(String date){
        LocalDate specificDate = LocalDate.parse(date);
        LocalDate today = LocalDate.now();

        long daysDifference = ChronoUnit.DAYS.between(specificDate, today);

        return daysDifference <= 0 ? "-" : String.valueOf(daysDifference);
    }
}
