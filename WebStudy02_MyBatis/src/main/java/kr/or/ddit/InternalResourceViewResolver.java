package kr.or.ddit;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;

/**
 * 동일한 폴더 아래서 관리되는 forward 로 이동할 view layer 를 대상으로 하는 객체.
 */
public class InternalResourceViewResolver implements ViewResolver{
	private String prefix = "/WEB-INF/views/";
	private String suffix = ".jsp";
	
	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}


	@Override
	public void resolveView(String logicalViewName, HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		String view = prefix + logicalViewName + suffix;
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
