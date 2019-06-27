package com.kbh.elk.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbh.elk.app.config.exception.common.InternalLogErrorException;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestWrapper extends ContentCachingRequestWrapper{

	private ObjectMapper objectMapper;

	public RequestWrapper(HttpServletRequest request) {
		super(request);
		objectMapper = new ObjectMapper();
	}

	Object convertToObject(){
		byte[] requestBody = getContentAsByteArray();
		return requestBody.length == 0 ? null : mappingBody();
	}

	private Object mappingBody(){
		Object mappedBody;
		try {
			mappedBody = objectMapper.readValue(getContentAsByteArray(), Object.class);
		} catch (IOException e) {
			throw new InternalLogErrorException(41000);
		}
		return mappedBody;
	}
}
