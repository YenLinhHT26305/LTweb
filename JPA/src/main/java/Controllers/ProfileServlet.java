package Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import Service.UserService;
import Service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/profile")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int userId = Integer.parseInt(request.getParameter("userId"));
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");

        // Upload file avatar
        Part filePart = request.getPart("avatar");
        String fileName = null;

        if (filePart != null && filePart.getSize() > 0) {
            // Lấy tên file gốc bằng API chuẩn Servlet
            String originalFileName = Paths.get(filePart.getSubmittedFileName())
                                           .getFileName().toString();

            // Đặt tên file có timestamp để tránh trùng
            fileName = System.currentTimeMillis() + "_" + originalFileName;

            // Thư mục lưu upload
            String uploadPath = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // Ghi file ra server
            filePart.write(uploadPath + File.separator + fileName);
        }

        // Gọi service update
        userService.updateProfile(userId, fullname, phone, fileName);

        // Redirect về trang profile
        response.sendRedirect(request.getContextPath() + "/admin/home");
    }
}
