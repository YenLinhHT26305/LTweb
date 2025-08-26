package Login_Session;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login-session") 
public class Login_Session extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("linh".equals(username) && "123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("name", username);

            response.sendRedirect(request.getContextPath() + "/hello-session");
        } else {
            out.print("Tài khoản hoặc mật khẩu không chính xác<br/>");
            request.getRequestDispatcher("loginsession.html").include(request, response);
        }

    }
}
