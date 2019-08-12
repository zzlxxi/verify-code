package ink.icopy.verifycode.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author lizhengguang
 * @date 2019-8-12 17:33:10
 */
@Aspect
@Component
public class HttpMethodFlagHandler {

    Logger logger = LoggerFactory.getLogger(HttpMethodFlagHandler.class);

    @Pointcut("@annotation(ink.icopy.verifycode.annotation.HttpMethodFlag)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        final Class<?> target = joinPoint.getTarget().getClass();
        for (Method method : target.getMethods()) {
            final HttpMethodFlag annotation = method.getAnnotation(HttpMethodFlag.class);
            if (annotation != null) {
                logger.error(annotation.name());
            }
        }
        for (Object arg : joinPoint.getArgs()) {
            Class<?> clazz = arg.getClass();
            String className = clazz.getName();
            if ("org.springframework.web.multipart.support.StandardMultipartHttpServletRequest".equals(className)) {
                HttpServletRequest request = (HttpServletRequest) arg;
                logger.error(String.valueOf(request.getRequestURL()));
            } else {
                logger.error(className);
            }
        }
    }

}
