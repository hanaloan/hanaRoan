package com.Controller;

import com.DAO.LoanLendDAO;
import com.Model.LoanLend;
import com.Service.LoanLendService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ApplyLoan")
public class ApplyLoanController extends HttpServlet {

    private LoanLendService loanLendService;

    @Override
    public void init() throws ServletException {
        // 서비스와 DAO 객체를 초기화
        loanLendService = new LoanLendService(new LoanLendDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerIdx = Integer.parseInt(request.getParameter("customer_idx"));
        int loanIdx = Integer.parseInt(request.getParameter("loan_idx"));
        String startDate = request.getParameter("start_date");
        String endDate = request.getParameter("end_date");
        double loanAmount = Double.parseDouble(request.getParameter("loan_amount"));
        String loanStatus = request.getParameter("loan_status");

        // LoanLend 객체 생성
        LoanLend loanLend = new LoanLend(customerIdx, loanIdx, startDate, endDate, loanAmount, loanStatus);

        // 서비스를 통해 데이터베이스에 INSERT 실행
        loanLendService.applyLoan(loanLend);

        // 성공적으로 처리되었을 경우에 대한 처리
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Loan Application Submitted Successfully</h2>");
        out.println("<p>Thank you for applying for a loan. Your application has been submitted.</p>");
        out.println("<p>Date: " + new Date() + "</p>");
        out.println("<form action=\"/jsp/CustomerManagement/CustomerManagement.jsp\" method=\"get\">");
        out.println("<input type=\"submit\" value=\"Go to Customer Management\">");
        out.println("</form>");
        out.println("</body></html>");
    }
}