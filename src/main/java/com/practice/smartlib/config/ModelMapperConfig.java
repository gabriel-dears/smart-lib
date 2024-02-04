package com.practice.smartlib.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
}
