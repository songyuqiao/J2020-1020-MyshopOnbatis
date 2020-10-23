package com.mobiletrain.mapper;

import com.mobiletrain.domain.Cart;

import java.util.List;

public interface CartMapper {
    int insert(Cart cart);

    int delete(List<Integer> list);

    List<Cart> queryByUid(String uid);
}
