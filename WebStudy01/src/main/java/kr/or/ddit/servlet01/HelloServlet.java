package kr.or.ddit.servlet01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello.html")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer html = new StringBuffer();
		html.append("<html>");
		html.append("<body>");
		html.append("<h1>Hello Servlet</h1>");
		html.append("</body>");
		html.append("</html>");
		resp.getWriter().println(html);
	}
}
