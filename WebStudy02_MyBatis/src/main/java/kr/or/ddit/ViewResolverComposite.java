package kr.or.ddit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ViewResolverComposite implements ViewResolver {
	
	private List<ViewResolver> resolvers;
	{
		resolvers = new ArrayList<ViewResolver>();
		resolvers.add(new ContentNegotiatingViewResolver());		
		resolvers.add(new InternalResourceViewResolver());		
	}

	@Override
	public void resolveView(String logicalViewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(Optional.ofNullable(logicalViewName)
				.filter((lvn)->lvn.startsWith("redirect:"))
				.isPresent())
		{
			logicalViewName = logicalViewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + logicalViewName);
			return;
		}
		
		for(ViewResolver single : resolvers) {
			try {
				single.resolveView(logicalViewName, req, resp);
				break;
			}catch (Exception e) {
				log.warn("{} 를 해결하다 문제 발생했음. {}", logicalViewName, e.getMessage());
				continue;
			}
		}
	}

}










