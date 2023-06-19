package com.Controller;

import com.Model.LoginUser;
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
        // 요청 파라미터 추출
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 형식적 유효성 검사
        // 유효하지 않은 비밀번호 형식인 경우
        if (!ValidationRegex.isRegexPassword(password)) {
            String errorPageUrl = req.getContextPath() + "/jsp/login/loginError_format.jsp";
            resp.setContentType("text/html");
            resp.getWriter().println("<script>window.open('" + errorPageUrl + "', '_blank');</script>");
            return;
        }



        // User 모델 생성
        LoginUser loginUser = new LoginUser(username, password);

        // 모든 형식적 유효성 검사 통과하면 비즈니스로직 시작
        try {
            // LoginService를 사용하여 의미적 유효성 검사 및 인증 수행
            boolean authenticated = loginService.authenticateUser(loginUser);

            if (authenticated) {
                // 로그인 성공 시 응답
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Login successful");

                HttpSession session = req.getSession();
                session.setAttribute("loggedInUser", username);
                String employeeUrl = req.getContextPath() + "/jsp/ManageEmployee/EmployeeManagement.jsp";
                resp.sendRedirect(employeeUrl);

            } else {
                // 로그인 실패 시 응답
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().println("Login failed");
            }
        } catch (SQLException e) {
            // 예외 발생 시 응답
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal server error");
        }
    }
}