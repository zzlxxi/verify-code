package ink.icopy.verifycode.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;

@SpringBootTest
public class ThreadPoolExecutorTest {

  private Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);

  @Test
  public void pool() {
    ExecutorService executorService =
        ThreadPoolExecutorUtils.threadPoolExecutor("TestThreadName", false, 5, 20, 0L);
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));
    executorService.submit(() -> logger.info(Thread.currentThread().getName()));

    executorService.shutdown();
  }
}
