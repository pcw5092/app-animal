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
import data.sido.SidoResponse;
import util.AnimalAPI;
import util.SidoAPI;

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
		SidoResponse sidoResponse = SidoAPI.getSidos();

		if (sidoResponse != null) {
			req.setAttribute("sidos", sidoResponse.getBody().getItems().getItem());
		}

		if (animalResponse != null) {
			req.setAttribute("datas", animalResponse.getBody().getItems().getItem());
			req.setAttribute("total", animalResponse.getBody().getTotalCount());

			int p;
			if (req.getParameter("pageNo") == null) {
				p = 1;
			} else {
				p = Integer.parseInt(req.getParameter("pageNo"));
			}

			int total = animalResponse.getBody().getTotalCount();
			int lastPage = total / 12 + (total % 12 > 0 ? 1 : 0);

			int start = p % 5 == 0 ? p - 4 : p - (p % 5) + 1;
			int last = p % 5 == 0 ? p : p - (p % 5) + 5;

			last = last > lastPage ? lastPage : last;

			req.setAttribute("start", start);
			req.setAttribute("last", last);

			boolean existPrev = start == 1 ? false : true;
			boolean existNext = last < lastPage - 1 ? true : false;

			req.setAttribute("existPrev", existPrev);
			req.setAttribute("existNext", existNext);
		}

		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
	}
}
