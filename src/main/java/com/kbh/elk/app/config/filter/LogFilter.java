package com.kbh.elk.app.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class LogFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		requestConfigForMDC(request);
		chain.doFilter(request, response);
		responseConfigForMDC(response);
		MDC.clear();
	}

	private void requestConfigForMDC(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		MDC.put("UUID", Thread.currentThread().getName());
		MDC.put("req", String.valueOf(req));
		MDC.put("requestUri", req.getRequestURI());
		MDC.put("method", req.getMethod());
		MDC.put("reqRes", " Request");
		log.info("{}", "Request");
	}

	private void responseConfigForMDC(ServletResponse response) {
		HttpServletResponse res = (HttpServletResponse) response;

		MDC.put("reqRes", " Response");
		MDC.put("status", String.valueOf(res.getStatus()));
		log.info("{}", "Response");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
