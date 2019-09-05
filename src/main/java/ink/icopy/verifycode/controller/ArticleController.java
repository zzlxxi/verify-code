package ink.icopy.verifycode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lizhengguang
 */
@Controller
public class ArticleController {

  @RequestMapping("/articleList.html")
  public String getArticleList(
      Model model,
      String title,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "1") Integer pageNum) {

    List<Object> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      //
      Map<String, Object> map = new HashMap<>(16);
      map.put("id", i);
      map.put("title", "测试标题");
      map.put("summary", "测试摘要");
      map.put("createTime", System.currentTimeMillis());
      list.add(map);
    }

    model.addAttribute("list", list);
    return "articleList";
  }
}
