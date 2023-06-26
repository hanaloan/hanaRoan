package com.DAO;

import com.Model.*;
import com.utils.DatabaseConnector;
import com.utils.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanApprovalDao {

    private static final String BASE_QUERY = "SELECT *\n" +
            "FROM hanaroDB.customers AS c\n" +
            "LEFT JOIN hanaroDB.loan_lend AS ll ON c.customer_idx = ll.customer_idx\n" +
            "LEFT JOIN hanaroDB.loan_products AS lp ON ll.loan_idx = lp.loan_idx\n" +
            "LEFT JOIN hanaroDB.loan_types AS lt ON lp.loan_type_id = lt.loan_type_idx\n" +
            "LEFT JOIN hanaroDB.loan_payments AS lpm ON ll.lend_idx = lpm.loan_lend_idx\n" +
            "WHERE ll.lend_idx IS NOT NULL";

    public List<CustomerManagement> getCustomerInfo(String loanTypeName, String loanStatus, CustomerManagementReq customerManagementReq) {
        List<CustomerManagement> customerManagementList = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = createPreparedStatement(conn, loanTypeName, loanStatus);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customerManagementList.add(getCustomerManagementFromResultSet(rs));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseException("Failed to connect to the database: " + e.getMessage(), e);
        }

        return customerManagementList;
    }

    private PreparedStatement createPreparedStatement(Connection conn, String loanTypeName, String loanStatus) throws SQLException {
        String sql = createSqlQuery(loanTypeName, loanStatus);
        PreparedStatement stmt = conn.prepareStatement(sql);

        int paramIndex = 1;

        if (loanTypeName != null) {
            stmt.setString(paramIndex++, loanTypeName);
        }

        if (loanStatus != null) {
            stmt.setString(paramIndex, loanStatus);
        }

        return stmt;
    }

    private String createSqlQuery(String loanTypeName, String loanStatus) {
        StringBuilder sql = new StringBuilder(BASE_QUERY);

        if (loanTypeName != null) {
            sql.append(" AND lt.loan_type_name = ?");
        }

        if (loanStatus != null) {
            sql.append(" AND ll.loan_status = ?");
        }

        return sql.toString();
    }

    private CustomerManagement getCustomerManagementFromResultSet(ResultSet rs) throws SQLException {
        return new CustomerManagement(
                rs.getInt("customer_idx"),
                rs.getString("name"),
                rs.getString("contact_info"),
                rs.getString("password"),
                rs.getInt("lend_idx"),
                String.valueOf(rs.getDate("start_date")),
                String.valueOf(rs.getDate("start_date")),
                rs.getLong("loan_amount"),
                rs.getString("loan_status"),
                rs.getInt("repayment_idx"),
                rs.getLong("payment_amount"),
                rs.getString("payment_status"),
                rs.getFloat("overdue_interest_rate"),
                rs.getFloat("loan_interest_rate"),
                rs.getString("loan_type_name"),
                rs.getInt("lend_period"),
                0,
                null,
                null);
    }
}