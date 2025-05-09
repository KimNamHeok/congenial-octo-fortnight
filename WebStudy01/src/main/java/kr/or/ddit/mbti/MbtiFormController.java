package kr.or.ddit.mbti;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import com.google.gson.Gson;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * HttpServlet
 * HttpServletRequest
 * HttpServletResponse
 * HttpSession
 * ServletContext : 하나의 어플리케이션(컨텍스트)내에서 싱글턴으로 존재함.
 * 
 */
@WebServlet(loadOnStartup = 1, value="/mbti/form")
public class MbtiFormController extends HttpServlet{
	private Properties mbtiProps;
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		String qualifiedName = "/kr/or/ddit/mbti/Mbti.properties";
		try (
			InputStream is = MbtiFormController.class.getResourceAsStream(qualifiedName);
			Reader reader = new InputStreamReader(is, "UTF-8")
		) {
			mbtiProps = new Properties();
			mbtiProps.load(reader);
			application.setAttribute("mbtiProps", mbtiProps);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession session = req.getSession();
		String accept = req.getHeader("accept");
		if(accept.contains("json")) {
			resp.setContentType("application/json");
			Gson gson = new Gson();
			String json = gson.toJson(mbtiProps);
			resp.getWriter().print(json);
		}else {
			req.setAttribute("mbtiProps", mbtiProps);
			String contentPage = "/WEB-INF/views/mbti/mbtiForm.jsp";
			req.setAttribute("contentPage", contentPage);
			req.getRequestDispatcher("/WEB-INF/views/mbti/mbtiModule/layout.jsp").forward(req, resp);
		}
		
	}
}