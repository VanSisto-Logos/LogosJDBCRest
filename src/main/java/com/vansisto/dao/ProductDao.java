package com.vansisto.dao;

import com.vansisto.model.Product;

public interface ProductDao extends Dao<Product> {
    Product getProductByName(String name);
}
