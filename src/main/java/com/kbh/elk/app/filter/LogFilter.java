package com.kbh.elk.app.filter;

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

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		MDC.put("requestUri", ((HttpServletRequest) request).getRequestURI());
		MDC.put("method", req.getMethod());
		MDC.put("reqRes", " Request");
		log.info("{}", "Request");

		chain.doFilter(request, response);

		// Response
		MDC.put("reqRes", " Response");
		MDC.put("status", String.valueOf(res.getStatus())); // Req or Res
		log.info("{}", "Response");

		MDC.clear();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
