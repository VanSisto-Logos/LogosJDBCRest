package com.vansisto.dao.impl;

import com.vansisto.dao.BucketDao;
import com.vansisto.model.Bucket;
import com.vansisto.util.MySQLConnector;
import com.vansisto.util.Querries;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Log4j
public class BucketDaoImpl implements BucketDao {
    @Override
    public void create(Bucket bucket) {
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
    }

    @Override
    public Bucket getById(int id) {
        return null;
    }

    @Override
    public List<Bucket> getAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(Bucket bucket) {

    }
}
