package com.DAO;

import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEmployeeAuthDao {
    Connection conn=null;
    PreparedStatement ps = null;
    public void updateEmployeeAuth(Integer empIdx, String employeeAuthName) throws SQLException {
        try {
            conn = DatabaseConnector.getConnection();
            String sql="UPDATE hanaroDB.employees SET authority_idx = ? WHERE employee_idx=?";
            ps=conn.prepareStatement(sql);


            String s1=employeeAuthName;
            Integer empAuthId;
            if (s1.equals("all")){
                empAuthId=1;
            }
            else if (s1.equals("managing Products")){
                empAuthId=2;
            }
            else if (s1.equals("managing Customers")){
                empAuthId=3;
            }
            else if (s1.equals("read only")){
                empAuthId=4;
            }
            else{
                empAuthId=5;
            }

            ps.setInt(1, (empAuthId));
            ps.setInt(2, empIdx);

            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

}
