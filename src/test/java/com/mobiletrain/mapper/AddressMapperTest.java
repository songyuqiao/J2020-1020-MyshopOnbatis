package com.mobiletrain.mapper;

import com.mobiletrain.domain.Address;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AddressMapperTest {
    private Address address = new Address();
    private SqlSession sqlSession;
    private AddressMapper addressMapper;

    @Before
    public void init(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
            sqlSession = build.openSession(true);
            addressMapper = sqlSession.getMapper(AddressMapper.class);
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
        address.setDetail("吉林省四平市铁西区");
        address.setName("宋雨桥");
        address.setPhone("18704340284");
        address.setUid(7);
        address.setLevel(1);

        int insert = addressMapper.insert(address);
        System.out.println(insert);
    }

    @Test
    public void queryByUid(){
        List<Address> addresses = addressMapper.queryByUid("7");
        System.out.println(addresses);
    }
}
