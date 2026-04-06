package com.poly.filter;

import java.io.IOException;

import com.poly.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter({ 
    "/favorite", 
    "/edit-profile", 
    "/change-password", 
    "/share" 
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // Chưa đăng nhập → chặn + nhớ trang cũ
        if (user == null) {

            // Lưu trang người dùng định vào
            String uri = req.getRequestURI() + 
                         (req.getQueryString() != null ? "?" + req.getQueryString() : "");

            session.setAttribute("security-uri", uri);

            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Đã login → cho đi tiếp
        chain.doFilter(request, response);
    }
}
