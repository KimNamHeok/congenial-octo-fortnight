package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/item/form")
public class formServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/item01/form.jsp").forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		System.out.println("name : " + name);
		
		Part part = req.getPart("boFile");
		String key = part.getName();
		String fileName = part.getSubmittedFileName();
		long fileSize = part.getSize();
		String mimeType = part.getContentType();
		System.out.println("key : " + key);
		System.out.println("filename : " + fileName);
		System.out.println("fileSize : " + fileSize);
		System.out.println("memeType : " + mimeType);
		
		fileUpload(req, part);
		
		resp.sendRedirect(req.getContextPath() + "/item/result");
	}
	
		private void fileUpload(HttpServletRequest req, Part part) {
			
			//1. 업로드 경로를 만든다.
			String uploadPath = req.getServletContext().getRealPath("/resources/upload");
			System.out.println("uploadPath : " + uploadPath);
			// 2. 업로드 할때 사용할 파일명을 만든다.
			// 파일명(UUID_원본파일명)
			String fileName = UUID.randomUUID().toString() + "_" + part.getSubmittedFileName();
			System.out.println("fileName : " + fileName);
			
			// 3. 업로드 하기 위한 폴더 경로를 실제로 만든다.
			File file = new File(uploadPath);
			if(!file.exists()) {		// 내가 설정한 경로에 파일이 없으면 
				file.mkdirs();			// mkdirs는 저 경로 파일들 전부 만듬, mkdir는 upload 파일만 만듬 중간 파일 없으면 오류남 -> 편리를 위해 mkdirs
				
			}
			
			try {
				part.write(uploadPath + "/" + fileName);	// 해당경로에 파일명으로 파일을 써라
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	
}
