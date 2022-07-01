package com.deltadental.pcp.search.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deltadental.platform.common.logger.LoggerFilter;

@Configuration
public class ServiceFilters {

	@Bean
	public FilterRegistrationBean<LoggerFilter> loggerFilterRegistrationBean() {
		FilterRegistrationBean<LoggerFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setName("Logger");
		registrationBean.setFilter(new LoggerFilter());
		registrationBean.setOrder(1);
		return registrationBean;
	}
}