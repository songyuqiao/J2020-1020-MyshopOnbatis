package com.mobiletrain.mapper;

import com.mobiletrain.domain.Cart;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class CartMapperTest {
    private Cart cart = new Cart();
    private SqlSession sqlSession;
    private CartMapper cartMapper;

    @Before
    public void init(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
            sqlSession = build.openSession(true);
            cartMapper = sqlSession.getMapper(CartMapper.class);
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
        cart.setPid(1);
        cart.setNum(2);
        cart.setMoney(15000);
        cart.setUid(8);
        int insert = cartMapper.insert(cart);
        System.out.println(insert);
    }

    @Test
    public void delete(){
        Integer[] arr = {1 , 2};
        List<Integer> ints = Arrays.asList(arr);
        int delete = cartMapper.delete(ints);
        System.out.println(delete);
    }

    @Test
    public void queryByUid(){
        List<Cart> cartList = cartMapper.queryByUid("8");
        System.out.println(cartList);
    }
}
