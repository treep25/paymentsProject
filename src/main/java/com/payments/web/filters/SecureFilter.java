package com.payments.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SecureFilter")
public class SecureFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String loginURL = req.getContextPath() + "/logIn.jsp";
        String registrURL = req.getContextPath() + "/signUp.jsp";

        boolean loggedIn = session != null && session.getAttribute("customer") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURL) ;
        boolean registrRequest = req.getRequestURI().equals(registrURL) ;

        if(loggedIn || loginRequest || registrRequest) {
            chain.doFilter(req, res);
        }
        else {
            res.sendRedirect("http://localhost:8080/paymentsProject_war/signUp.jsp");
        }
    }
}
