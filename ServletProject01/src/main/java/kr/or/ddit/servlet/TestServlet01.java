package kr.or.ddit.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.vo.MemberVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/*
 * 클라이언트(브라우저)에서 요청한 URL을 여러 서블릿들 중, 브라우저에 입력한 URL과 동일한 패턴을 가지고 있는 서블릿이
 * 요청을 처리하게 됩니다. 우리가 요청한 URL은 'http://localhost/TestServlet01'로 시작하면서 '/text', '/html' 등등으로 끝나는 URL입니다.
 * 아래 @WebServlet 어노테이션에 명시한 '/TestServlet01/*' 는 'http://localhost/TestServlet01'로 시작하는 모든 URL을 처리할 수 있는 서블릿이라는 의미입니다.
 * 즉, '/TestServlet01'로 시작하는 모든 URL에 대해 이 서블릿이 요청을 처리하게 됩니다.
 * 
 * 서블릿은 자바로 작성된 서버측 프로그램으로, 클라이언트의 요청을 처리하고 응답을 생성하는 역할을 합니다.
 * 서블릿은 HTTP 프로토콜을 기반으로 동작하며, 클라이언트의 요청을 처리하기 위해 HttpServletRequest 객체를 사용하고,
 * 응답을 생성하기 위해 HttpServletResponse 객체를 사용합니다.
 * 
 * 요청을 받은 서블릿에서 request를 사용하고 싶을 땐, 아래 매개변수 자리에서처럼 HttpServletRequest request를 사용하고,
 * 요청을 받은 서블릿에서 response를 사용하고 싶을 땐, 아래 매개변수 자리에서처럼 HttpServletResponse response를 사용합니다.
 */
@WebServlet("/TestServlet01/*")
public class TestServlet01 extends HttpServlet {

	/*
	 * doGet(), doPost() 메소드 각각은 요청에 Method 방식에 따라 호출되는 메소드입니다.
	 * 요청 방식이 GET 방식일 경우 doGet() 메소드가 호출되고, 요청 방식이 POST 방식일 경우 doPost() 메소드가 호출됩니다.
	 * 
	 * # GET 방식의 요청
	 * GET 방식의 요청은 브라우저 주소창에 URL을 입력하거나, a태그로 묶인 링크를 클릭할 때 발생합니다.
	 * 또 Form 태그에서 method 속성을 GET으로 설정하고 submit 버튼을 클릭할 때도 발생합니다.
	 * 
	 * # POST 방식의 요청
	 * POST 방식의 요청은 Form 태그에서 method 속성을 POST로 설정하고 submit 버튼을 클릭할 때 발생합니다.
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 자, 지금부터 클라이언트로부터 요청을 받아서 해당 서블릿의 GET방식의 요청이면 doGet() 메소드가 호출되고, POST방식의 요청이면 doPost() 메소드가 호출됩니다.
		// 요청을 받았으니 이제 요청을 보내준 곳으로 응답을 보내줘야겠죠?
		// 응답을 보내는건 내 자유기도 하지만, 요청을 보낼 때의 요구사항대로 보내주는게 좋겠죠?
		// 아래 각 타입의 응답으로 데이터를 만들어서 전송해봅시다!
		
		// 응답이니까 response 객체를 사용해야겠죠?
		// 응답을 보내기 위해서는 response 객체에 들어있는 데이터를 전송하기 위해 사용하는 메소드, Writer를 사용해야 합니다.
		// response.getWriter() 메소드를 사용하면, 응답을 보내기 위한 Writer 객체를 얻을 수 있습니다.
		PrintWriter writer = response.getWriter();		// 응답으로 데이터 전송해 줄 녀석 소환!
		// 그럼 녀석이 가지고 있는 기술 중에 브라우저로 보내고 싶은 데이터 보낼 때 사용할 수 있는 print(), println() 메소드를 사용할 수 있습니다!
		// 이 메소드를 사용하면, 클라이언트에게 응답을 보낼 수 있습니다.
		// writer.print("Hello, World!");	// 클라이언트에게 Hello, World!라는 문자열을 응답으로 보냅니다.
		
		String URI = request.getRequestURI();
		URI = URI.split("/")[3];	// URI에서 '/ServletProject01/TestServlet01/' 부분을 잘라내고, '/text' 부분만 남깁니다.
		System.out.println(URI);
		if(URI.equals("text")) {
			// -------- 1. 문자열 응답으로 전송하기 --------
			writer.print("Hello, World!");	// 클라이언트에게 Hello, World!라는 문자열을 응답으로 보냅니다.
			writer.println("나는 학범이라고해! 난 서블릿을 공부중이야!");
			writer.println("서블릿 알고보니까 별거 없더라고?");
			writer.append("내가 바뻐서 그렇지 한다면 끝을 보는 나라고!");
			// 여기까지 일단 확인!
			
			// 이렇게 보냈더니 한글이 깨져서 나오는군요? 이건 왜그러냐면요!
			// 응답으로 데이터를 보낼 때, 한글은 UTF-8로 인코딩해서 보내야 하는데, 기본적으로는 ISO-8859-1로 인코딩해서 보내기 때문입니다.
			// 그래서 한글이 깨져서 나오는거죠? 그럼 어떻게 해야할까요? 한글이 깨지지 않는 설정이 필요하겠죠?
			// 그럴때도 마찬가지로 응답을 담당하고 있는 response 객체를 사용해서 설정해주면 됩니다.
			// 응답에 데이터를 보낼 때, 한글이 깨지지 않도록 ContentType을 설정해주면 됩니다.
			// ContentType은 응답으로 보내는 데이터의 타입을 설정하는 것입니다.
			// 우리가 응답으로 보내고 있는 데이터는 문자열이므로, 이때 문자열의 ContentType은 'text/plain'입니다.
			// 그리고 한글이 깨지지 않도록 인코딩을 UTF-8로 설정해주면 됩니다.
			response.setContentType("teCoxt/plain;charset=UTF-8");
			// 여기까지 일단 확인!
		}else if(URI.equals("html")) {
			// -------- 2. html 코드 응답으로 전송하기 --------
			// html 코드를 응답으로 보낸다는건 여러분들이 알고 있는 html 태그를 이용한 간단한 웹페이지를 만들어서 응답으로 보내는 것입니다.
			// html 코드를 이용해서 화면을 만들 때, <p>화면이다!</p>와 같은 태그로만 구성해서 보내지 않잖아요?
			// 그러니까 기본적인 html 태그를 사용해서 화면을 구성한 문자열을 만들어야겠죠?
			// 그런데 응답으로 보내는 데이터가 html 코드이니까 데이터 타입에 해당하는 ContentType을 뭘로 설정해줘야해요?
			// 그렇죠! html 코드니까 'text/html'로 설정해주면 아주 속이 편안해지는거죠! 그럼 해볼까요?
			// 우선 간단한 html 코드를 만들자구요!
			writer.print("<html>");
			writer.print("	<head>");
			writer.print("		<title>TestServlet01</title>");
			writer.print("	</head>");
			writer.print("	<body>");
			writer.print("		<h1>TestServlet01</h1>");
			writer.print("		<p>서블릿을 이용한 응답</p>");
			writer.print("	</body>");
			writer.print("</html>");
			// 여기까지 일단 확인!
			
			// 역시나 한글이 포함되어 있다보니 한글이 깨지네요? 왜 깨진다구요? 브라우저가 응답으로 받은 데이터를 해석할 때, 
			// 인코딩을 UTF-8로 해석하지 않고 ISO-8859-1로 해석하기 때문입니다. 그래서 한글을 깨지지 않고 해석할 수 있도록 UTF-8로 인코딩을 해줘야 합니다. 
			// html 코드를 응답으로 보내는거니까 ContentType을 'text/html'로 설정해주고, 한글이 포함되어 있으니까 인코딩을 UTF-8로 설정해주면 됩니다.
			response.setContentType("text/html;charset=UTF-8");
			// 여기까지 일단 확인!
		}else if(URI.equals("collection")) {
			// -------- 3. Collection 응답으로 전송하기 --------
			// Collection 종류는 대표적으로 List, Set, Map이 있죠? 그 중에 List, Map을 사용해서 응답으로 보내봅시다!
			// List는 ArrayList를 사용하고, Map은 HashMap을 사용해서 응답으로 보내봅시다.
			List<String> list = new ArrayList<>();		// Collection List 생성
			list.add("난! ");
			list.add("서블릿을 ");
			list.add("공부중이야!");
			
			Map<String, String> map = new HashMap<>();	// Collection Map 생성
			map.put("class", "304호");
			map.put("name", "학범");
			map.put("gender", "남자");
			
			// 데이터를 만들었으니까 응답으로 보내봐야겠죠?
//			writer.print(list);	// List를 응답으로 보내기
			writer.print(map);	// Map을 응답으로 보내기
			// 여기까지 일단 확인!
			
			// 응답으로 List를 보내면, List의 toString() 메소드가 호출되어서 List의 내용을 문자열로 변환해서 응답으로 보내게 됩니다.
			response.setContentType("application/json;charset=UTF-8");	// 
			// 여기까지 일단 확인!
			// 응답으로 List 데이터를 보내고 ContentType을 'application/json'으로 설정해주면, 브라우저에서 응답으로 리스트를 짜잔하고 볼 줄 알았는데
			// 객체의 형태로 나타나지 않고 그냥 문자열로 나타나네요? 그건 왜 그러냐면요?
			// 응답으로 보내는 데이터가 JSON 형식이 아니기 때문입니다. JSON 형식으로 응답을 보내려면, List를 JSON 형식으로 변환해줘야 합니다.
			// List를 JSON 형식으로 변환해주는 라이브러리를 사용해야 합니다. 대표적으로 Gson, Jackson, org.json 등이 있습니다.
			// 그래서 Collection 데이터를 보낼 때에는 위 라이브러리를 이용해서 JSON 형식의 데이터를 만들어줘야 한다는게 학범이의 정설이죠!
		}else if(URI.equals("vo")) {
			// -------- 4. 객체 응답으로 전송하기 --------
			// 객체를 응답으로 보내기 위해서 MemberVO 클래스를 만들어봅시다!
			// 원하는 데이터로 MemberVO 객체를 만들어서 보내보죠!
			MemberVO member = new MemberVO();	// 객체 생성
			member.setMemNo(1);
			member.setMemId("admin");
			member.setMemPass("1234");
			member.setMemName("학범잉");
			// 데이터를 다 넣었으니까 이제 MemberVO 객체를 응답으로 보내볼까요?
			writer.print(member);	// 객체를 응답으로 보내기
			// 여기까지 일단 확인!
			response.setContentType("application/json;charset=UTF-8");
			// 사실상 객체를 json 타입으로 보냈다곤 하지만, 실제로는 toString() 문자열이 날라간거지!
			// 그래서 json 데이터를 직접 만들고 보내줘야한다는거야!
			// 여기까지 일단 확인!
		}else if(URI.equals("json")) {
			// -------- 5. JSON 데이터 전송하기 --------
			JsonObject json = new JsonObject();	// JSON 데이터 생성
			json.addProperty("status", 200);	// JSON 데이터에 속성 추가
			json.addProperty("message", "success");	// JSON 데이터에 속성 추가
			
			Map<String, Object> jsonMap = new HashMap<>();	// JSON 데이터로 변환할 Map 생성
			jsonMap.put("memNo", 1);
			jsonMap.put("memId", "admin");
			jsonMap.put("memPass", "1234");
			jsonMap.put("memName", "학범잉");
			
			JsonObject jsonData = new JsonObject();	// JSON 데이터 생성
			for(String key : jsonMap.keySet()) {
				jsonData.addProperty(key, jsonMap.get(key).toString());	// JSON 데이터에 속성 추가
			}
			json.add("data", jsonData);	// JSON 데이터에 속성 추가		add -> 포장해서 보내는
			
			// 데이터를 다 넣었으니까 이제 JSON 데이터를 응답으로 보내볼까요?
			writer.print(json);	// JSON 데이터를 응답으로 보내기
			// 여기까지 일단 확인!
			
			response.setContentType("application/json;charset=UTF-8");
			// 여기까지 일단 확인!
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
