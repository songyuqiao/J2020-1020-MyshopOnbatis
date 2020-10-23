package com.mobiletrain.dao;

import com.mobiletrain.utils.DataSourceUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class CartDAOImpl implements CartDAO {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());
    @Override
    public int queryCarts(String id) {
        String sql = "select sum(money) from `tb_cart` inner join tb_user on tb_user.id = tb_cart.uid where id =?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

    @Override
    public int addOrder(String id, int money, String format) {
        int result=0;
        String sql = "insert into tb_order(uid,money,status,time,aid) values(?,?,1,?,2)";
        try {
             result = jdbcTemplate.update(sql, id, money, format);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
