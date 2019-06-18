package com.kbh.elk.app.config.async;

import com.kbh.elk.app.config.exception.AsyncExceptionHandler;
import lombok.AllArgsConstructor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.UUID;
import java.util.concurrent.Executor;

/**
  ASYNC_TASK_CORE_POOL_SIZE    :   기본 Thread 수
  ASYNC_TASK_MAX_POOL_SIZE     :   최대 Thread 수
  ASYNC_TASK_QUEUE_CAPACITY    :   QUEUE 수
  ASYNC_EXECUTOR_BEAN_NAME     :   Thread Bean Name
  ASYNC_THREAD_PREFIX          :   스레드 비동기 전위문자열
 */

@Configuration
@EnableAsync
@AllArgsConstructor
public class AsyncConfig implements AsyncConfigurer {

	private static int ASYNC_TASK_CORE_POOL_SIZE = 10;
	private static int ASYNC_TASK_MAX_POOL_SIZE = 30;
	private static int ASYNC_TASK_QUEUE_CAPACITY = 20;
	private static String ASYNC_EXECUTOR_BEAN_NAME = "taskExecutor";
	private static String ASYNC_THREAD_PREFIX = "KBH-T-";

	private AsyncExceptionHandler asyncExceptionHandler;

	@Bean(name = "taskExecutor")
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(ASYNC_TASK_CORE_POOL_SIZE);
		taskExecutor.setMaxPoolSize(ASYNC_TASK_MAX_POOL_SIZE);
		taskExecutor.setQueueCapacity(ASYNC_TASK_QUEUE_CAPACITY);
		taskExecutor.setBeanName(ASYNC_EXECUTOR_BEAN_NAME);
		taskExecutor.setThreadNamePrefix(ASYNC_THREAD_PREFIX+ UUID.randomUUID().toString());
		taskExecutor.initialize();
		return taskExecutor;
	}
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return asyncExceptionHandler;
	}
}