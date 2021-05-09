package com.test.mykola.exception;

import static java.lang.String.format;

public class GoodsNotFoundException extends RuntimeException {
    public GoodsNotFoundException(Long id) {
        super(format("Goods with id=%s not found!", id));
    }
}
