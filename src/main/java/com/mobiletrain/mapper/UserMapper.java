package com.mobiletrain.mapper;

import com.mobiletrain.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int insert(User user);

    List<User> queryByUsername(String username);

    List<User> queryByEmail(String email);

    User query(@Param("username") String username , @Param("password") String password);

    int active(String code);
}
