package kr.or.ddit.test;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class test extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/test01/form.jsp").forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String zipcode1 = req.getParameter("zipcode1");
        String zipcode2 = req.getParameter("zipcode2");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");

        String cellphone = req.getParameter("cellphone1") + "-" +
                        req.getParameter("cellphone2") + "-" +
                        req.getParameter("cellphone3");

        String phone = req.getParameter("phone1") + "-" +
                       req.getParameter("phone2") + "-" +
                       req.getParameter("phone3");

        String email = req.getParameter("email_id") + req.getParameter("email_domain");

        String calendar = req.getParameter("calendar");
        
        String birth = req.getParameter("birth_year") + "-" +
                       req.getParameter("birth_month") + "-" +
                       req.getParameter("birth_day");

        System.out.println("아이디: " + userid);
        System.out.println("비밀번호: " + password);
        System.out.println("이름: " + name);
        System.out.println("우편번호: " + zipcode1 + "-" + zipcode2);
        System.out.println("주소: " + address1 + " " + address2);
        System.out.println("휴대전화: " + cellphone);
        System.out.println("전화번호: " + phone);
        System.out.println("이메일: " + email);
        System.out.println("생년월일: " +  calendar + birth);
    
	}
}
