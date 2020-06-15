package com.arley.mapper;

import com.arley.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author leizige
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserList();
}
