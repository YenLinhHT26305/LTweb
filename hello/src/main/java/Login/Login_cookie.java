package Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login-cookie")
public class Login_cookie extends HttpServlet{
	private static final long serialVersionUID = -7042221523405824860L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		String user = req.getParameter("username");
		String pass = req.getParameter("password");

		if("linh".equals(user) && "123".equals(pass)) {
			Cookie cookie = new Cookie("username", user); 
			cookie.setMaxAge(30); 
			resp.addCookie(cookie);

			resp.sendRedirect(req.getContextPath() + "/hello");
		} else {
			resp.sendRedirect(req.getContextPath() + "/login.html");
		}
	}
}

