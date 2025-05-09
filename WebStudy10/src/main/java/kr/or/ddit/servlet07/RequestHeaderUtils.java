package kr.or.ddit.servlet07;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

public class RequestHeaderUtils {
	public Map<String,String> requestHeaderToMap(HttpServletRequest req){
		Map<String, String> header = new HashMap<>();
		Enumeration<String> names = req.getHeaderNames();
		while (names.hasMoreElements()) {
			String headerName = (String) names.nextElement();
			header.put(headerName, req.getHeader(headerName));
		}
		req.setAttribute("header", header);
		return header;
	}
}
