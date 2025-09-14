package Controllers;

import java.io.IOException;
import java.util.List;

import Entity.Category;
import Service.CategoryService;
import Service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7163496964471429596L;
	public CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("insert")) {
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			String images = "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/o/p/oppo-reno14-f-w_1.jpg";
			Category category = new Category();
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(statuss);

			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

		} else if (url.contains("update")) {
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			String images = "https://www.duchuymobile.com/images/companies/1/1-tin-moi/2025/10-9/2/mau-sac-iphone-17-pro-max.jpg";
			// upload file chưa viết

			Category category = new Category();
			category.setCategoryId(categoryid);
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(statuss);

			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			
		} else if (url.contains("delete")) {
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			try {
				cateService.delete(categoryid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // gọi service để xóa trong DB
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
