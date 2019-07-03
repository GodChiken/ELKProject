package com.kbh.elk.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseWrapper extends ContentCachingResponseWrapper {
	private ObjectMapper objectMapper;

	public ResponseWrapper(HttpServletResponse response) {
		super(response);
		this.objectMapper = new ObjectMapper();
	}

	Object convertToObject() throws IOException {
		if (getContentAsByteArray().length == 0) {
			return "";
		}
		return objectMapper.readValue(getContentAsByteArray(), Object.class);
	}
}