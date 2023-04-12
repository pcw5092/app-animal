package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.QueryStringBuilder;

@WebServlet("/test")
public class TestController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String upr_cd = "6210000";
		String upkind = "244000";
		String pageNo = "1";
		String bgnde = "20230410";
		String endde = null;
//		if(endde == null) {
//			endde = "";
//		}

		// 쉽게 만들기 위해 Map 을 활용할 수 있다.
		
		Map<String, String> parmas = new HashMap<>();
		parmas.put("upr_cd", upr_cd);
		parmas.put("upkind", upkind);
		parmas.put("pageNo", pageNo);
		parmas.put("bgnde", bgnde);
		

		String query = QueryStringBuilder.build(parmas);
		System.out.println(query);
	}
}
