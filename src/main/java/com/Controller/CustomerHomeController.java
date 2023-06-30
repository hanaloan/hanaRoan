package com.Controller;
import com.Model.*;
import com.Service.LoginService;

import com.Service.LoginService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


public class CustomerHomeController extends HttpServlet {
    private final LoginService loginService;

    public CustomerHomeController() {
        loginService = new LoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer customer_Idx = (Integer) session.getAttribute("customer_Idx");
        String username = (String) session.getAttribute("username");

        //재사용 -> 소득, 신용도 얻기 위함(Login -> 커스터홈 로직 말고 GNB -> 커스터홈이 겹치기 때문)
        LoginCreditScoreModelReq loginCreditScoreModelReq = new LoginCreditScoreModelReq();
        loginCreditScoreModelReq.setCustomer_Idx(customer_Idx);
        try {
            LoginCreditScoreModelRes loginCreditScoreModelRes = loginService.getCreditScore(loginCreditScoreModelReq);

            // 사용자 정보관련(재사용)
            req.setAttribute("username", username);
            req.setAttribute("income", loginCreditScoreModelRes.getIncome());
            req.setAttribute("credit", loginCreditScoreModelRes.getCreditScore());
            req.setAttribute("customer_idx",customer_Idx);

            // 추천상품 관련(재사용)
            LoginRecommendationReq recoReq = new LoginRecommendationReq(customer_Idx, loginCreditScoreModelRes.getIncome(), loginCreditScoreModelRes.getCreditScore());
            LoginRecommendationRes recoRes = loginService.getRecoProduct(recoReq);
            req.setAttribute("recoRes", recoRes);

            // 로그인 성공 시 응답
            resp.setStatus(HttpServletResponse.SC_OK);
            req.getRequestDispatcher("/jsp/CustomerHome/CustomerHome.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

// 유저네임, 소득, 신용도, cus_pk
//<h1>Welcome, <%= request.getAttribute("username") %>!</h1>
//<p>Income: <%= request.getAttribute("income") %></p>
//<p>Credit: <%= request.getAttribute("credit") %></p>
//<p>Customer Idx: <%= request.getAttribute("customer_idx") %></p>
//
// 이거는 있던거 로직활용(재사용성)
//<p>Loan Image: <%= product.getLoanImage() %></p>
//<p>Loan Name: <%= product.getLoanName() %></p>
//<p>Loan Interest Rate: <%= product.getLoanInterestRate() %></p>