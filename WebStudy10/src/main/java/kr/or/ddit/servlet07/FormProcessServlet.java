package kr.or.ddit.servlet07;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.vo.StudentVO;

@WebServlet("/07/formProcess")
public class FormProcessServlet extends HttpServlet {
	private void case1GetParameter(HttpServletRequest req) {
		// 낱개의 파라미터 수신
//		name, age, address, birthday, license, career, hobbies, grade
		String name = req.getParameter("name");
		
		Integer age = Optional.ofNullable(req.getParameter("age"))
						.filter(s->s.matches("[0-9]{1,3}"))
						.map(Integer::parseInt)
						.orElse(null);
		String address = req.getParameter("address");
		String birthday = req.getParameter("birthday");
		String career = req.getParameter("career");
		String grade = req.getParameter("grade");
		String[] license = req.getParameterValues("license");
		String[] hobbies = req.getParameterValues("hobbies");
		
		System.out.printf("파라미터 명 : %s, 파라미터 값 : %s \n","name", name);
		System.out.printf("파라미터 명 : %s, 파라미터 값 : %s \n","hobbies", Arrays.toString(hobbies));
		
	}
	
	private void case2GetParameter(HttpServletRequest req) {
		// 파라미터의 이름으로 모든 파라미터를 수신
		Enumeration<String> names = req.getParameterNames();
		
		while(names.hasMoreElements()) {
			String name = (String)names.nextElement();
			String[] value = req.getParameterValues(name);
			
			System.out.printf("파라미터 명 : %s, 파라미터 값 : %s \n",name, Arrays.toString(value));
		}
	}
	private void case3GetParameter(HttpServletRequest req) {
		//parameter Map 활용
		req.getParameterMap().forEach((k,v)->System.out.printf("%s : %s\n",k, Arrays.toString(v)));
	}
	
	// 다른 거에 비해서 느림 1~2초의 시간이 필요하면 이걸 하면 안됨!
		private StudentVO case4GetParameterToCommandObject(HttpServletRequest req) {
			StudentVO commandObject = new StudentVO();
			// reflection은 고도의 작업임. 누군가가 reflection을 대신해준다면? 그래서 라이브러리 추가할 것임
			try {
				// reflection 시간관계상 못하고 넘어갔음
				// reflection에 대한 보강!하실 것임!..
				BeanUtils.populate(commandObject, req.getParameterMap());
				return commandObject;
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
				//return null; // 절대로 하면 안되는 코드임! 이게 정상코드인지 오류가 난것인지 구분을 못함
			}
			// VO에 설정을 제대로 했으면 이걸 쓸 수 있음! 맵안의 데이터를 빈안에 담아주는 데이터임!
			
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		case1GetParameter(req);
//		case2GetParameter(req);
//		case3GetParameter(req);
		StudentVO commandObject = case4GetParameterToCommandObject(req);
		System.out.println(commandObject);
	}

	


	private void caseMeGetParameter(HttpServletRequest req) {
		
		Map<String, String[]> params = req.getParameterMap();
		
		for(Entry<String,String[]> entry : params.entrySet() ) {
			for(String value : entry.getValue()) {
				System.out.printf("파라미터 명 : %s, 파라미터 값 : %s \n",entry.getKey(), value);
			}
		}
		
	}
}
