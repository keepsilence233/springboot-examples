package cn.wengzi.mapper;

import cn.wengzi.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Arley
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> queryUserList();
}
