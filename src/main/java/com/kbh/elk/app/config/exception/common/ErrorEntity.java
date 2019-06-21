package com.kbh.elk.app.config.exception.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorEntity {
	private String status;
	private String message;
	private int code = 0;
}
