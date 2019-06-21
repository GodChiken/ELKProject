package com.kbh.elk.app.config.exception.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

	private int errorCode;

	public BaseException(int code) {
		this.errorCode = code;
	}
}
