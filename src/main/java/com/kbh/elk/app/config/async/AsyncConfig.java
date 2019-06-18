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


/**
2019-06-17 10:46:29.669 DEBUG 17192 [KBH-T-1              ] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [*]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT,-java.lang.Exception
2019-06-17 10:46:29.669 DEBUG 17192 [KBH-T-1              ] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(776919546PersistenceContext[entityKeys=[],collectionKeys=[]];ActionQueue[insertions=ExecutableList{size=0} updates=ExecutableList{size=0} deletions=ExecutableList{size=0} orphanRemovals=ExecutableList{size=0} collectionCreations=ExecutableList{size=0} collectionRemovals=ExecutableList{size=0} collectionUpdates=ExecutableList{size=0} collectionQueuedOps=ExecutableList{size=0} unresolvedInsertDependencies=null])] for JPA transaction
2019-06-17 10:46:29.669 DEBUG 17192 [KBH-T-1              ] o.h.e.t.internal.TransactionImpl         : On TransactionImpl creation, JpaCompliance#isJpaTransactionComplianceEnabled == false
2019-06-17 10:46:29.669 DEBUG 17192 [KBH-T-1              ] o.h.e.t.internal.TransactionImpl         : begin
2019-06-17 10:46:29.685 DEBUG 17192 [KBH-T-1              ] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@20893243]
2019-06-17 10:46:29.689 DEBUG 17192 [KBH-T-1              ] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(776919546PersistenceContext[entityKeys=[],collectionKeys=[]];ActionQueue[insertions=ExecutableList{size=0} updates=ExecutableList{size=0} deletions=ExecutableList{size=0} orphanRemovals=ExecutableList{size=0} collectionCreations=ExecutableList{size=0} collectionRemovals=ExecutableList{size=0} collectionUpdates=ExecutableList{size=0} collectionQueuedOps=ExecutableList{size=0} unresolvedInsertDependencies=null])] for JPA transaction
2019-06-17 10:46:29.689 DEBUG 17192 [KBH-T-1              ] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2019-06-17 10:46:29.695 DEBUG 17192 [KBH-T-1              ] stomAnnotationTransactionAttributeSource : Adding transactional method 'getOne' with attribute: PROPAGATION_REQUIRED,ISOLATION_DEFAULT,readOnly
2019-06-17 10:46:29.696 DEBUG 17192 [KBH-T-1              ] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(776919546PersistenceContext[entityKeys=[],collectionKeys=[]];ActionQueue[insertions=ExecutableList{size=0} updates=ExecutableList{size=0} deletions=ExecutableList{size=0} orphanRemovals=ExecutableList{size=0} collectionCreations=ExecutableList{size=0} collectionRemovals=ExecutableList{size=0} collectionUpdates=ExecutableList{size=0} collectionQueuedOps=ExecutableList{size=0} unresolvedInsertDependencies=null])] for JPA transaction
2019-06-17 10:46:29.696 DEBUG 17192 [KBH-T-1              ] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2019-06-17 10:46:29.696 TRACE 17192 [KBH-T-1              ] o.s.t.i.TransactionInterceptor           : Getting transaction for [org.springframework.data.jpa.repository.support.SimpleJpaRepository.getOne]
2019-06-17 10:46:29.703 TRACE 17192 [KBH-T-1              ] .s.t.s.TransactionSynchronizationManager : Retrieved value [org.springframework.orm.jpa.EntityManagerHolder@61b43878] for key [org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean@1f0e2bdc] bound to thread [KBH-T-1]
 */
