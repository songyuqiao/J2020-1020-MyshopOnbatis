package com.mobiletrain.mapper;

import com.mobiletrain.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserMapperTest {
    private User user = new User();
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void init(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
            sqlSession = build.openSession(true);
            userMapper = sqlSession.getMapper(UserMapper.class);
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
        user.setUsername("胡汉三");
        user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        user.setEmail("3622807903@qq.com");
        user.setGender("m");
        user.setFlag(1);
        user.setRole(1);
        user.setCode("b928b09b-db81-4939-b865-821b29c07513");

        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void queryByUsername(){
        List<User> username = userMapper.queryByUsername("胡汉三");
        if(username.get(0) == null){
            System.out.println("可以注册");
        }else{
            System.out.println("该用户名已存在，请重新输入");
        }
    }

    @Test
    public void queryByEmail(){
        List<User> email = userMapper.queryByEmail("3622807903@qq.com");
        if(email.get(0) == null){
            System.out.println("可以注册");
        }else{
            System.out.println("邮箱已存在，请重新输入");
        }
    }

    @Test
    public void queryByUsernameAndPassword(){
        User user = userMapper.query("宋雨桥", "e10adc3949ba59abbe56e057f20f883e");
        System.out.println(user);
    }

    @Test
    public void active(){
        int active = userMapper.active("b928b09b-db81-4939-b865-821b29c07513");
        System.out.println(active);
    }
}
