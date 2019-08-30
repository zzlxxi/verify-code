package ink.icopy.verifycode.conf;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/** @author lizhengguang */
@EnableTransactionManagement
@Configuration
@MapperScan("ink.icopy.verifycode.mapper")
public class MybatisPlusConfig {
  /** 分页插件 */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    // 你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制
    paginationInterceptor.setLimit(-1);
    return paginationInterceptor;
  }
}
