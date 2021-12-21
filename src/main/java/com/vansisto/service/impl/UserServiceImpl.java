package com.vansisto.service.impl;

import com.vansisto.dao.UserDao;
import com.vansisto.dao.impl.UserDaoImpl;
import com.vansisto.exception.PasswordsDontMatchException;
import com.vansisto.exception.UserNotFoundException;
import com.vansisto.model.User;
import com.vansisto.service.UserService;
import com.vansisto.util.Role;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public void create(User user) {
        if (Objects.isNull(user.getRole())){
            user.setRole(Role.USER);
        }
        dao.create(user);
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(User user) {

    }

    @SneakyThrows
    @Override
    public User getByEmailAndPassword(String email, String password) {
        User user = dao.getByEmail(email);

        if (user.getPassword().equals(password)) return user;
        else throw new PasswordsDontMatchException();
    }
}
