package kr.or.ddit.mbti.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mapper.MbtiMapper;
import kr.or.ddit.mapper.impl.MbtiMapperImpl;
import kr.or.ddit.mbti.service.MbtiService;
import kr.or.ddit.mbti.service.MbtiServiceImpl;
import kr.or.ddit.vo.MbtiVO;

@WebServlet("/rest/mbti")
public class MbtiRestController extends HttpServlet{
	private MbtiMapper service = new MbtiMapperImpl();
	Gson gson = new Gson();
	
//	@Override ==> CorsFilter 로 이동.
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//*****
//		// CORS 헤더 설정
//		resp.setHeader("ACCESS-CONTROL-ALLOW-ORIGIN", "http://localhost:6060");
//		resp.setHeader("ACCESS-CONTROL-ALLOW-METHODS", "GET,POST,PUT,DELETE");
//		resp.setHeader("ACCESS-CONTROL-ALLOW-HEADERS", "content-type");
//		//*****
//		
//		super.service(req, resp);
//	}
//	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
		List<MbtiVO> list = service.readMbtiList();
		gson.toJson(list, resp.getWriter());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MbtiVO newMbti = gson.fromJson(req.getReader(), MbtiVO.class);
		service.createMbti(newMbti);
		resp.sendRedirect(req.getContextPath() + "/rest/mbti");
	}
}