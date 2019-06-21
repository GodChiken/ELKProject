package com.kbh.elk.app.config.exception.common;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * 10000~19999    : business error code
 * 20000~29999    : field validation error code
 * 30000~39999    : no resource code
 */

@Getter
@Component
public class ErrorMessageMap {
	private static Map<Integer, String> messageMap = new TreeMap<>();

	static {
		messageMap.put(500, "Result ERROR");
		initBusinessCode();
		initFieldValidationCode();
		initNoResourceCode();
	}

	private static void initBusinessCode() {
		messageMap.put(10000, "business error");
	}

	private static void initFieldValidationCode() {
		messageMap.put(20000, "field error");
	}

	private static void initNoResourceCode() {
		messageMap.put(30000, "no resource error");
	}

	public String getMessage(int code) {
		return messageMap.get(code);
	}
}
