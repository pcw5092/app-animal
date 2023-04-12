package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.animal.AnimalResponse;
import util.AnimalAPI;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 축종코드 : 개=> "417000", 고양이 => "422400" , 기타 => "429900"
		String upkind = req.getParameter("upkind");
		String upr_cd = req.getParameter("upr_cd");
		String pageNo = req.getParameter("pageNo");
		String bgnde = req.getParameter("bgnde");
		if (bgnde != null && bgnde.matches("\\d{4}-\\d{2}-\\d{2}")) {
			bgnde = bgnde.substring(0, 4) + bgnde.substring(5, 7) + bgnde.substring(8);
		}
		String endde = req.getParameter("endde");
		if (endde != null && endde.matches("\\d{4}-\\d{2}-\\d{2}")) {
			endde = endde.substring(0, 4) + endde.substring(5, 7) + endde.substring(8);
		}
		AnimalResponse animalResponse = AnimalAPI.getAnimals(upkind, upr_cd, pageNo, bgnde, endde);

		if (animalResponse != null) {
			req.setAttribute("datas", animalResponse.getBody().getItems().getItem());
			req.setAttribute("total", animalResponse.getBody().getTotalCount());
			int total = animalResponse.getBody().getTotalCount();

			req.setAttribute("lastPageNo", total / 12 + (total % 12 > 0 ? 1 : 0));
			
			int lastPage = total / 12 + (total % 12 > 0 ? 1 : 0);
			
			int p;
			if (req.getParameter("pageNo") == null) {
				p = 1;
			} else {
				p = Integer.parseInt(req.getParameter("pageNo"));
			}

			Map<String, Object> map = new HashMap<>();
			map.put("a", p * 10 - 9);
			map.put("b", p * 10);

			int idx = p * 10;

			int start = p % 5 == 0 ? p - 4 : p - (p % 5) + 1;
			int last = p % 5 == 0 ? p : p - (p % 5) + 5;

			last = last > lastPage ? lastPage : last;

			req.setAttribute("idx", idx);
			req.setAttribute("start", start);
			req.setAttribute("last", last);

			boolean existPrev = p >= 6;
			boolean existNext = lastPage > last;

			req.setAttribute("existPrev", existPrev);
			req.setAttribute("existNext", existNext);
		}




		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
	}
}
