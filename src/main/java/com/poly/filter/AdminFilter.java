package com.poly.filter;

import java.io.IOException;

import com.poly.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");


        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }


        if (user.getIsAdmin() == null || !user.getIsAdmin()) {
            resp.sendRedirect(req.getContextPath() + "/index");
            return;
        }

        chain.doFilter(request, response);
    }
}
