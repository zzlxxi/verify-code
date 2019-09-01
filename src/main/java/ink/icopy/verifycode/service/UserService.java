package ink.icopy.verifycode.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ink.icopy.verifycode.entity.User;
import ink.icopy.verifycode.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** @author lizhengguang */
@Slf4j
@Service
public class UserService {

  @Autowired private UserMapper userMapper;

  public List<User> queryUserList() {
    QueryWrapper<User> query = new QueryWrapper<User>().eq("id", 3).or(q -> q.eq("name", "Sandy"));
    List<User> users = userMapper.selectList(query);
    users.forEach(u -> log.info(u.toString()));
    return users;
  }
}
