package com.Service;

import com.DAO.LoginDao;
import com.Model.*;

import java.sql.SQLException;

public class LoginService {

    private final LoginDao loginDao;

    public LoginService() {
        loginDao = new LoginDao();
    }

    public LoginUserRes authenticateUser(LoginUserReq user) throws SQLException {

        LoginUserRes loginUserRes = loginDao.login(user);

        // 의미적 유효성 검사 -> if 회원있음, else 회원없음
        if (loginUserRes != null && loginUserRes.getName() != null) {
            loginUserRes.setAuthenticated(true);
        } else {
            loginUserRes = new LoginUserRes(null, 0, false);
        }

        return loginUserRes;
    }

    public LoginForAdminRes authenticateAdmin(LoginForAdminReq loginForAdminReq) throws SQLException {
        // LoginDao의 로그인 메소드 호출
        LoginForAdminRes loginForAdminRes = loginDao.loginAdmin(loginForAdminReq);

        // 의미적 유효성 검사 -> if 회원있음, else 회원없음
        if (loginForAdminRes != null && loginForAdminRes.getName() != null) {
            loginForAdminRes.setAuthenticated(true);
        } else {
            loginForAdminRes = new LoginForAdminRes(null, false);
        }

        return loginForAdminRes;
    }


    public LoginCreditScoreModelRes getCreditScore(LoginCreditScoreModelReq modelReq) throws SQLException {
        return loginDao.getCreditScore(modelReq);
    }

    public LoginRecommendationRes getRecoProduct(LoginRecommendationReq recoReq) throws SQLException{
        return loginDao.getRecoProduct(recoReq);
    }
}