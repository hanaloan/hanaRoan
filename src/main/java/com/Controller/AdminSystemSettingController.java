package com.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminSystemSettingController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 버튼누르면 기본적으로 조회만 하니까 Get으로 통일했습니다.
        HttpSession session = req.getSession();
        Integer employeeIdx = (Integer) session.getAttribute("employee_idx");

        // employeeIdx 변수를 이용해서 db접근해서 원하는 정보 가지고 오시면 됩니다.
        System.out.println(employeeIdx);


        //이거는 뷰단으로 인덱스 보내는건데, 테스트용입니다.
        req.setAttribute("employee_idx", employeeIdx);
        req.getRequestDispatcher("/jsp/AdminSystemSetting/AdminSystemSetting.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}