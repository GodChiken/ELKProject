package com.kbh.elk.app.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbh.elk.app.util.LogUtil;
import com.kbh.elk.app.util.RequestWrapper;
import com.kbh.elk.app.util.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

import static java.lang.String.valueOf;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest req,
	                                HttpServletResponse res,
	                                FilterChain chain) throws ServletException, IOException {
		ContentCachingRequestWrapper request = new RequestWrapper(req);
		ContentCachingResponseWrapper response = new ResponseWrapper(res);
		Map<String, Object> requestMap = LogUtil.makeLoggingRequestMap(request);
		chain.doFilter(request, response);
		Map<String, Object> responseMap = LogUtil.makeLoggingResponseMap(response);
		setUniqueIdentifier(requestMap,responseMap);
		log.info(new ObjectMapper().writeValueAsString(requestMap));
		log.info(new ObjectMapper().writeValueAsString(responseMap));
		MDC.clear();
		response.copyBodyToResponse();
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		AntPathMatcher pathMatcher = new AntPathMatcher();
		String targetPath = request.getServletPath();
		return pathMatcher.match("/favicon.ico", targetPath);
	}
	private void setUniqueIdentifier(Map requestMap, Map responseMap){
		String uniqueIdentifier = generateUniqueIdentifier();
		requestMap.put("UUID",uniqueIdentifier);
		responseMap.put("UUID",uniqueIdentifier);
	}
	private String generateUniqueIdentifier() {
		return valueOf(new StringJoiner("-")
				.add("KBH")
				.add("T")
				.add(valueOf(UUID.randomUUID())));
	}
}
