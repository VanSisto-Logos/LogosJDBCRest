package com.vansisto.dao.impl;

import com.vansisto.dao.BucketDao;
import com.vansisto.exception.BucketNotFoundException;
import com.vansisto.exception.UserNotFoundException;
import com.vansisto.model.Bucket;
import com.vansisto.util.MySQLConnector;
import com.vansisto.util.Querries;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Log4j
public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(Querries.CREATE_BUCKET)
                ) {

            statement.setTimestamp(1, Timestamp.valueOf(bucket.getCreatedDate()));
            statement.setInt(2, bucket.getId());

            statement.execute();
        } catch (SQLException e) {
            log.error(e);
        }

//        TODO:
        return null;
    }

    @Override
    public Bucket getById(int id) {
        return null;
    }

    @Override
    public List<Bucket> getAll() {
        return null;
    }

    @SneakyThrows
    @Override
    public void deleteById(int id) {
        if (!isExist(id)) throw new BucketNotFoundException(id);

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Querries.DELETE_BUCKET_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void update(Bucket bucket) {

    }

    @Override
    public boolean isExist(int id) {
        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bucket WHERE id = ?");
        ) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e ) {
            log.error(e);
        }
        return false;
    }
}
