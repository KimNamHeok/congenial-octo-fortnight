package kr.or.ddit.servlet03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/03/media")
public class MediaStreamingServlet extends HttpServlet{
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
		String videoName = req.getParameter("video");
		if(videoName==null || videoName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "비디오 이름 누락");
			// 상태요정 응답상태 
			return;
		}
		Path filePath = Paths.get(folderPath,videoName);
		// path는 경로만 가지고 있음. 여타의 다른 속서잉 필요하면 utility => files
		System.out.println(filePath);
		
		if(!Files.exists(filePath)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,"그런 영상은 없음!");
			return;
		}
		
		String mime = application.getMimeType(videoName);
//		String mime = "video/mp4";
		resp.setContentType(mime);
		try(
			//  소괄호 안의 것은 with resource구문임.
		ServletOutputStream out =  resp.getOutputStream();
		){
			Files.copy(filePath, out);
		}
	}
}
