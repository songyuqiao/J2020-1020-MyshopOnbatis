package com.mobiletrain.service;

public interface CartService {
    int queryCarts(String id);

    int addOrder(String id, int money, String format);
}
