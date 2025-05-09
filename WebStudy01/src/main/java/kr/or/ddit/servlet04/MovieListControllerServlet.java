package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/04/movieList")
public class MovieListControllerServlet extends HttpServlet{
	private ServletContext application;
	private String folderPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		application = getServletContext();
		folderPath = application.getInitParameter("movieFolder");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File folder = new File(folderPath);
		String[] children = folder.list();
		
//		String options = Arrays.stream(children).filter((fn)->{		// 최종적으로 options를 만드는거임
//			String mime = application.getMimeType(fn);
//			return mime!=null && mime.startsWith("video/");
//		}).map((fn)->String.format("<option>%s</option>", fn))
//		.collect(Collectors.joining("\n"));
//		
//		req.setAttribute("options", options);
		
		req.setAttribute("children", children);

		req.getRequestDispatcher("/WEB-INF/views/04/movieList.jsp").forward(req, resp);
	}
}









