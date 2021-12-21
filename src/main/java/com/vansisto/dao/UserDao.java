package com.vansisto.dao;

import com.vansisto.model.User;

public interface UserDao extends Dao<User> {
    User getByEmail(String email);
}
