package com.kbh.elk.app.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateAwareObjectMapper extends ObjectMapper {
	public HibernateAwareObjectMapper() {
		registerModule(new Hibernate5Module());
	}
}
