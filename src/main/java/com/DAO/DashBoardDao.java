package com.DAO;

import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DashBoardDao {

    private static final String GET_TOTAL_AMOuNT_QUERY =
            "SELECT SUM(loan_lend.loan_amount) AS total_lent_amount FROM hanaroDB.loan_payments JOIN hanaroDB.loan_lend ON loan_payments.loan_lend_idx = loan_lend.lend_idx WHERE loan_payments.payment_status IN ('in progress', 'overdue')";

    public String getTotalLendData() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_TOTAL_AMOuNT_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String totalLentAmount = rs.getString("total_lent_amount");
                if (totalLentAmount != null) {
                    return totalLentAmount;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "0"; // 기본값으로 0을 반환하거나 원하는 값을 반환할 수 있습니다.
    }


    private static final String GET_OVERDUE_AMOuNT_QUERY =
            "SELECT SUM(loan_lend.loan_amount) AS overdue_lent_amount FROM hanaroDB.loan_payments JOIN hanaroDB.loan_lend ON loan_payments.loan_lend_idx = loan_lend.lend_idx WHERE loan_payments.payment_status IN ('overdue')";

    public String getOverdueLendData() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_OVERDUE_AMOuNT_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String overdueLentAmount = rs.getString("overdue_lent_amount");
                if (overdueLentAmount != null) {
                    return overdueLentAmount;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "0";
    }
    private static final String GET_COUNT_PENDING_QUERY =
            "SELECT COUNT(loan_status) AS pending_loans FROM hanaroDB.loan_lend WHERE loan_status = 'pending'";

    public String getCountPendingLends() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_COUNT_PENDING_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String pendingLoansCount = rs.getString("pending_loans");
                if (pendingLoansCount != null) {
                    return pendingLoansCount;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "0";
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
                String overduePercentage = rs.getString("overdue_percentage");
                if (overduePercentage != null) {
                    return overduePercentage;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "0";
    }

    private static final String GET_COUNT_PAYMENT_STATUS =
            "SELECT payment_status, COUNT(payment_status) as count FROM hanaroDB.loan_payments WHERE payment_status IN ('in progress', 'paid', 'overdue') GROUP BY payment_status";

    public Map<String, Integer> getCountPaymentStatus() throws SQLException {
        Map<String, Integer> statusCounts = new HashMap<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_COUNT_PAYMENT_STATUS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String status = rs.getString("payment_status");
                int count = rs.getInt("count");
                statusCounts.put(status, count);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return statusCounts;
    }

    private static final String GET_RATIO_OF_LOAN_TYPE =
            "SELECT\n" +
                    "    loan_type_name,\n" +
                    "    ROUND(COUNT(*) / (SELECT COUNT(*) FROM hanaroDB.loan_lend) * 100, 2) AS percentage\n" +
                    "FROM\n" +
                    "    hanaroDB.loan_lend AS ll\n" +
                    "    JOIN hanaroDB.loan_products AS lp ON ll.loan_idx = lp.loan_idx\n" +
                    "    JOIN hanaroDB.loan_types AS lt ON lp.loan_type_id = lt.loan_type_idx\n" +
                    "WHERE\n" +
                    "    lt.loan_type_name IN ('전세대출', '월세대출', '담보대출')\n" +
                    "GROUP BY\n" +
                    "    loan_type_name;";

    public Map<String, String> getRatioOfLoanType() throws SQLException {
        Map<String, String> loanTypeLists = new HashMap<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_RATIO_OF_LOAN_TYPE);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String loanTypeName = rs.getString("loan_type_name");
                String percentage = rs.getString("percentage");
                loanTypeLists.put(loanTypeName, percentage);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loanTypeLists;
    }
}
