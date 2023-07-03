package com.Controller;

import com.Model.*;
import com.Service.ApplyProductService;
import com.Service.LoginService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet("/applyProduct")
public class ApplyProductController extends HttpServlet {
    private ApplyProductService applyProductService;
    private LoginService loginService;
    @Override
    public void init() throws ServletException {
        super.init();
        applyProductService = new ApplyProductService();
        loginService = new LoginService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession session = request.getSession();
            String productId = request.getParameter("productId");
            String customerIdx = session.getAttribute("customer_Idx").toString();
            ApplyProductRes productInfo = applyProductService.getApplyProductInfo(productId);
            ApplyCustomerRes customerInfo = applyProductService.getApplyCustomerInfo(customerIdx);
            request.setAttribute("productInfo", productInfo);
            request.setAttribute("customerInfo", customerInfo);
            request.getRequestDispatcher("jsp/ApplyProduct/ApplyProduct.jsp").forward(request, response);
        } catch (SQLException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        try{
            request.setCharacterEncoding("UTF-8");
            if(request.getParameter("customerIdx") != null
                    && request.getParameter("loanIdx") != null
                    && request.getParameter("lendAmount") != null){
                String loanIdx = request.getParameter("loanIdx");
                String customerIdx = request.getParameter("customerIdx");
                String lendAmount = request.getParameter("lendAmount");
                applyProductService.applyProduct(loanIdx, customerIdx, lendAmount);
            }
            HttpSession session = request.getSession();
            LoginUserRes loginUserRes = (LoginUserRes) session.getAttribute("loginUserRes");

            LoginCreditScoreModelReq modelReq = new LoginCreditScoreModelReq(loginUserRes.getCustomer_Idx());
            LoginCreditScoreModelRes modelRes = loginService.getCreditScore(modelReq);

            int credit = modelRes.getCreditScore();
            int income = modelRes.getIncome();

            // 사용자 정보관련
            request.setAttribute("username", loginUserRes.getName());
            request.setAttribute("income", income);
            request.setAttribute("credit", credit);
            request.setAttribute("customer_idx", loginUserRes.getCustomer_Idx());

            // 추천상품 관련
            LoginRecommendationReq recoReq = new LoginRecommendationReq(loginUserRes.getCustomer_Idx(), income, credit);
            LoginRecommendationRes recoRes = loginService.getRecoProduct(recoReq);
            request.setAttribute("recoRes", recoRes);

            // 알림 관련
            LoginAlertMessageReq alertReq = new LoginAlertMessageReq(loginUserRes.getCustomer_Idx());
            LoginAlertMessageRes alertRes = loginService.getAlertMessages(alertReq);
            request.setAttribute("alertRes", alertRes);

            // 세션 관련
            session.setAttribute("username", loginUserRes.getName());
            session.setAttribute("customer_Idx", loginUserRes.getCustomer_Idx());

            // 사용자 가입중인 상품 관련
            LoginPersonalProductReq personalProductReq = new LoginPersonalProductReq(loginUserRes.getCustomer_Idx());
            LoginPersonalProductRes personalProductRes = loginService.getPersonalProducts(personalProductReq);
            request.setAttribute("personalProducts", personalProductRes);

//            response.sendRedirect("https://www.hanaroan.shop/CustomerHome");
            request.getRequestDispatcher("jsp/CustomerHome/CustomerHome.jsp").forward(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

}