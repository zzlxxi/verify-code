package ink.icopy.verifycode.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
public class ThreadPoolExecutorTest {

  private Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);

  @Test
  public void pool() {
    ThreadPoolExecutor executor =
        ThreadPoolExecutorUtils.threadPoolExecutor("logThread", false, 5, 20, 0L);
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));
    executor.submit(() -> logger.info(Thread.currentThread().getName()));

    executor.shutdown();
    if (executor.isShutdown()) {
      logger.info("线程池关闭");
    }
  }
}
