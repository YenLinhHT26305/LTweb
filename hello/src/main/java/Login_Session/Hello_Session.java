package Login_Session;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/hello-session")
public class Hello_Session extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2473958383423508854L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("name") != null) {
            String name = (String) session.getAttribute("name");
            out.print("Xin chào " + name + "<br/>");
            out.print("<a href='logout'>Đăng xuất</a>");
        } else {
            out.print("Bạn chưa đăng nhập! <a href='loginsession.html'>Login</a>");
        }

    }
}