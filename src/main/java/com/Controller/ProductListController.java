package com.Controller;

//
//import com.Background.RedisConnectionPool;
//import com.Service.RedisService;

import com.Model.*;
import com.Service.DisplayProductListService;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

@WebServlet("/productList")
public class ProductListController extends HttpServlet {
    private DisplayProductListService displayProductListService;
    //private RedisService redisService;

    @Override
    public void init() throws ServletException {
        super.init();
        displayProductListService = new DisplayProductListService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Integer customer_Idx = (Integer) session.getAttribute("customer_Idx");

            String category = request.getParameter("category");
            if (category == null || category.isEmpty()) {category = "*";}

            List<ProductRes> productList = displayProductListService.getProducts(category);
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/jsp/ProductList/ProductList.jsp").forward(request, response);


//            redisService = RedisConnectionPool.getInstance().getRedisService();
//            redisService.increasePageView(customer_Idx);
//            redisService.addToPreList(customer_Idx);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
//        finally {
//            if(redisService != null) {
//                System.out.println("레디스 닫음");
//                redisService.close();
//            }
//        }

    }
}
