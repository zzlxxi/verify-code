package ink.icopy.verifycode.conf;

import ink.icopy.verifycode.common.http.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author lizhengguang
 * @date 2019-08-18 21:09
 */
@ControllerAdvice
public class UnifiedReturnConf {

  @RestControllerAdvice(value = "ink.icopy.verifycode.controller")
  static class ResultResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(
        MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
      return true;
    }

    @Override
    public Object beforeBodyWrite(
        Object bodyObject,
        MethodParameter methodParameter,
        MediaType mediaType,
        Class<? extends HttpMessageConverter<?>> aClass,
        ServerHttpRequest serverHttpRequest,
        ServerHttpResponse serverHttpResponse) {
      if (bodyObject instanceof Result) {
        return bodyObject;
      }
      return new Result<Object>(bodyObject);
    }
  }
}
