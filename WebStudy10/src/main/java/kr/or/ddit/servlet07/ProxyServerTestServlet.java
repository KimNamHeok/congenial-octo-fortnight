package kr.or.ddit.servlet07;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 *	SOP 제한이 걸린 클라이언트를 대신해서, 컨텐츠 제공 서버쪽으로 요청을 보내고, 
 *	응답을 수신한 후 클라이언트에게 최종 응답을 중개할 컨트롤러
 *	line, header로 구성된 get 요청을 MDN서버로 전송
 */

@WebServlet("/07/proxyServer")
public class ProxyServerTestServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "https://developer.mozilla.org/ko/docs/Web/HTTP/Reference/Headers";
		// 이러면 브라우저를 하나 가지고 있는것임!
		HttpClient client = HttpClient.newHttpClient();
		// request를 만드는 것! uri 설정
		HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create(url))
								.GET()
								.header("Accept", "text/html")
								.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if(response.statusCode()==200) {
				String html = response.body();
				Document document = Jsoup.parse(html);
				
				Element element = document.selectFirst("details summary a[href$='Headers']");
				
				List<String> headerNames = Optional.ofNullable(element)
						.map(el->el.closest("details"))
						.map(p->p.select("li code"))
						.map(es->es.eachText())
						.orElse(List.of());
				System.out.println(headerNames);

				String json = new Gson().toJson(headerNames);
				resp.setContentType("application/json");
				resp.getWriter().print(json);
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
