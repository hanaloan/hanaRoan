package com.Controller;

import com.DAO.EmployeeManagementDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeManagementController extends HttpServlet {

    public EmployeeManagementController() {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        req.setCharacterEncoding("UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String uri=request.getRequestURI();
        String context = request.getContextPath();
        String command = uri.substring(context.length());
        String site=null;

        System.out.println("command : "+command);

        EmployeeManagementDao emp=new EmployeeManagementDao();

        switch (command){
            case "/DashBoardTest.do":
                site="DashBoardTest.jsp";
                break;
            case "/EmployeeManagement.do":
                site=EmployeeManagementDao.selectEmployee(request, response);
                break;
            default:break;
        }
        RequestDispatcher dispatcher =request.getRequestDispatcher(site);
        dispatcher.forward(request, response);




    }


}
