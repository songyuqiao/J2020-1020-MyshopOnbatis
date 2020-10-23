package com.mobiletrain.dao;

import com.mobiletrain.domain.Category;
import com.mobiletrain.domain.Goods;
import com.mobiletrain.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    JdbcTemplate jdbc = new JdbcTemplate(DataSourceUtils.getDataSource());
    @Override
    public List<Category> queryAll() {
        String sql = "select * from tb_goods_type";

        return jdbc.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public int insert(Category category) {
        String sql = "insert into tb_goods_type(name,level,Parent) values (?,?,?)";


        String lastIdSql = "select last_insert_id()";
        Integer id = jdbc.queryForObject(lastIdSql , Integer.class);
        category.setId(id);

        return jdbc.update(sql, category.getName(), category.getLevel(), category.getParent());
    }

    @Override
    public List<Goods> getGoods() {
        String sql = "select * from tb_goods";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Goods.class));
    }

    @Override
    public List<Goods> queryById(String id) {
        String sql  = "select * from tb_goods where id = ?";
        return jdbc.query(sql , new BeanPropertyRowMapper<>(Goods.class) , id);
    }
}
