package com.mobiletrain.dao;

public interface CartDAO {
    int queryCarts(String id);

    int addOrder(String id, int money, String format);
}
