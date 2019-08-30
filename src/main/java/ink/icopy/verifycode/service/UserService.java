package ink.icopy.verifycode.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ink.icopy.verifycode.entity.User;
import ink.icopy.verifycode.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/** @author lizhengguang */
@Service
public class UserService {
  private UserMapper userMapper;

  public UserService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public List<User> queryUserList() {
    QueryWrapper<User> query = new QueryWrapper<User>().eq("id", 3).or(q -> q.eq("name", "Sandy"));
    List<User> users = userMapper.selectList(query);
    users.forEach(u -> System.out.println(u));
    return users;
  }
}
