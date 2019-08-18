package ink.icopy.verifycode.controller;

import ink.icopy.verifycode.annotation.HttpMethodFlag;
import ink.icopy.verifycode.util.VerifyCodeUtils;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author lizhengguang
 * @date 2019-8-12 16:52:58
 */
@RestController
public class VerifyCodeController {

  private OkHttpClient httpClient;
  Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);

  public VerifyCodeController(OkHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @GetMapping("/verifyCode")
  public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    // 创建验证码生成器实例取得生成图片和随机字符串
    VerifyCodeUtils vc = new VerifyCodeUtils();
    BufferedImage image = vc.getImage();
    String text = vc.getText();
    // 随机字符串存入session中
    HttpSession session = req.getSession();
    session.setAttribute("index_code", text);
    // 用流传输
    VerifyCodeUtils.output(image, resp.getOutputStream());
  }

  @PostMapping("/verify")
  @HttpMethodFlag(name = "verifyabcabc")
  public Object verify(HttpServletRequest req, String code) {
    HttpSession session = req.getSession();
    String sessionId = session.getId();
    logger.info(sessionId + " : " + code);
    return sessionId;
  }

  @GetMapping("/request")
  public String request(String bbb) {
    Request request = new Request.Builder().url(bbb).method("GET", null).build();
    try {
      Response response = httpClient.newCall(request).execute();
      return response.body().string();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @GetMapping("/default")
  public Object defaultResult() {
    return "this is default 123";
  }
}
