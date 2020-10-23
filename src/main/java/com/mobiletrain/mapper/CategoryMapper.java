package com.mobiletrain.mapper;

import com.mobiletrain.domain.Category;

import java.util.List;

public interface CategoryMapper {
    int insert(Category category);

    List<Category> queryByParent(String parentid);

    List<Category> queryAll();
}
