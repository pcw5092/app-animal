package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.animal.AnimalItem;
import util.AnimalAPI;

@WebServlet("/detail")
public class DetailController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no =req.getParameter("no");
		
		AnimalItem item =AnimalAPI.findByDesertionNo(no);
		
		
		req.setAttribute("item", item);
		
		req.getRequestDispatcher("/WEB-INF/views/detai.jsp").forward(req, resp);
	}
}





