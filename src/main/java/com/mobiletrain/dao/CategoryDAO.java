package com.mobiletrain.dao;

import com.mobiletrain.domain.Category;
import com.mobiletrain.domain.Goods;

import java.util.List;

public interface CategoryDAO {
    //查询商品类别
    List<Category> queryAll();

    int insert(Category category);

    List<Goods> getGoods();

    List<Goods> queryById(String id);
}
