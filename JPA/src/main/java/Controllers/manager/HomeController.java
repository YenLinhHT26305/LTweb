package Controllers.manager;

import java.io.IOException;
import java.util.List;

import Entity.Category;
import Entity.Users;
import Service.CategoryService;
import Service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/manager/home")
public class HomeController extends HttpServlet{
	public CategoryService cateService = new CategoryServiceImpl();
	private static final long serialVersionUID = 437885477888051582L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 HttpSession session = req.getSession();
	        Users currentUser = (Users) session.getAttribute("account");

	        if (currentUser == null) {
	            resp.sendRedirect(req.getContextPath() + "/login");
	            return;
	        }

	        // Lấy category của riêng user này
	        List<Category> list = cateService.findByUserId(currentUser.getId());
	        req.setAttribute("listCategory", list);

		req.getRequestDispatcher("/views/manager/home.jsp").forward(req, resp);
	}
}
