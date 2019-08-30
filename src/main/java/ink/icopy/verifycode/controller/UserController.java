package ink.icopy.verifycode.controller;

import ink.icopy.verifycode.entity.User;
import ink.icopy.verifycode.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** @author lizhengguang */
@RestController
@RequestMapping("/user")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/list")
  public List<User> queryUsers() {
    return userService.queryUserList();
  }
}
