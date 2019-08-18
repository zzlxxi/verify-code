package ink.icopy.verifycode.conf;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lizhengguang
 * @date 2019-08-18 21：48
 */
@Configuration
public class CustomerWebConfigure implements WebMvcConfigurer {

  @Bean
  public HttpMessageConverters customerWeb() {
    return new HttpMessageConverters(new FastJsonHttpMessageConverter());
  }
}
