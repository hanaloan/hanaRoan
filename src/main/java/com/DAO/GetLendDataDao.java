package com.DAO;
import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetLendDataDao {

    private static final String BASE_QUERY =
            "SELECT SUM(loan_lend.loan_amount) AS total_lent_amount FROM hanaroDB.loan_payments JOIN hanaroDB.loan_lend ON loan_payments.loan_lend_idx = loan_lend.lend_idx WHERE loan_payments.payment_status IN ('in progress', 'overdue')";

    public String getLendData() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(BASE_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("total_lent_amount");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}
