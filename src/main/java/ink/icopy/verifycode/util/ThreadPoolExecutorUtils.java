package ink.icopy.verifycode.util;

import com.google.common.base.Strings;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** @author lizhengguang */
public class ThreadPoolExecutorUtils {

  public void defaultPool() {}

  static ThreadPoolExecutor threadPoolExecutor(
      String threadName,
      boolean daemon,
      int corePoolSize,
      int maximumPoolSize,
      long keepAliveTime) {
    ThreadFactory threadFactory = getThreadFactory(threadName, daemon);
    return getThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, threadFactory);
  }

  private static ThreadPoolExecutor getThreadPoolExecutor(
      int corePoolSize, int maximumPoolSize, long keepAliveTime, ThreadFactory threadFactory) {
    return new ThreadPoolExecutor(
        corePoolSize,
        maximumPoolSize,
        keepAliveTime,
        TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(1024),
        threadFactory,
        new ThreadPoolExecutor.AbortPolicy());
  }

  private static ThreadFactory getThreadFactory(String threadName, boolean daemon) {
    ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
    if (!Strings.isNullOrEmpty(threadName)) {
      threadFactoryBuilder.setNameFormat(threadName);
    }
    threadFactoryBuilder.setDaemon(daemon);
    return threadFactoryBuilder.build();
  }
}
