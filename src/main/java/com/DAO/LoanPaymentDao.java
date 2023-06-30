package com.DAO;

import com.Model.Payment;
import com.utils.DatabaseConnector;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Date;

public class LoanPaymentDao {
    public List<Payment> getPayments(String option1, String option2) throws SQLException {
        List<Payment> paymentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT lp.repayment_idx, c.name, lp.payment_amount, lp.payment_status, lpr.loan_type_id, lpr.loan_name, ll.start_date, ll.end_date\n" +
                    "FROM loan_payments lp\n" +
                    "JOIN loan_lend ll ON lp.loan_lend_idx = ll.lend_idx\n" +
                    "JOIN loan_products lpr ON ll.loan_idx = lpr.loan_idx\n" +
                    "JOIN customers c ON ll.customer_idx = c.customer_idx\n" +
                    "WHERE (ll.loan_status = 'approved' OR ll.loan_status = 'paid')";

            String[] option = getPaymentOption(option1, option2);
            Map<Integer, String> category = new HashMap<>();

            if(!option[0].equals("*") || !option[1].equals("*")){
                int idx = 1;
                if(!option[0].equals("*")){
                    sql += " AND lpr.loan_type_id = ?";
                    category.put(idx++, option[0]);
                }
                if(!option[1].equals("*")){
                    sql += " AND lp.payment_status = ?";
                    category.put(idx++, option[1]);
                }
                stmt = conn.prepareStatement(sql);
                for(int i : category.keySet()){
                    stmt.setString(i, category.get(i));
                }
            }
            else{
                stmt = conn.prepareStatement(sql);
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                int repaymentIdx = rs.getInt("repayment_idx");
                String customerName = rs.getString("name");
                String loanType = getLoanType(rs.getInt("loan_type_id"));
                String productName = rs.getString("loan_name");
                Date startDate = rs.getDate("start_date");
                Date paymetDueDate = rs.getDate("end_date");
                BigDecimal dueBalance = rs.getBigDecimal("payment_amount");
                String passedDate = getPassedDate(rs.getString("end_date"));
                String paymentStatus = getStatus(rs.getString("payment_status"));
                Payment payment = new Payment(repaymentIdx, customerName, loanType, productName, startDate,
                        paymetDueDate, dueBalance, passedDate, paymentStatus);
                paymentList.add(payment);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return paymentList;
    }

    public void deductBalance(String paymentId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String sql = "UPDATE loan_payments as lp\n" +
                    "JOIN loan_lend as ll ON lp.loan_lend_idx = ll.lend_idx\n" +
                    "SET lp.payment_amount = 0\n," +
                    "    lp.payment_status = 'paid'\n," +
                    "    ll.loan_status = 'paid'\n" +
                    "WHERE repayment_idx = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, paymentId);
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public void handleOverdue(String paymentId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String sql = "UPDATE loan_payments as lp\n" +
                    "JOIN loan_lend as ll ON lp.loan_lend_idx = ll.lend_idx\n" +
                    "JOIN loan_products as lpr ON ll.loan_idx = lpr.loan_idx\n" +
                    "SET lp.payment_amount = ll.loan_amount + (ll.loan_amount * lpr.overdue_interest_rate / 100) * CEIL(DATEDIFF(CURDATE(), ll.end_date) / 365),\n" +
                    "    lp.payment_status = 'overdue'\n" +
                    "WHERE lp.payment_status != 'paid' AND lp.repayment_idx = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, paymentId);
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public void createLoanPayment(int lendId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String sql = "INSERT INTO hanaroDB.loan_payments (payment_amount, payment_status, loan_lend_idx)\n" +
                        "SELECT ll.loan_amount + ROUND(loan_amount * lp.loan_interest_rate * lp.lend_period / 100, 2), 'in progress', lend_idx\n" +
                        "FROM loan_lend as ll\n" +
                        "INNER JOIN loan_products as lp ON ll.loan_idx = lp.loan_idx \n" +
                        "WHERE lend_idx = ?;";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, lendId);

            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* Helper Functions */
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
            case "연체":
                retVal[1] = "overdue";
                break;
            case "상환중":
                retVal[1] = "in progress";
                break;
            case "상환완료":
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

        return String.valueOf(daysDifference);
    }

    public String getStatus(String status){
        switch(status){
            case "in progress":
                return "상환중";
            case "paid":
                return "상환완료";
            case "overdue":
                return "연체";
            default:
                return "-";
        }
    }

}
