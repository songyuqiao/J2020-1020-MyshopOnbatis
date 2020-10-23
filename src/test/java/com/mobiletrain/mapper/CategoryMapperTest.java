package com.mobiletrain.mapper;

import com.mobiletrain.domain.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class CategoryMapperTest {
    private Category category = new Category();
    private SqlSession sqlSession;
    private CategoryMapper categoryMapper;

    @Before
    public void init(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
            sqlSession = build.openSession(true);
            categoryMapper = sqlSession.getMapper(CategoryMapper.class);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @After
    public void destory(){
        sqlSession.close();
    }

    @Test
    public void insert(){
        category.setName("小顽童智能手表");
        category.setLevel(1);
        category.setParent("0");
        int insert = categoryMapper.insert(category);
        System.out.println(insert);
    }

    @Test
    public void queryByParent(){
        List<Category> categories = categoryMapper.queryByParent("6");
        System.out.println(categories);
    }

    @Test
    public void queryAll(){
        List<Category> categories = categoryMapper.queryAll();
        System.out.println(categories);
    }
}
