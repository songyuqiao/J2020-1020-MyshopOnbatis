package com.mobiletrain.dao;

import com.mobiletrain.domain.Cart;
import com.mobiletrain.domain.User;
import com.mobiletrain.domain.goodType;

import java.util.List;

public interface UserDAO {
    List<User> QueryByName(String username);

    List<User> queryByEmail(String email);

    int codeByCode(String code);

    int insert(User use);

    int updateByName(User use);

    List<User> query(String username, String password);

    List<goodType> queryGoodsType();

    List<User> queryRole(String username, String password);

    List<Cart> queryById(String id);
}
