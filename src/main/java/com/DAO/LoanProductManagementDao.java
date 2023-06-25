package com.DAO;

import com.Model.Employee;
import com.Model.Product;
import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanProductManagementDao {
    PreparedStatement ps=null;

    public List<Product> selectProducts(){
        List<Product> productList = new ArrayList<>();
        try{
            Connection conn = DatabaseConnector.getConnection();
            String sql="SELECT lp.loan_idx, lp.loan_name, lt.loan_type_name,lp.start_date, lp.end_date, lp.min_credit, lp.lend_limit, lp.lend_period, lp.loan_description, lp.loan_interest_rate, lp.overdue_interest_rate\n" +
                    "FROM loan_products lp JOIN loan_types lt ON lp.loan_type_id = lt.loan_type_idx";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {
//                Integer idx=rs.getInt("employee_idx");
//                String name = rs.getString("name");
//                String levelName = rs.getString("authority_type");
//                Product emp = new Product(idx, name, levelName );
//                productList.add(emp); // 주석2 리스트에 담아주고
            }
            conn.close();
            ps.close();
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;




}
}
