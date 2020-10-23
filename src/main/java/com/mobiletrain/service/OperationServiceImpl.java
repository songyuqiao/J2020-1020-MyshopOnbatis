package com.mobiletrain.service;

import com.mobiletrain.dao.OperationDAO;
import com.mobiletrain.dao.OperationDAOImpl;
import com.mobiletrain.domain.Address;
import com.mobiletrain.domain.Cart;
import com.mobiletrain.domain.Goods;

import java.util.List;

public class OperationServiceImpl implements OperationService {
    OperationDAO operation = new OperationDAOImpl();
    @Override
    public boolean add(Goods goods) {
        int result = operation.add(goods);
        return result == 1;
    }

    @Override
    public List<Cart> query(String uid) {
        return operation.query(uid);
    }

    @Override
    public int sumPrice(String uid) {
        int money = operation.sumMoney(uid);
        return money;
    }

    @Override
    public List<Address> queryAddress(String id) {
        return operation.queryAddress(id);
    }

    @Override
    public int insert(Address address , String utf8Name , String utf8Detail) {
        int result = operation.insert(address , utf8Name , utf8Detail);
        return result;
    }

    @Override
    public List<Address> getAddress(int id) {
        //   obtain(获取)
        List<Address> list = operation.obtain(id);
        return list;
    }
}
