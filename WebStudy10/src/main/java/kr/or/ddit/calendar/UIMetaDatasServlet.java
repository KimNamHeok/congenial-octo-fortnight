package kr.or.ddit.calendar;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calendar/uiMetaDatas")
public class UIMetaDatasServlet extends HttpServlet{
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("application/json");
      UIMetaDatas metaDatas = new UIMetaDatas();
      String json = new Gson().toJson(metaDatas);
      try (PrintWriter out = resp.getWriter()){
         out.print(json);
      }
   }
}
