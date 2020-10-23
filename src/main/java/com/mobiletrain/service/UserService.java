package com.mobiletrain.service;

import com.mobiletrain.dao.UserDAO;
import com.mobiletrain.dao.UserDAOImpl;
import com.mobiletrain.domain.Cart;
import com.mobiletrain.domain.User;
import com.mobiletrain.domain.goodType;

import java.util.List;

public class UserService implements UserServiceImpl{
    UserDAO user = new UserDAOImpl();
    @Override
    public boolean queryUsername(String username) {
        List<User> list = user.QueryByName(username);
        boolean result;
        if(list.size() >= 1){
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    @Override
    public boolean queryByEmail(String email) {
        List<User> list = user.queryByEmail(email);
        boolean result;
        if(list.size() >= 1){
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    @Override
    public boolean codeByCode(String code) {
        int i = user.codeByCode(code);
        return i == 1;
    }

    @Override
    public int insert(User use) {
        return user.insert(use);
    }

    @Override
    public boolean updateByName(User use) {
        int result = user.updateByName(use);
        return result == 1 ;
    }

    @Override
    public List<User> query(String username, String password) {
        List<User> list = user.query(username , password);


        return list;
    }

    @Override
    public List<goodType> queryGoodsType() {
        return user.queryGoodsType();
    }

    @Override
    public boolean queryRole(String username, String password) {
        boolean result;
        List<User> list = user.queryRole(username , password);
        if(list.size() != 0){
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    @Override
    public List<Cart> queryById(String id) {
        return user.queryById(id);
    }
}
