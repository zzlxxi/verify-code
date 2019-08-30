package ink.icopy.verifycode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.icopy.verifycode.entity.User;

import java.util.List;

/**
 * @author lizhengguang
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> selectList();
}
