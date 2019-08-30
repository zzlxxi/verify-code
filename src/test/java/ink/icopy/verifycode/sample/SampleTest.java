package ink.icopy.verifycode.sample;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ink.icopy.verifycode.entity.User;
import ink.icopy.verifycode.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {
  @Autowired private UserMapper userMapper;

  @Test
  public void testSelect() {
    QueryWrapper<User> query = new QueryWrapper<User>().eq("id", 3).or(q -> q.eq("name", "Sandy"));
    List<User> users = userMapper.selectList(query);
    users.forEach(u -> System.out.println(u));
  }
}
