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

@WebServlet(urlPatterns = { 
    "/manager/categories", 
    "/manager/category/add", 
    "/manager/category/insert",
    "/manager/category/edit", 
    "/manager/category/update", 
    "/manager/category/delete" })
public class ManagerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users currentUser = (Users) session.getAttribute("account");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String url = req.getRequestURI();

        if (url.contains("categories")) {
            // chỉ lấy category của currentUser
            List<Category> list = cateService.findByUserId(currentUser.getId());
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/manager/category-list.jsp").forward(req, resp);

        } else if (url.contains("add")) {
            req.getRequestDispatcher("/views/manager/category-add.jsp").forward(req, resp);

        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = cateService.findById(id);

            if (category == null || category.getUser().getId() != currentUser.getId()) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền sửa Category này");
                return;
            }

            req.setAttribute("cate", category);
            req.getRequestDispatcher("/views/manager/category-edit.jsp").forward(req, resp);

        } else if (url.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = cateService.findById(id);

            if (category == null || category.getUser().getId() != currentUser.getId()) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xóa Category này");
                return;
            }

            try {
				cateService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            resp.sendRedirect(req.getContextPath() + "/manager/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users currentUser = (Users) session.getAttribute("account");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String url = req.getRequestURI();

        if (url.contains("insert")) {
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            String images = req.getParameter("images");

            Category category = new Category();
            category.setCategoryname(categoryname);
            category.setStatus(status);
            category.setImages(images);
            category.setUser(currentUser); // gắn user hiện tại

            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/manager/categories");

        } else if (url.contains("update")) {
            int id = Integer.parseInt(req.getParameter("categoryid"));
            Category category = cateService.findById(id);

            if (category == null || category.getUser().getId() != currentUser.getId()) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền sửa Category này");
                return;
            }

            category.setCategoryname(req.getParameter("categoryname"));
            category.setStatus(Integer.parseInt(req.getParameter("status")));
            category.setImages(req.getParameter("images"));

            cateService.update(category);
            resp.sendRedirect(req.getContextPath() + "/manager/categories");
        }
    }
}
