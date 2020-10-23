package com.mobiletrain.service;

import com.mobiletrain.domain.Cart;
import com.mobiletrain.domain.User;
import com.mobiletrain.domain.goodType;

import java.util.List;

public interface UserServiceImpl {
    boolean queryUsername(String username);

    boolean queryByEmail(String email);

    boolean codeByCode(String code);

    int insert(User user);

    boolean updateByName(User user);

    List<User> query(String username, String password);

    List<goodType> queryGoodsType();

    boolean queryRole(String username, String password);

    List<Cart> queryById(String id);
}
