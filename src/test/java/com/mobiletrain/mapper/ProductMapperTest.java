package com.mobiletrain.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mobiletrain.domain.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ProductMapperTest {
    private Product product = new Product();
    private SqlSession sqlSession;
    private ProductMapper productMapper;

    @Before
    public void init(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
            sqlSession = build.openSession(true);
            productMapper = sqlSession.getMapper(ProductMapper.class);
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
        product.setName("java编年史");
        product.setPubdate("2017-09-12");
        product.setPicture("要鸡毛照片，黄片要不");
        product.setPrice(150000);
        product.setStar("5");
        product.setIntro("要多少本就有多少本");
        product.setTypeid(1);
        int insert = productMapper.insert(product);
        System.out.println(insert);
    }

    @Test
    public void queryPage(){
        PageHelper.startPage(1 , 5);

        List<Product> products = productMapper.queryPage();

        PageInfo<Product> productPageInfo = new PageInfo<>(products);

        System.out.println(products);
    }

    @Test
    public void delete(){
        int delete = productMapper.delete("8");
        System.out.println(delete);
    }
}
