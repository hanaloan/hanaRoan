package com.Service;

import com.DAO.InsertProductDao;
import com.Model.Product;
import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class InsertProductService {
    private InsertProductDao insertProductDao;

    public InsertProductService() {
        insertProductDao = new InsertProductDao();
    }

//    public void insertProduct(Product product){
//        System.out.println("서비스 왔음");
//        insertProductDao.insertProduct(product);
//        System.out.println("서비스 나옴");
//    }


    //예외철 및 롤백 논리 구현
    public void insertProduct(Product product) throws SQLException, ClassNotFoundException {
        System.out.println("서비스 왔음");
        Connection conn = null;
        try {
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);
            System.out.println("DAO 들어가기 직전");
            insertProductDao.insertProduct(product);
            System.out.println("DAO 나오자마자");
            conn.commit();
            System.out.println("서비스 나옴");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Perform rollback if any exception occurs
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
//                conn.close();
            }
        }
    }
}


