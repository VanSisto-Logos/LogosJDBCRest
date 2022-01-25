package com.vansisto.dao.impl;

import com.vansisto.dao.BucketDao;
import com.vansisto.dao.ProductDao;
import com.vansisto.model.Bucket;
import com.vansisto.model.Product;
import com.vansisto.util.HibernateUtil;
import com.vansisto.util.MySQLConnector;
import com.vansisto.util.Querries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Log4j
public class ProductDaoImpl implements ProductDao {
    private Session session = HibernateUtil.getSession();

//    @Override
//    public Product create(Product product) {
//        boolean withId = product.getId() != 0;
//        final String SQL = withId ? Querries.CREATE_PRODUCT : Querries.CREATE_PRODUCT_WITHOUT_ID;
//
//        try (
//                Connection connection = MySQLConnector.getConnection();
//                PreparedStatement statement = connection.prepareStatement(SQL)
//        ) {
//
//            statement.setString(1, product.getName());
//            statement.setString(2, product.getDescription());
//            statement.setBigDecimal(3, product.getPrice());
//            if (withId) statement.setInt(4, product.getId());
//
//            statement.execute();
//        } catch (SQLException e) {
//            log.error(e);
//        }
//
////        TODO:
//        return null;
//    }

    @Override
    public Product create(Product product) {
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();

        return product;
    }


    @Override
    public Product getById(int id) {
        return session.get(Product.class, id);
    }

    @Override
    public List<Product> getAll() {
        return session.createCriteria(Product.class).list();
    }

    @Override
    public void deleteById(int id) {
        session.beginTransaction();
        session.delete(getById(id));

        session.getTransaction().commit();
    }

    @Override
    public void update(Product product) {
        session.beginTransaction();
        session.merge(product);
        session.getTransaction().commit();
    }

    @Override
    public boolean isExist(int id) {
        return !Objects.isNull(getById(id));
    }

//    public static void main(String[] args) {
//        final int ID = 26;
//        ProductDao dao = new ProductDaoImpl();
//
//        dao.create(new Product( "Test", "Test", new BigDecimal(55.55)));
//        System.out.println(dao.isExist(ID));
//        dao.update(new Product(ID, "Test updated", "Updated", new BigDecimal(66.66)));
//        System.out.println(dao.getById(ID));
//        System.out.println(dao.getAll().size());
//        dao.deleteById(ID);
//        System.out.println(dao.isExist(ID));
//    }

//    public static void main(String[] args) {
//        ProductDao dao = new ProductDaoImpl();
//
//        Product product1 = new Product("MTM Prod 1", "Desc", new BigDecimal(11));
//        Product product2 = new Product("MTM Prod 2", "Desc", new BigDecimal(22));
//
//        Bucket bucket1 = new Bucket();
//        bucket1.getProducts().add(product1);
//        Bucket bucket2 = new Bucket();
//        bucket2.getProducts().add(product1);
//        Bucket bucket3 = new Bucket();
//        Bucket bucket4 = new Bucket();
//        Bucket bucket5 = new Bucket();
//
//        product1.getBuckets().add(bucket1);
//        product1.getBuckets().add(bucket2);
//        product1.getBuckets().add(bucket3);
//
//        product2.getBuckets().add(bucket4);
//        product2.getBuckets().add(bucket5);
//
//        dao.create(product1);
//        dao.create(product2);
//    }

//    public static void main(String[] args) {
//        BucketDao bucketDao = new BucketDaoImpl();
//        Bucket bucket1 = bucketDao.getById(1);
//        Bucket bucket2 = bucketDao.getById(3);
//
//        Product product1 = new Product("MTM Prod 1", "", new BigDecimal(11));
//        Product product2 = new Product("MTM Prod 2", "", new BigDecimal(11));
//        Product product3 = new Product("MTM Prod 3", "", new BigDecimal(11));
//        Product product4 = new Product("MTM Prod 4", "", new BigDecimal(11));
//
//        bucket1.getProducts().add(product1);
//        bucket1.getProducts().add(product2);
//        bucket1.getProducts().add(product3);
//
//        bucket2.getProducts().add(product3);
//        bucket2.getProducts().add(product4);
//        bucket2.getProducts().add(product1);
//
//
//        Session session = HibernateUtil.getSession();
//        session.beginTransaction();
//
//        session.merge(bucket1);
//        session.merge(bucket2);
//        session.getTransaction().commit();
//    }

//    public static void main(String[] args) {
//        BucketDao bucketDao = new BucketDaoImpl();
//        Bucket bucket1 = bucketDao.getById(1);
//
//        Product product1 = new Product("MTM Prod 1", "", new BigDecimal(11));
//        Product product2 = new Product("MTM Prod 2", "", new BigDecimal(11));
//        Product product3 = new Product("MTM Prod 3", "", new BigDecimal(11));
//
//        bucket1.getProducts().add(product1);
//        bucket1.getProducts().add(product2);
//        bucket1.getProducts().add(product3);
//
//        bucketDao.update(bucket1);
//    }

    @Override
    public Product getProductByName(String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        query.where( builder.equal( root.get( "name" ), name ) );

        return session.createQuery(query).getSingleResult();
    }
}
