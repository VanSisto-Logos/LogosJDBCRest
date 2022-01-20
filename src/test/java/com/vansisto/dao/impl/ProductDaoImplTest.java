package com.vansisto.dao.impl;

import com.vansisto.dao.ProductDao;
import com.vansisto.model.Product;
import com.vansisto.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoImplTest {
    static Session session;
    static ProductDao dao = new ProductDaoImpl();

    @BeforeAll
    static void init() {
        session = HibernateUtil.getSession();
    }

    @Test
    void create() {
        final int ID = 16;
        
        assertFalse(dao.isExist(ID));

        session.beginTransaction();

        dao.create(new Product(ID, "Test product", "Test description", new BigDecimal(55.55)));
        session.getTransaction().commit();

        assertTrue(dao.isExist(ID));

        Product toUpdate = new Product(ID, "Test product updated", "Test description updated", new BigDecimal(66.66));

        session.beginTransaction();
        dao.update(toUpdate);
        session.getTransaction().commit();

        assertEquals(toUpdate, dao.getById(ID));

        session.beginTransaction();
        dao.deleteById(ID);
        session.getTransaction().commit();

        assertFalse(dao.isExist(ID));
    }
}