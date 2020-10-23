package com.mobiletrain.dao;

import com.mobiletrain.domain.Address;
import com.mobiletrain.domain.Cart;
import com.mobiletrain.domain.Goods;
import com.mobiletrain.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OperationDAOImpl implements OperationDAO {
    JdbcTemplate jdbc = new JdbcTemplate(DataSourceUtils.getDataSource());
    @Override
    public int add(Goods goods) {
        String sql = "insert into tb_goods(name,pubdate,picture,price,star,intro,typeid) values (?,?,?,?,?,?,?)";
        return jdbc.update(sql,
                goods.getName(),
                goods.getPubdate(),
                goods.getPicture(),
                goods.getPrice(),
                goods.getStar(),
                goods.getIntro(),
                goods.getTypeid());
    }

    @Override
    public int addCart(Cart cart) {
        String sql = "insert into tb_cart(pid,num,money,uid) values (?,1,?,?)";
        return jdbc.update(sql, cart.getPid(), cart.getMoney(),cart.getUid());
    }

    @Override
    public List<Cart> query(String uid) {
        String sql = "select tb_cart.*, tb_goods.name as goodName from  tb_cart inner join tb_goods on tb_goods.id=tb_cart.pid where tb_cart.id = ?";
        return jdbc.query(sql , new BeanPropertyRowMapper<>(Cart.class) , uid);
    }

    @Override
    public int sumMoney(String uid) {
        String sql = "select count(money) from tb_cart where id = ?";
        return jdbc.queryForObject(sql, Integer.class, uid);
    }

    @Override
    public List<Address> queryAddress(String id) {
        String sql = "select tb_address.* from tb_address inner join tb_user on tb_address.uid = tb_user.id where uid = ?";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Address.class), id);
    }

    @Override
    public int insert(Address address , String utf8Name , String utf8Detail) {
        String sql = "insert into tb_address(detail,`name`,phone,uid,`level`) values (?,?,?,?,1)";
        return jdbc.update(sql, utf8Detail, utf8Name, address.getPhone(), address.getUid());
    }

    @Override
    public List<Address> obtain(int id) {
        String sql = "select * from tb_address where uid = ?";
        //   obtain(获取)
        List<Address> obtain = jdbc.query(sql , new BeanPropertyRowMapper<>(Address.class) , id);
        return obtain;
    }
}
