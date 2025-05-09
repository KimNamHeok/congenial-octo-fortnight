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
		application = getServletContext();
		folderPath =  application.getInitParameter("movieFolder");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String videoName = req.getParameter("video");
		if(videoName==null || videoName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "비디오 이름 누락");
			return;
		}

		Path filePath = Paths.get(folderPath, videoName);
		System.out.println(filePath);
		
		if(!Files.exists(filePath)){
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "그런 영상은 없음.");
			return;
		}
		
		// 검증 통과하면 스트리밍 서비스 제공
		
		String mime = application.getMimeType(videoName);
		
		resp.setContentType(mime);
		
		try(
			ServletOutputStream out = resp.getOutputStream();
		){
			Files.copy(filePath, out);
		}
	}
}












