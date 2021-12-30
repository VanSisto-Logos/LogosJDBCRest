package com.vansisto.service.impl;

import com.vansisto.dao.BucketDao;
import com.vansisto.dao.impl.BucketDaoImpl;
import com.vansisto.exception.PasswordsDontMatchException;
import com.vansisto.model.Bucket;
import com.vansisto.model.User;
import com.vansisto.service.UserService;
import com.vansisto.util.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserService service = new UserServiceImpl();
    BucketDao bucketDao = new BucketDaoImpl();

    final String EMAIL2 = "test2@test2.test";

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId(7);
        user.setRole(Role.USER);
        user.setEmail(EMAIL2);
        user.setFirstName("Test first 2 name");
        user.setLastName("Test last 2 name");
        user.setPassword("test2");

        service.create(user);
    }

    @AfterEach
    void tearDown() {
        service.deleteById(7);
    }

    @Test
    void create() {
        final int ID = 2;

        User user = new User();
        user.setId(ID);
        user.setRole(Role.ADMIN);
        user.setEmail("test@test.test");
        user.setFirstName("Test first name");
        user.setLastName("Test last name");
        user.setPassword("test");

        User savedUser = service.create(user);

        assertTrue(user.equals(savedUser));
        assertTrue(bucketDao.isExist(ID));

        service.deleteById(ID);

        assertFalse(service.isExist(ID));
        assertFalse(bucketDao.isExist(ID));
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void getByEmailAndPassword() {
        User receivedByEmail = service.getByEmailAndPassword(EMAIL2, "test2");

        assertEquals(7, receivedByEmail.getId());

        assertThrows(PasswordsDontMatchException.class, () -> {
            service.getByEmailAndPassword(EMAIL2, "not real");
        });
    }
}