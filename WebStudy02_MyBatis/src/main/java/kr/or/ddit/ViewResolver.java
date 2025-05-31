package kr.or.ddit;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ViewResolver {
	public default void setPrefix(String prefix) {}
	public default void setSuffix(String suffix) {}
	
	public void resolveView(String logicalViewName, HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException;
}
