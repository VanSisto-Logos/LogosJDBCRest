package com.vansisto.util;

public class Querries {
//    BUCKET
    public static String CREATE_BUCKET = "INSERT INTO bucket(created_date, id) VALUES (?, ?)";

//    USER
    public static String CREATE_USER = "INSERT INTO user (email, password, first_name, last_name, role, id) VALUES (?, ?, ?, ?, ?, ?)";
    public static String CREATE_USER_WITHOUT_ID = "INSERT INTO user (email, password, first_name, last_name, role) VALUES (?, ?, ?, ?, ?)";
    public static String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email LIKE ?";
    public static String GET_ALL_USERS = "SELECT * FROM user";
    public static String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    public static String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    public static String UPDATE_USER_BY_ID = "UPDATE user SET first_name = ?, last_name = ?, email = ?, role = ? WHERE id = ?";

//    PRODUCT
    public static String CREATE_PRODUCT = "INSERT INTO product(name, description, price, id) VALUES (?, ?, ?, ?)";
    public static String CREATE_PRODUCT_WITHOUT_ID = "INSERT INTO product(name, description, price) VALUES (?, ?, ?)";

//    BUCKET_PRODUCT
    public static String CREATE_BUCKET_PRODUCT = "INSERT INTO bucket_product (bucket_id, product_id, number, id) VALUES (?, ?, ?, ?)";
    public static String CREATE_BUCKET_PRODUCT_WITHOUT_ID = "INSERT INTO bucket_product (bucket_id, product_id, number) VALUES (?, ?, ?)";
}
