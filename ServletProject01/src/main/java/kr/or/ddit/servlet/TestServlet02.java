package kr.or.ddit.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.vo.MemberVO;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonObject;


@WebServlet("/TestServlet02/*")
public class TestServlet02 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	 *  # 테스트
	 *  
	 *  1. http://localhost/TestServlet02/test01 을 요청했을 때는 본인의 이름을 응답으로 보내주세요.
	 *  
	 *  2. http://localhost/TestServlet02/test02 를 요청했을 떄는 본인의 이름과 나이를
	 *  <p></p> 태그가 설정된 html 문서를 만들어서 응답으로 보내주세요.
	 *  
	 *  3. http://localhost/TestServlet02/test03 를 요청했을 때는 본인의 회원번호, 아이디를
	 *   설정한 MemberVO 객체를 만들어서 응답으로 보내주세요.
	 *   (JSON
	 */
		
		PrintWriter writer = response.getWriter();		// 객체든 뭐든 보낼 때 writer보냄
		
		String URI = request.getRequestURI();
		System.out.printf("URI : {}", URI);
		
		URI = URI.split("/")[3];
		
		if(URI.equals("test01")) {
			
			writer.print("김남혁");
			response.setContentType("text/plain; charset=UTF-8");
			
		}else if(URI.equals("test02")) {
			
			writer.print("<html>");
			writer.print("	<head>");
			writer.print("		<title>TestServlet01</title>");
			writer.print("	<head>");
			writer.print("	<body>");
			writer.print("		<p>김남혁, 27</p>");
			writer.print("	</body>");
			writer.print("</html>");
			response.setContentType("text/html; charset=UTF-8");
			
		}else if(URI.equals("test03")) {

			
			MemberVO member = new MemberVO();	// 객체 생성
			member.setMemNo(1);					// 지금 우리는 DB사용 안하니 직접 member 객체에 데이터 집어넣음
			member.setMemId("admin");
			
			JsonObject jsonData = new JsonObject();	// JSON 데이터 생성
			
			jsonData.addProperty("MemNo", member.getMemNo());
			jsonData.addProperty("MemId", member.getMemId());
			
			writer.print(jsonData);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
