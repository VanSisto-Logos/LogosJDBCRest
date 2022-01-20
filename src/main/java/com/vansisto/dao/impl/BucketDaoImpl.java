package com.vansisto.dao.impl;

import com.vansisto.dao.BucketDao;
import com.vansisto.model.Bucket;
import com.vansisto.util.HibernateUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;

@Log4j
public class BucketDaoImpl implements BucketDao {
    private Session session = HibernateUtil.getSession();

    @Override
    public Bucket create(Bucket bucket) {
        session.beginTransaction();
        session.persist(bucket);
        session.getTransaction().commit();
        return bucket;
    }

    @Override
    public Bucket getById(int id) {
        return session.get(Bucket.class, id);
    }

    @Override
    public List<Bucket> getAll() {
        return session.createCriteria(Bucket.class).list();
    }

    @SneakyThrows
    @Override
    public void deleteById(int id) {
        session.beginTransaction();
        session.delete(getById(id));
        session.getTransaction().commit();
    }

    @Override
    public void update(Bucket bucket) {
        session.beginTransaction();
        session.merge(bucket);
        session.getTransaction().commit();
    }

    @Override
    public boolean isExist(int id) {
        return !Objects.isNull(getById(id));
    }
}
