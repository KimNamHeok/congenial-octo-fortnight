package kr.or.ddit.servlet03;

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
@WebServlet("/03/movieList_me")
public class MovieListServlet_me extends HttpServlet {
	private ServletContext application;
	private String folderPath;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 톰캣 대화 && 컨텍스트필요 context => 어플리케이션
		// 싱글톤이기 때문에 전역변수로 빼서 썼음.
		application = getServletContext();
		folderPath = application.getInitParameter("movieFolder");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File folder = new File(folderPath);  

		StringBuffer html = new StringBuffer(); 
		html.append("<html>");
		html.append("<body>");
		html.append(String.format("<form action='%s/03/media'>", req.getContextPath()));
		html.append("<select name='video' onchange='this.form.submit();'>");
				
		String option = "<option>%s</option>";
		//list() ==> 파일명 // listFiles ==> 파일정보

		File[] files = folder.listFiles(file -> {
			if (!file.isFile()) return false;
			String name = file.getName().toLowerCase();
			return !name.endsWith(".png") &&
					(name.endsWith(".mp4") || name.endsWith(".avi") ||
							name.endsWith(".mov") || name.endsWith(".mkv") ||
							name.endsWith(".wmv") || name.endsWith(".flv"));
		});

		for(File file : files) {
			html.append(String.format(option,file.getName()));
		};
		
		
		html.append("</select>");
		html.append("<button type='submit'>전송</button>");
		html.append("</form>");
		html.append("</body>");
		html.append("</html>");
		
		resp.setContentType("text/html;charset=UTF-8");
		try(
				PrintWriter out = resp.getWriter();
		){
			out.print(html);
		}
		
	}
}
