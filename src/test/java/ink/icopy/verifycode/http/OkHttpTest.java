package ink.icopy.verifycode.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OkHttpTest {

  @Autowired private OkHttpClient client;

  @Test
  public void request() {
    Request request = new Request.Builder().url("https://publicobject.com/helloworld.txt").build();
    try {
      final Response response = client.newCall(request).execute();
      response.body().source().readByteString();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
