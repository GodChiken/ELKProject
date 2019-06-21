package com.kbh.elk.app.util;

import org.apache.commons.lang3.EnumUtils;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public interface BaseEnum {
	String getName();

	static Map<String, String> getEnumToMap(Class clazz){
		Map<String, BaseEnum> enumMap = EnumUtils.getEnumMap(clazz);
		return enumMap.entrySet().stream()
				.collect(toMap(e -> e.getKey(), e -> e.getValue().getName()));
	}
}
