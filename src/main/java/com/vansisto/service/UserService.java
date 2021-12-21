package com.vansisto.service;

import com.vansisto.model.User;

public interface UserService extends Service<User> {
    User getByEmailAndPassword(String email, String password);
}
