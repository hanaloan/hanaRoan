package com.Service;

import com.DAO.LoginDao;
import com.Model.LoginUser;

import java.sql.SQLException;

public class LoginService {

    private final LoginDao loginDao;

    public LoginService() {
        loginDao = new LoginDao();
    }

    public boolean authenticateUser(LoginUser loginUser) throws SQLException {

        // 의미적 유효성 검사
        if (!isMeaningfulValidationPassed(loginUser)) {
            return false;
        }

        // LoginDao의 로그인 메소드 호출
        return loginDao.login(loginUser);
    }

    private boolean isMeaningfulValidationPassed(LoginUser loginUser) {
        // 여기에 의미적 유효성 검사 로직을 구현

        return true; // 의미적 유효성 검사 통과
    }
}