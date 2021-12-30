package com.vansisto.service.impl;

import com.vansisto.dao.BucketDao;
import com.vansisto.dao.UserDao;
import com.vansisto.dao.impl.BucketDaoImpl;
import com.vansisto.dao.impl.UserDaoImpl;
import com.vansisto.exception.PasswordsDontMatchException;
import com.vansisto.model.Bucket;
import com.vansisto.model.User;
import com.vansisto.service.UserService;
import com.vansisto.util.Role;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    private BucketDao bucketDao = new BucketDaoImpl();

    @Override
    public User create(User user) {
        Role receivedRole = user.getRole();
        if (Objects.isNull(receivedRole)){
            user.setRole(Role.USER);
        }
        User createdUser = dao.create(user);
        bucketDao.create(new Bucket(createdUser.getId(), LocalDateTime.now()));

        return createdUser;
    }

    @Override
    public User getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(int id) {
        bucketDao.deleteById(id);
        dao.deleteById(id);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public boolean isExist(int id) {
        return dao.isExist(id);
    }

    @SneakyThrows
    @Override
    public User getByEmailAndPassword(String email, String password) {
        User user = dao.getByEmail(email);

        if (user.getPassword().equals(password)) return user;
        else throw new PasswordsDontMatchException();
    }
}
