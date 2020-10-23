package com.mobiletrain.mapper;

import com.mobiletrain.domain.OrderDetail;

import java.util.List;

public interface OrderDetailMapper {
    int insert(OrderDetail orderDetail);

    List<OrderDetail> queryByOid(String oid);
}
