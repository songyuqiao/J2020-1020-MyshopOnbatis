package com.mobiletrain.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

public class MyBatisDruidDataSource extends PooledDataSourceFactory {
    public MyBatisDruidDataSource(){
        this.dataSource = new DruidDataSource();
    }
}
