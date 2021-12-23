package com.vansisto.dao.impl;

import com.vansisto.dao.UserDao;
import com.vansisto.exception.UserNotFoundException;
import com.vansisto.model.User;
import com.vansisto.util.MySQLConnector;
import com.vansisto.util.Querries;
import com.vansisto.util.Role;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        User user = null;

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Querries.GET_USER_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                user = User.builder()
                            .id(resultSet.getInt("id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .role(Role.valueOf(resultSet.getString("role")))
                        .build();

        } catch (SQLException e) {
            log.error(e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (
                Connection connection = MySQLConnector.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(Querries.GET_ALL_USERS);
                ){

            while (resultSet.next()){
                users.add(User.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .role(Role.valueOf(resultSet.getString("role")))
                .build());
            }


        } catch (SQLException e) {
            log.error(e);
        }

        return users;
    }

    @Override
    public void deleteById(int id) {
        getById(id);

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Querries.DELETE_USER_BY_ID)
                ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void update(User user) {
        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Querries.UPDATE_USER_BY_ID)
                ) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole().name());
            preparedStatement.setInt(5, user.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            log.error(e);
        }
    }

    @SneakyThrows
    @Override
    public User getByEmail(String email) {
        User user = null;

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Querries.GET_USER_BY_EMAIL);
                ) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) user = User.builder()
                    .id(resultSet.getInt("id"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .password(resultSet.getString("password"))
                    .role(Role.valueOf(resultSet.getString("role")))
            .build();

        } catch (SQLException e) {
            log.error(e);
        }
        if (Objects.isNull(user)) throw new UserNotFoundException(email);
        else return user;
    }
}
