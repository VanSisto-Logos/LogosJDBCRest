package com.vansisto.dao.impl;

import com.vansisto.dao.BucketProductDao;
import com.vansisto.model.BucketProduct;
import com.vansisto.util.MySQLConnector;
import com.vansisto.util.Querries;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Log4j
public class BucketProductDaoImpl implements BucketProductDao {
    @Override
    public void create(BucketProduct bucketProduct) {
        boolean withId = bucketProduct.getId() != 0;
        final String SQL = withId ? Querries.CREATE_BUCKET_PRODUCT : Querries.CREATE_BUCKET_PRODUCT_WITHOUT_ID;

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {

            statement.setInt(1, bucketProduct.getBucketId());
            statement.setInt(2, bucketProduct.getProductId());
            if (Objects.isNull(bucketProduct.getNumber())) statement.setInt(3, 1);
            else statement.setInt(3, bucketProduct.getNumber());
            if (withId) statement.setInt(4, bucketProduct.getId());

            statement.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public BucketProduct getById(int id) {
        return null;
    }

    @Override
    public List<BucketProduct> getAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(BucketProduct bucketProduct) {

    }
}
