package com.kbh.elk.app.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

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
				.parallel().forEach(headerInfoSetting(request, requestHeaderMap));
		requestHeaderMap.entrySet().parallelStream()
				.forEach(element -> requestMap.put(element.getKey(), element.getValue()));
	}

	private static Consumer<String> headerInfoSetting(HttpServletRequest request, Map<String, Object> requestHeaderMap) {
		return headerName -> {
			if (headerName.equals("user-agent"))
				requestHeaderMap.putAll(separateUserAgent(request.getHeader(headerName)));
			else requestHeaderMap.put(headerName, request.getHeader(headerName));
		};
	}

	private static Map<? extends String, ?> separateUserAgent(String userAgentString) {
		Map<String, Object> userAgentMap = new HashMap<>();
		UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
		Browser browser = userAgent.getBrowser();
		Version version = userAgent.getBrowserVersion();
		userAgentMap.put("browserName", browser.getName());
		userAgentMap.put("browserType", browser.getBrowserType());
		userAgentMap.put("browserManufacturer", browser.getManufacturer());
		userAgentMap.put("browserRenderingEngine", browser.getRenderingEngine());
		userAgentMap.put("browserVersion", Optional.ofNullable(version).map(v -> v.getVersion()).orElse(""));
		OperatingSystem operatingSystem = userAgent.getOperatingSystem();
		userAgentMap.put("OSType", operatingSystem.getDeviceType());
		userAgentMap.put("OSManufacture", operatingSystem.getManufacturer());
		userAgentMap.put( "OSName", operatingSystem.getName());
		return userAgentMap;
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
		response.getHeaderNames().stream()
				.parallel()
				.forEach(headerName
						-> responseHeaderMap.put(headerName, response.getHeader(headerName)));
		responseMap.put("header", responseHeaderMap);
	}
}
