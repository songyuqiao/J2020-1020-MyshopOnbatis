package com.mobiletrain.mapper;

import com.mobiletrain.domain.Order;

import java.util.List;

public interface OrderMapper {
    int insert(Order order);

    int update(Order order);

    int delete(int id);

    List<Order> queryByUid(int uid);
}
