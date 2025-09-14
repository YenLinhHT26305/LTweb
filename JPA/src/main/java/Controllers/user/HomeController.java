package Controllers.user;

import java.io.IOException;
import java.util.List;

import Entity.Category;
import Service.CategoryService;
import Service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/user/home")

public class HomeController extends HttpServlet{

	private static final long serialVersionUID = 437885477888051582L;
	public CategoryService cateService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> list = cateService.findAll();
		req.setAttribute("listcate", list);
		req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
		super.doGet(req, resp);
	}
}
