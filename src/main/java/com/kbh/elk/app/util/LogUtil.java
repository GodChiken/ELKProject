package com.kbh.elk.app.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class LogUtil {
	public static Map<String, Object> makeLoggingRequestMap(HttpServletRequest request) {
		Map<String, Object> requestMap = buildRequestInfo(request);
		buildRequestHeader(request, requestMap);
		buildRequestBody((RequestWrapper) request, requestMap);
		return requestMap;
	}

	private static void buildRequestBody(RequestWrapper request, Map<String, Object> requestMap) {
		requestMap.put("body", request.convertToObject());
	}

	private static void buildRequestHeader(HttpServletRequest request, Map<String, Object> requestMap) {
		Map<String, Object> requestHeaderMap = new HashMap<>();
		Collections.list(request.getHeaderNames()).stream()
				.parallel().forEach(
						headerName ->
								requestHeaderMap.put(headerName, request.getHeader(headerName))
				);
		requestMap.put("header", requestHeaderMap);

	}

	private static Map<String, Object> buildRequestInfo(HttpServletRequest request) {
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("url", request.getRequestURL().toString());
		requestMap.put("queryString", request.getQueryString());
		requestMap.put("method", request.getMethod());
		requestMap.put("remoteAddr", request.getRemoteAddr());
		requestMap.put("remoteHost", request.getRemoteHost());
		requestMap.put("remotePort", request.getRemotePort());
		requestMap.put("remoteUser", request.getRemoteUser());
		requestMap.put("encoding", request.getCharacterEncoding());
		return requestMap;
	}

	public static Map<String, Object> makeLoggingResponseMap(HttpServletResponse response) throws IOException {
		Map<String, Object> responseMap = buildResponseInfo(response);
		buildResponseHeader(response, responseMap);
		Object responseBody = ((ResponseWrapper) response).convertToObject();
		responseMap.put("body", responseBody);
		return responseMap;
	}

	private static Map<String, Object> buildResponseInfo(HttpServletResponse response) {
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", response.getStatus());
		return responseMap;
	}

	private static void buildResponseHeader(HttpServletResponse response, Map<String, Object> responseMap) {
		Map<String, Object> responseHeaderMap = new HashMap<>();
		response.getHeaderNames().stream().parallel()
				.forEach(
						headerName ->
								responseHeaderMap.put(headerName, response.getHeader(headerName))
				);
		responseMap.put("header", responseHeaderMap);
	}
}
