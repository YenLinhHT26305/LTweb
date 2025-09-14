package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

@WebServlet(urlPatterns = { "/image" })
public class DownloadFileController extends HttpServlet {

	private static final long serialVersionUID = 314712717450794316L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fname");
		File file = new File(Constant.DIR + File.separator + fileName);

		if (file.exists()) {
			// Lấy MIME type theo tên file (.jpg, .png, .gif, ...)
			String mimeType = getServletContext().getMimeType(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream"; // fallback
			}
			resp.setContentType(mimeType);

			// Gửi file ra output stream
			try (FileInputStream fis = new FileInputStream(file)) {
				IOUtils.copy(fis, resp.getOutputStream());
			}
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found: " + fileName);
		}
	}
}
