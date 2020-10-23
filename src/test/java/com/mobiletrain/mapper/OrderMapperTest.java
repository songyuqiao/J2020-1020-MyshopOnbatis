package com.mobiletrain.mapper;

import com.mobiletrain.domain.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class OrderMapperTest {
    private Order order = new Order();
    private SqlSession sqlSession;
    private OrderMapper orderMapper;

    @Before
    public void init(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
            sqlSession = build.openSession(true);
            orderMapper = sqlSession.getMapper(OrderMapper.class);
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
        order.setUid(7);
        order.setMoney(12000);
        order.setStatus("1");
        order.setTime("2019-10-28");
        order.setAid(2);

        int result = orderMapper.insert(order);
        System.out.println(result);
    }

    @Test
    public void update(){
        order.setMoney(12000);
        order.setTime("2222-9-21");
        order.setUid(7);
        int result = orderMapper.update(order);
        System.out.println(result);
    }

    @Test
    public void delete(){
        int result = orderMapper.delete(1);
        System.out.println(result);
    }

    @Test
    public void queryByUid(){
        List<Order> orders = orderMapper.queryByUid(7);
        System.out.println(orders);
    }
}
