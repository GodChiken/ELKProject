package com.kbh.elk.app.config.filter;

import com.kbh.elk.app.util.LogUtil;
import com.kbh.elk.app.util.RequestWrapper;
import com.kbh.elk.app.util.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.value;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest req,
	                                HttpServletResponse res,
	                                FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = new RequestWrapper(req);
		HttpServletResponse response = new ResponseWrapper(res);
		Map<String, Object> requestMap = LogUtil.makeLoggingRequestMap(request);
		chain.doFilter(request, response);
		Map<String, Object> responseMap = LogUtil.makeLoggingResponseMap(response);
		log.info("{}", value("req", requestMap), value("res", responseMap));
		MDC.clear();
		((ResponseWrapper) response).copyBodyToResponse();
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		AntPathMatcher pathMatcher = new AntPathMatcher();
		String targetPath = request.getServletPath();
		return pathMatcher.match("/favicon.ico",targetPath);
	}
}
