package ink.icopy.verifycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lizhengguang
 * @date 2019-8-12 16:54:44
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class VerifyCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VerifyCodeApplication.class, args);
    }

}
