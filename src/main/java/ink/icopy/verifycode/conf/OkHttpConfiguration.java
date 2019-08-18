package ink.icopy.verifycode.conf;

import ink.icopy.verifycode.util.SSLSocketClientFactory;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author lizhengguang
 * @date 2019-8-13 15:52:27
 */
@Configuration
public class OkHttpConfiguration {

  @Bean
  public OkHttpClient OkHttpClient() {
    return new OkHttpClient()
        .newBuilder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .sslSocketFactory(SSLSocketClientFactory.getSSLSocketFactory())
        .hostnameVerifier(SSLSocketClientFactory.getHostnameVerifier())
        .build();
  }
}
