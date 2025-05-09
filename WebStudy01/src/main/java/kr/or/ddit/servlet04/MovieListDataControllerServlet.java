package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 	웹어플리케이션의 아키텍처
 *  Model1 : 요청과 응답에 대한 모든 책임을 하나의 객체(서블릿, jsp)로 처리할 때.
 *  Model2 : 요청에 대한 처리를 담당하는 객체와 동적 응답을 생성하는 객체가 분리된 구조.
 *  		 동적 응답을 생성하는 객체(view)가 분리된 구조.
 *  		HTML 컨텐츠를 생성하는 경우, controller(servlet), view(jsp)
 *  		템플릿 소스와 동적 데이터를 결합할 수 있는 주체가 필요함 -> 템플릿 엔진
 *  		서버사이드 템플릿 엔진(SSR) : jsp, thymleaf, mustache, freemarker. velocity
 *  		클라이언트 사이드 템플릿 엔진(CSR) : React, VueJs, AngularJs
 *  		JSON 켄텐츠를 생성하는 경우, controller(servlet), view(servlet)
 *  최종 HTML 의 형태가 완성되는 위치에 따른 랜더링 방식
 *  SSR(Server Side Rendering) : 최종 HTML 이 서버에서 만들어지고, 한번의 응답으로 전송된 후
 *  							클라이언트는 렌더링만 수행함
 *  							(한번의 요청이면 충분)
 *  CSR(Client Side Rendering) : 초기 로딩시에 템플릿 HTML 이 응답으로 전송되고,
 *  							스크립트 기반의 비동기 요청으로 데이터 응답을 수신한 후,
 *  							클라이언트가 템플릿과 데이터를 결합하여 최종 HTML 을 생성하고,
 *  							최종적으로 랜더링함.
 *  							(템플릿에 대한 요청, 중간에 스크립트, 데이터에 대한 요청) => 총 3번의 요청
 */
@WebServlet("/04/movieListData")
public class MovieListDataControllerServlet extends HttpServlet{
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
		String[] children = folder.list((d, n)->{
			String mine = application.getMimeType(n);
			return mine!=null && mine.startsWith("video");
		});
//		Marshalling : native data 의 표현 구조를 공통 메시지 형식(json)으로 바꾸는 작업
		Gson gson = new Gson();	// gson 객체필요
		String json = gson.toJson(children);	// gson의 toJson메서드 사용, 문자열로 된 json
		resp.setContentType("application/json;charset=UTF-8");
		try(
			// Closable 객체 생성 및 선언 구문
			PrintWriter out = resp.getWriter();
		){
			out.print(json);
		}
	}
}









