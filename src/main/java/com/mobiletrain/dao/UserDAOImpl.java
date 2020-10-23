package com.mobiletrain.dao;

import com.mobiletrain.domain.Cart;
import com.mobiletrain.domain.User;
import com.mobiletrain.domain.goodType;
import com.mobiletrain.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    JdbcTemplate jdbc = new JdbcTemplate(DataSourceUtils.getDataSource());
    @Override
    public List<User> QueryByName(String username) {
        String sql = "select * from tb_user where username = ?";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    @Override
    public List<User> queryByEmail(String email) {
        String sql = "select * from tb_user where email = ?";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(User.class), email);
    }

    @Override
    public int codeByCode(String code) {
        return 1;
    }

    @Override
    public int insert(User use) {
        String sql = "insert into tb_user(username,password,email,gender,flag,role,code) values(?,?,?,?,?,?,?)";
        return jdbc.update(sql , use.getUsername() , use.getPassword() , use.getEmail() , use.getGender() , 1 , 1 , use.getCode());
    }

    @Override
    public int updateByName(User user) {
        String sql = "update tb_user set flag = 2 where code = ?";
        return jdbc.update(sql, user.getCode());
    }

    @Override
    public List<User> query(String username, String password) {
        String sql = "select * from tb_user where username = ? and password = ?";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(User.class), username, password);
    }

    @Override
    public List<goodType> queryGoodsType() {
        String sql = "select * from tb_goods_type";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(goodType.class));
    }

    @Override
    public List<User> queryRole(String username, String password) {
        String sql = "select * from tb_user where role = 0 and username = ? and password = ?";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(User.class) , username , password);
    }

    @Override
    public List<Cart> queryById(String id) {
        String sql = "select tb_cart.*, tb_goods.name as goodName from  tb_cart inner join tb_goods on tb_goods.id=tb_cart.pid where uid = ?";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Cart.class), id);
    }
}
