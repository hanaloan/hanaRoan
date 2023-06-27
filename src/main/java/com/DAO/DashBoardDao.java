package com.DAO;

import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardDao {

    private static final String GET_TOTAL_AMOuNT_QUERY =
            "SELECT SUM(loan_lend.loan_amount) AS total_lent_amount FROM hanaroDB.loan_payments JOIN hanaroDB.loan_lend ON loan_payments.loan_lend_idx = loan_lend.lend_idx WHERE loan_payments.payment_status IN ('in progress', 'overdue')";

    public String getTotalLendData() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_TOTAL_AMOuNT_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("total_lent_amount");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static final String GET_OVERDUE_AMOuNT_QUERY =
            "SELECT SUM(loan_lend.loan_amount) AS overdue_lent_amount FROM hanaroDB.loan_payments JOIN hanaroDB.loan_lend ON loan_payments.loan_lend_idx = loan_lend.lend_idx WHERE loan_payments.payment_status IN ('overdue')";

    public String getOverdueLendData() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_OVERDUE_AMOuNT_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("overdue_lent_amount");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static final String GET_COUNT_PENDING_QUERY =
            "SELECT COUNT(loan_status) AS pending_loans FROM hanaroDB.loan_lend WHERE loan_status = 'pending'";

    public String getCountPendingLends() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_COUNT_PENDING_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("pending_loans");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static final String GET_OVERDUE_PERCENTAGE =
            "SELECT \n" +
                    "  100 * (SELECT COUNT(*) FROM hanaroDB.loan_payments WHERE payment_status = 'overdue') /\n" +
                    "  (SELECT COUNT(*) FROM hanaroDB.loan_payments WHERE payment_status IN ('in progress', 'overdue')) AS overdue_percentage;\n";

    public String getOverduePercentage() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_OVERDUE_PERCENTAGE);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.out.println(rs.getString("overdue_percentage"));
                return rs.getString("overdue_percentage");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
