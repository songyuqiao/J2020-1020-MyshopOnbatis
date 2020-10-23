package com.mobiletrain.service;

import com.mobiletrain.domain.Address;
import com.mobiletrain.domain.Cart;
import com.mobiletrain.domain.Goods;

import java.util.List;

public interface OperationService {
    boolean add(Goods goods);

    List<Cart> query(String uid);

    int sumPrice(String uid);

    List<Address> queryAddress(String id);

    int insert(Address address , String utf8Name , String utf8Detail);

    List<Address> getAddress(int id);
}
