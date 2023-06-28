package com.DAO;

import com.Model.Product;
import com.utils.DatabaseConnector;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertProductDao {
//    Connection conn= null;
//    PreparedStatement ps = null;
    CallableStatement ps = null;

    public void insertProduct(Product product) throws SQLException, ClassNotFoundException {
        System.out.println("Dao 들어옴1");
        try{
            Connection conn = DatabaseConnector.getConnection();
//            String sql="INSERT INTO hanaroDB.loan_products (loan_name, loan_type_id, loan_description, loan_interest_rate, overdue_interest_rate, lend_limit, lend_period, min_credit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//            ps=conn.prepareStatement(sql);

            String sql = "{CALL InsertProduct(?, ?, ?, ?, ?, ?, ?, ?)}";

            System.out.println(sql);
            ps=conn.prepareCall(sql);

            ps.setString(1, product.getName());
            System.out.println("testestsest");
            Integer loan_type = null;
            String s1=product.getLoanTypeName();
            if (s1.equals("전세대출")){
                loan_type=1;
            }
            else if (s1.equals("월세대출")){
                loan_type=2;
            }
            else if (s1.equals("담보대출")){
                loan_type=3;
            }

            ps.setInt(2, loan_type);
            ps.setString(3, product.getDescription());
            ps.setBigDecimal(4, product.getInterestRate());
            ps.setBigDecimal(5, product.getOverdueInterestRate());
            ps.setBigDecimal(6, product.getLendLimit());
            ps.setInt(7, product.getLoanPeriod());
            ps.setInt(8, product.getMinCredit());


            System.out.println("값 들어감");
            System.out.println(ps);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}

//        } catch (SQLException e) {
//            if (conn != null) {
//                conn.rollback();
//            }
//            throw e;
//        } catch (ClassNotFoundException e) {
//            if (conn != null) {
//                conn.rollback();
//            }
//            throw e;
//        } finally {
//            if (ps != null) {
//                ps.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//}

