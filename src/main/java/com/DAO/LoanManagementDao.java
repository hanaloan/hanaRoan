package com.DAO;

import com.Model.Product;
import com.utils.DatabaseConnector;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanManagementDao {
    PreparedStatement ps=null;
    Connection conn = null;
    ResultSet rs=null;

    public List<Product> selectProducts(String option1) throws SQLException {
        List<Product> productList = new ArrayList<>();
        try{
            conn = DatabaseConnector.getConnection();
            String sql="SELECT lp.loan_idx, lp.loan_name, lt.loan_type_name, lp.min_credit, lp.lend_limit, lp.lend_period, lp.loan_description, lp.loan_interest_rate, lp.overdue_interest_rate\n" +
                    "FROM loan_products lp JOIN loan_types lt ON lp.loan_type_id = lt.loan_type_idx";

//            String[] option = getLoanType(String option);


            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {
                Integer loan_idx=rs.getInt("loan_idx");
                String loan_name = rs.getString("loan_name");
                String loan_type_name = rs.getString("loan_type_name");
                Integer min_credit = rs.getInt("min_credit");
                BigDecimal lend_limit = rs.getBigDecimal("lend_limit");
                Integer lend_period=rs.getInt("lend_period");
                String loan_description = rs.getString("loan_description");
                BigDecimal loan_interest_rate = rs.getBigDecimal("loan_interest_rate");
                BigDecimal overdue_interest_rate = rs.getBigDecimal("overdue_interest_rate");

                Product product = new Product(loan_idx, loan_name, loan_type_name,min_credit,lend_limit, lend_period,loan_description, loan_interest_rate , overdue_interest_rate );
                productList.add(product);
            }


        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return productList;
    }








}
