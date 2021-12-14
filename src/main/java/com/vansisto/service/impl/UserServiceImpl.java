package com.vansisto.service.impl;

import com.vansisto.dao.Dao;
import com.vansisto.dao.impl.UserDaoImpl;
import com.vansisto.model.User;
import com.vansisto.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private Dao dao = new UserDaoImpl();

    @Override
    public void create(User user) {
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
}
