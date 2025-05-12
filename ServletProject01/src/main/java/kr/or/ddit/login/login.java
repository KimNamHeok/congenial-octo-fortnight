package kr.or.ddit.login;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("아이디");
        String password = request.getParameter("비밀번호");
        String name = request.getParameter("이름");
        String address = request.getParameter("주소");
        String phone = request.getParameter("휴대전화");
        String email = request.getParameter("이메일");
        String birthDate = request.getParameter("생년월일");
        String agreement = request.getParameter("이메일동의");

        if (userId == null || password == null || name == null || email == null) {
            response.getWriter().write("<script>alert('필수 입력 항목이 누락되었습니다. 다시 확인해주세요.'); history.back();</script>");
            return;
        }

        if (agreement == null || !agreement.equals("동의합니다")) {
            response.getWriter().write("<script>alert('이메일 수신 동의를 체크해야 합니다.'); history.back();</script>");
            return;
        }

        // 회원 정보를 JSP 페이지로 전달
        request.setAttribute("userId", userId);
        request.setAttribute("password", password);
        request.setAttribute("name", name);
        request.setAttribute("address", address);
        request.setAttribute("phone", phone);
        request.setAttribute("email", email);
        request.setAttribute("birthDate", birthDate);
        request.setAttribute("agreement", agreement);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/01/result.jsp");
        dispatcher.forward(request, response);
    }
}