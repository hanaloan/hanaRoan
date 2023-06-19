package com.Controller;

import com.Model.*;
import com.Service.LoginService;
import com.utils.ValidationRegex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final LoginService loginService;

    public LoginController() {
        loginService = new LoginService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청 파라미터 추출
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 형식적 유효성 검사
        // 유효하지 않은 비밀번호 형식인 경우
//        if (!ValidationRegex.isRegexPassword(password)) {
//            String errorPageUrl = req.getContextPath() + "/jsp/login/loginError_format.jsp";
//            resp.setContentType("text/html");
//            resp.getWriter().println("<script>window.open('" + errorPageUrl + "', '_blank');</script>");
//            return;
//        }



//         User 모델 생성
        LoginUserReq user = new LoginUserReq(username, password);

        // 모든 형식적 유효성 검사 통과하면 비즈니스로직 시작
        try {
            // LoginService를 사용하여 의미적 유효성 검사 및 인증 수행
            LoginUserRes loginUserRes = loginService.authenticateUser(user);

            if (loginUserRes.isAuthenticated()) {
                // 사용자 정보관련 LoginCreditScoreModelReq 가져오면서 Idx 할당
                LoginCreditScoreModelReq modelReq = new LoginCreditScoreModelReq(loginUserRes.getCustomer_Idx());
                LoginCreditScoreModelRes modelRes = loginService.getCreditScore(modelReq);

                int credit = modelRes.getCreditScore();
                int income = modelRes.getIncome();

                // 사용자 정보관련
                req.setAttribute("username", loginUserRes.getName());
                req.setAttribute("income", income);
                req.setAttribute("credit", credit);
                req.setAttribute("customer_idx", loginUserRes.getCustomer_Idx());

                // 추천상품 관련
                LoginRecommendationReq recoReq = new LoginRecommendationReq(loginUserRes.getCustomer_Idx(), income, credit);
                LoginRecommendationRes recoRes = loginService.getRecoProduct(recoReq);
                req.setAttribute("recoRes", recoRes);

                // 로그인 성공 시 응답
                resp.setStatus(HttpServletResponse.SC_OK);
                req.getRequestDispatcher("/jsp/CustomerHome/CustomerHome.jsp").forward(req, resp);
            }else {

                String errorMessage = "ID와 Password를 확인해주세요";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/jsp/login/Login.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}