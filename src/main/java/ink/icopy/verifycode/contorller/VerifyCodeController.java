package ink.icopy.verifycode.contorller;

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
        //创建验证码生成器实例取得生成图片和随机字符串
        VerifyCodeUtils vc = new VerifyCodeUtils();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        //随机字符串存入session中
        HttpSession session = req.getSession();
        session.setAttribute("index_code", text);
        //用流传输
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

    @PostMapping("/request")
    public String request() {
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), "{\"link\":\"https://www.baidu.com\"}");
        Request request = new Request.Builder().url("https://www.baidu.com/sugrec?pre=1&p=3&ie=utf-8&json=1&prod=pc&from=pc_web&sugsid=1434,21122,29523,29519,29098,29568,28835,29221,29589&wd=%E4%B8%8A%E6%B5%B7%E5%A4%A9%E6%B0%94&req=2&pbs=%E4%B8%8A%E6%B5%B7%E5%A4%A9%E6%B0%94&csor=4&pwd=%E4%B8%8A%E6%B5%B7tianqi&cb=jQuery1102019553796293507797_1565684648253&_=1565684648272").method("GET", null).build();
        try {
            Response response = httpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
