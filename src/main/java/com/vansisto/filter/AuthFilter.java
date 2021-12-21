package com.vansisto.filter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vansisto.util.Role;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/auth")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        JsonObject json = new JsonObject();
        Role role = (Role) request.getSession().getAttribute("role");
        json.addProperty("role", role.name());
        if (Objects.isNull(role)) response.setStatus(401);
        else {
            response.getWriter().write(json.toString());
        }
    }

    @Override
    public void destroy() {

    }
}
