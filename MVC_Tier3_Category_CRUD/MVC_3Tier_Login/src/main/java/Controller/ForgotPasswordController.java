package Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import Model.User;
import Service.TokenService;
import Service.UserService;
import Service.impl.TokenServiceImpl;
import Service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3247562211432328509L;
	private UserService userService = new UserServiceImpl();
    private TokenService tokenService = new TokenServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = userService.findByEmail(email);

        if (user != null) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiry = LocalDateTime.now().plusMinutes(15);

            tokenService.saveToken(user.getId(), token, expiry);

            // Giả lập gửi mail (thực tế dùng JavaMail)
            System.out.println("Reset link: http://localhost:8080/MVC_3Tier_Login/reset-password?token=" + token);

            req.setAttribute("msg", "Link reset mật khẩu đã được gửi tới email của bạn.");
        } else {
            req.setAttribute("error", "Email không tồn tại!");
        }

        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }
}

