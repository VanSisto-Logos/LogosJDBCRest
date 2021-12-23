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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = RestUtil.getFromJson(req, User.class);
        User userFromDB = service.getByEmailAndPassword(user.getEmail(), user.getPassword());
        User userInSession = User.builder()
                        .id(userFromDB.getId())
                        .firstName(userFromDB.getFirstName())
                        .lastName(userFromDB.getLastName())
                        .email(userFromDB.getEmail())
                        .role(userFromDB.getRole())
                .build();

        HttpSession session = req.getSession();
        session.setAttribute("user", userInSession);
    }
}
