package com.kbh.elk.app.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomFilterConfig {
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		LoggingFilter loggingFilter = new LoggingFilter();
		filterRegistrationBean.setFilter(loggingFilter);
		filterRegistrationBean.setOrder(2);
		return filterRegistrationBean;
	}
}
