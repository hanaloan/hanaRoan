package com.Controller;

import com.Model.*;
import com.Service.LoginService;
import com.utils.ValidationRegex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        req.setCharacterEncoding("UTF-8");
        // 요청 파라미터 추출
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String userType = req.getParameter("userType");

        LoginUserReq loginUserReq = new LoginUserReq(username, password, userType);
        LoginForAdminReq loginForAdminReq = new LoginForAdminReq(username, password, userType);

        try {
            if ("admin".equals(userType)) {
                LoginForAdminRes loginForAdminRes = loginService.authenticateAdmin(loginForAdminReq);
                if (loginForAdminRes.isAuthenticated() && loginForAdminRes.getAuthorityType().equals("none")) {
                    String errorMessage = "권한이 없는 관리자입니다";
                    req.setAttribute("errorMessage", errorMessage);
                    req.getRequestDispatcher("/jsp/Login/login.jsp").forward(req, resp);

                } else if (loginForAdminRes.isAuthenticated()) {
                    HttpSession session = req.getSession();
                    session.setAttribute("username", loginForAdminRes.getName());
                    session.setAttribute("employee_idx", loginForAdminRes.getAdminIdx());
                    session.setAttribute("authType", loginForAdminRes.getAuthorityType());
                    //int ses=(int) session.getAttribute("employee_idx");

                    resp.sendRedirect("/jsp/DashBoard/DashBoard.jsp");

                } else {
                    String errorMessage = "Name과 Password를 확인해주세요";
                    req.setAttribute("errorMessage", errorMessage);
                    req.getRequestDispatcher("/jsp/Login/login.jsp").forward(req, resp);
                }

            } else {
                // LoginService를 사용하여 의미적 유효성 검사 및 인증 수행
                LoginUserRes loginUserRes = loginService.authenticateUser(loginUserReq);

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

                    // 알림 관련
                    LoginAlertMessageReq alertReq = new LoginAlertMessageReq(loginUserRes.getCustomer_Idx());
                    LoginAlertMessageRes alertRes = loginService.getAlertMessages(alertReq);
                    req.setAttribute("alertRes", alertRes);
                    // 세션 관련
                    HttpSession session = req.getSession();
                    session.setAttribute("username", loginUserRes.getName());
                    session.setAttribute("customer_Idx", loginUserRes.getCustomer_Idx());

                    // 로그인 성공 시 응답
                    resp.setStatus(HttpServletResponse.SC_OK);
                    req.getRequestDispatcher("/jsp/CustomerHome/CustomerHome.jsp").forward(req, resp);
                } else {

                    String errorMessage = "ID와 Password를 확인해주세요";
                    req.setAttribute("errorMessage", errorMessage);
                    req.getRequestDispatcher("/jsp/Login/login.jsp").forward(req, resp);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}