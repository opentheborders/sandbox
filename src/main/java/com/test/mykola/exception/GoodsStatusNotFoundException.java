package com.test.mykola.exception;

import static java.lang.String.format;

public class GoodsStatusNotFoundException extends RuntimeException {
    public GoodsStatusNotFoundException(Long id) {
        super(format("Goods status with id=%s not found!", id));
    }

    public GoodsStatusNotFoundException(String name) {
        super(format("Goods status with name=%s not found!", name));
    }
}
