package com.mobiletrain.mapper;

import com.mobiletrain.domain.OrderDetail;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class OrderDetailMapperTest {
    private OrderDetail orderDetail = new OrderDetail();
    private SqlSession sqlSession;
    private OrderDetailMapper orderDetailMapper;

    @Before
    public void init(){
        try{
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
            sqlSession = build.openSession(true);
            orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @After
    public void destory(){
        sqlSession.close();
    }

    @Test
    public void insert(){
        orderDetail.setOid("2");
        orderDetail.setPid(7);
        orderDetail.setNum(2);
        orderDetail.setMoney(8000);

        int insert = orderDetailMapper.insert(orderDetail);
        System.out.println(insert);
    }

    @Test
    public void queryByOid(){
        List<OrderDetail> orderDetails = orderDetailMapper.queryByOid("2");
        System.out.println(orderDetails);
    }
}
