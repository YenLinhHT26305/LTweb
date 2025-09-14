package Filter;

import java.io.IOException;

import Entity.Users;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns ="/user/*")
public class UserFilter implements Filter {
	public void destroy() {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	        throws IOException, ServletException {
	    
	    HttpServletRequest req = (HttpServletRequest) request;   // Ép về HTTP request
	    HttpServletResponse res = (HttpServletResponse) response; // Ép về HTTP response

	    HttpSession session = req.getSession();
	    Object obj = session.getAttribute("account");
	    Users user = (Users) obj;

	    if (user != null && user.getRoleId() == 1) {
	        chain.doFilter(request, response); // dùng lại request, response gốc
	    } else {
	        res.sendRedirect(req.getContextPath() + "/logout");
	    }
	}
	
	public void init(FilterConfig filterConfig) throws ServletException{
		
	}

}
