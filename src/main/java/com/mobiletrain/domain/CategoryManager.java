package com.mobiletrain.domain;

import com.mobiletrain.dao.CategoryDAO;
import com.mobiletrain.dao.CategoryDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private static List<Category> categorys ;

    //将从数据库里获取到的数据，存放到category里
    static {
        CategoryDAO category = new CategoryDAOImpl();
        categorys = category.queryAll();
    }
    //根据等级查询相应商品循环添加到集合里面
    public static List<Category> queryByLevel(int level){
        List<Category> list = new ArrayList<>();

        for(Category category:categorys){
            if(category.getLevel() == level){
                list.add(category);
            }
        }
        return list;
    }

    public static List<Category> queryByparent(String parent){
        List<Category> list = new ArrayList<>();
        //先不加if判断 ， 一会调整
        if(parent != null){
            for(Category category:categorys){
                if(parent.equals(category.getParent())){
                    list.add(category);
                }
            }
        }

        return list;
    }
    public static List<Category> getCategorys(){
        return categorys;
    }

    public static void add(Category category){
        CategoryDAO dao = new CategoryDAOImpl();
        int result = dao.insert(category);

        //如果返回的值为1，则为插入成功
        if(result == 1){
            //插入
            categorys.add(category);
        }
    }

    public static List<Goods> getGoods() {
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        List<Goods> category = categoryDAO.getGoods();
        return category;
    }

    public static List<Goods> queryById(String id) {
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        return categoryDAO.queryById(id);
    }
}
