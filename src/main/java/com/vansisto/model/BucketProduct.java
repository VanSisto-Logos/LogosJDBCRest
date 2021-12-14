package com.vansisto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BucketProduct {
    private int id;
    private int bucketId;
    private int productId;
    private Integer number;

    public BucketProduct(int bucketId, int productId, Integer number) {
        this.bucketId = bucketId;
        this.productId = productId;
        this.number = number;
    }

    public BucketProduct(int bucketId, int productId) {
        this.bucketId = bucketId;
        this.productId = productId;
    }
}
