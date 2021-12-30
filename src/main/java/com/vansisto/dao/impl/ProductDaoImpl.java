package com.vansisto.dao.impl;

import com.vansisto.dao.ProductDao;
import com.vansisto.model.Product;
import com.vansisto.util.MySQLConnector;
import com.vansisto.util.Querries;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Log4j
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        boolean withId = product.getId() != 0;
        final String SQL = withId ? Querries.CREATE_PRODUCT : Querries.CREATE_PRODUCT_WITHOUT_ID;

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)
        ) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            if (withId) statement.setInt(4, product.getId());

            statement.execute();
        } catch (SQLException e) {
            log.error(e);
        }

//        TODO:
        return null;
    }

    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public boolean isExist(int id) {
        return false;
    }
}
