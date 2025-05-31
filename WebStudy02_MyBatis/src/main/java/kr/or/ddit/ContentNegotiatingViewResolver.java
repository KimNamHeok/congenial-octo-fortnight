package kr.or.ddit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * "Accept" 요청 헤더를 기반으로 응답 컨텐츠의 형태를 결정함.
 * request "Accept" === response "Content-Type"
 * 
 * 
 */

public class ContentNegotiatingViewResolver implements ViewResolver {

	@Override
	public void resolveView(String logicalViewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String accept = req.getHeader("accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			// json 으로 직렬
			Enumeration<String> names = req.getAttributeNames();
			Map<String, Object> target = new HashMap<>();
			while (names.hasMoreElements()) {
				String attName = (String) names.nextElement();
				Object attValue = req.getAttribute(attName);
				target.put(attName, attValue);
			}
			Gson gson = new Gson();
//			String json = gson.toJson(target);
			resp.setContentType("application/json");
			try(
				PrintWriter out = resp.getWriter();
			){
//				out.print(json);
				gson.toJson(target, out);
			}
		}else {
			throw new ServletException("ContentNegotiatingViewResolver는 뷰를 해결하지 못했음.");
		}

	}

}
