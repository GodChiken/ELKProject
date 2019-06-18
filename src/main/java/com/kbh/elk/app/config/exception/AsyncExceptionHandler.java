package com.kbh.elk.app.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.StringJoiner;
import java.util.stream.Stream;

@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	private final static String FLOW_PATTERN = " > ";
	private final static String PACKAGE_SPLIT = "\\.";
	private final Logger logger = LoggerFactory.getLogger(AsyncExceptionHandler.class);

	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
		logger.error(getAsyncMessageFormat(method),throwable);
	}

	private String getAsyncMessageFormat(Method method) {
		return new StringJoiner(FLOW_PATTERN)
				.add(splitByPackage(method.getDeclaringClass().getName()))
				.add(method.getName())
				.add(splitByPackage(method.getGenericReturnType().getTypeName()))
				.toString();
	}
	private String splitByPackage(String target){
		return Stream.of(target.split(PACKAGE_SPLIT))
				.reduce((first,last) -> last)
				.get();
	}
}
