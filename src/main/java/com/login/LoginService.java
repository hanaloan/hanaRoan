package com.login;

import com.login.model.User;
import com.utils.ValidationRegex;

import java.sql.SQLException;

public class LoginService {

    private final LoginDao loginDao;

    public LoginService() {
        loginDao = new LoginDao();
    }

    public boolean authenticateUser(User user) throws SQLException {

        // 의미적 유효성 검사
        if (!isMeaningfulValidationPassed(user)) {
            return false;
        }

        // LoginDao의 로그인 메소드 호출
        return loginDao.login(user);
    }

    private boolean isMeaningfulValidationPassed(User user) {
        // 여기에 의미적 유효성 검사 로직을 구현

        return true; // 의미적 유효성 검사 통과
    }
}