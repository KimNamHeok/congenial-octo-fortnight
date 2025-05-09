package kr.or.ddit.servlet01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet("/now.do")
public class NowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		StringBuffer html = new StringBuffer();
		
		html.append("<html>");
		html.append("<body>");
		Date now = new Date(); 
		html.append(
				String.format("<h1>당신의 접속 시간 : %s </h1>", now)				
				);
//		html.append("<h1>access time :"+now+" </h1>");
//		상수가 총 3개가 들어가야함. 우리가 선택할 수 잇는 것은 formatting을 사용가능함.
		
		html.append("</body>");
		html.append("</html>");
		
		response.getWriter().println(html);
		
	}

}
