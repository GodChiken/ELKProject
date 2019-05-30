package com.kbh.elk.app.config.logback;

import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.decorate.JsonFactoryDecorator;

import java.text.SimpleDateFormat;

public class DateDecorator implements JsonFactoryDecorator {
	@Override
	public MappingJsonFactory decorate(MappingJsonFactory mappingJsonFactory) {
		ObjectMapper codec = mappingJsonFactory.getCodec();
		codec.setDateFormat(new SimpleDateFormat());
		return mappingJsonFactory;
	}
}