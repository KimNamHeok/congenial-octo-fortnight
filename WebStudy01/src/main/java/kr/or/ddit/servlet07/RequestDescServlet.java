package kr.or.ddit.servlet07;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Collection View ? 집합객체에 대한 순차 접근을 정의하고 있는 인터페이스
 * Iterator, Enumeration
 * while(end point check){
 *  pointer 이동
 * }
 */

@WebServlet("/07/requestDesc.do")
public class RequestDescServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Accept 헤더로 응답의 content-type을 협상(Content Negotiation)함.
		System.out.println("GET 요청 수신");
		Enumeration<String> names = req.getHeaderNames();
		while(names.hasMoreElements()) {
			String headerName = names.nextElement();
			System.out.printf("%s's value : %s\n", 
					headerName, req.getHeader(headerName));
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// body 가 있는 요청의 경우, content-* 헤더를 통해 body 의 컨텐츠를 판단함.
		
		System.out.println("POST 요청 수신");
		Map<String, String> header = new RequestHeaderUtils().requestHeaderToMap(req);
		for(Entry<String, String> entry : header.entrySet()) {
			System.out.printf("%s : %s \n", entry.getKey(), entry.getValue());
		}
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PUT 요청 수신");
		new RequestHeaderUtils().requestHeaderToMap(req)
				.forEach((k,v)->System.out.printf("%s:%s\n", k,v));
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(req, resp);
	}
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(req, resp);
	}
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(req, resp);
	}
}
