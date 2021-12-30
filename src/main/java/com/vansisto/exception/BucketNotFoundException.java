package com.vansisto.exception;

import lombok.extern.log4j.Log4j;

@Log4j
public class BucketNotFoundException extends Throwable {
    public BucketNotFoundException(int id) {
        super("Bucket with id " + id + " not found.");
        log.error("Bucket with id : \"" + id + "\" doesn't exist");
    }
}
