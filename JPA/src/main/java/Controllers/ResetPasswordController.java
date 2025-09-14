package Controllers;

import java.io.IOException;

import Service.TokenService;
import Service.UserService;
import Service.impl.TokenServiceImpl;
import Service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = -3905182006783167129L;

    private UserService userService = new UserServiceImpl();
    private TokenService tokenService = new TokenServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");

        if (token == null || !tokenService.isValid(token)) {
            req.setAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
            req.setAttribute("token", ""); // token rỗng → disable form submit
        } else {
            req.setAttribute("token", token); // token hợp lệ
        }

        req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String newPassword = req.getParameter("password");

        if (token != null && tokenService.isValid(token)) {
            int userId = tokenService.getUserIdByToken(token);
            userService.updatePassword(userId, newPassword);
            tokenService.deleteToken(token);

            resp.sendRedirect(req.getContextPath() + "/login?msg=reset_success");
        } else {
            req.setAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
            req.setAttribute("token", ""); // disable form submit
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
        }
    }
}
