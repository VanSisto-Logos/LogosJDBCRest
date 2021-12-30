package com.vansisto.servlet;

import com.vansisto.model.User;
import com.vansisto.service.UserService;
import com.vansisto.service.impl.UserServiceImpl;
import com.vansisto.util.RestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map parameters = req.getParameterMap();
        if (parameters.size() == 0){
            resp.getWriter().write(RestUtil.getJsonFromObject(service.getAll()));
        } else {

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameters = req.getParameterMap();
        if (parameters.size() == 0) resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        else {
            int id = Integer.valueOf(req.getParameter("id"));
            service.deleteById(id);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = RestUtil.getFromJson(req, User.class);
        service.update(user);
    }
}
