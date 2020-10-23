package com.mobiletrain.mapper;

import com.mobiletrain.domain.Product;

import java.util.List;

public interface ProductMapper {
    int insert(Product product);

    List<Product> queryPage();

    int delete(String id);
}
