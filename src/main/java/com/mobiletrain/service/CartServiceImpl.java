package com.mobiletrain.service;

import com.mobiletrain.dao.CartDAO;
import com.mobiletrain.dao.CartDAOImpl;

public class CartServiceImpl implements CartService {
    CartDAO cartDAO = new CartDAOImpl();
    @Override
    public int queryCarts(String id) {
        int result = cartDAO.queryCarts(id);
        return result;
    }

    @Override
    public int addOrder(String id, int money, String format) {
        int result = cartDAO.addOrder(id , money , format);
        return result;
    }
}
