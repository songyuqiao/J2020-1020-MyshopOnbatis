package com.mobiletrain.dao;

import com.mobiletrain.domain.Address;
import com.mobiletrain.domain.Cart;
import com.mobiletrain.domain.Goods;

import java.util.List;

public interface OperationDAO {
    int add(Goods goods);

    int addCart(Cart cart);

    List<Cart> query(String uid);

    int sumMoney(String uid);

    List<Address> queryAddress(String id);

    int insert(Address address , String utf8Name , String utf8Detail);

    List<Address> obtain(int id);
}
