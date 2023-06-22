package com.DAO;

import com.Model.Employee;
import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEmployeeDao {

    PreparedStatement ps = null;

    public void updateEmployee(Employee employee){
        try{
            Connection conn = DatabaseConnector.getConnection();
//            String sql = "UPDATE hanaroDB.employees SET "
//            ps=conn.prepareStatement(sql);






        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}
