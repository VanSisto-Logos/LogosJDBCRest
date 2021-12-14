package com.vansisto.dao.impl;

import com.vansisto.dao.UserDao;
import com.vansisto.model.User;
import com.vansisto.util.MySQLConnector;
import com.vansisto.util.Querries;
import com.vansisto.util.Role;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Log4j
public class UserDaoImpl implements UserDao {
    @Override
    public void create(User user) {
        boolean withId = user.getId() != 0;
        final String SQL = withId ? Querries.CREATE_USER : Querries.CREATE_USER_WITHOUT_ID;

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getRole().name());
            if (withId) statement.setInt(6, user.getId());

            statement.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

//    public static void main(String[] args) {
//        UserDaoImpl dao = new UserDaoImpl();
//        dao.create(User.builder()
//                .id(5)
//                .email("some5@mail.mail")
//                .password("password")
//                .firstName("First name")
//                .lastName("Last name")
//                .role(Role.ADMIN)
//                .build()
//        );
//    }

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
