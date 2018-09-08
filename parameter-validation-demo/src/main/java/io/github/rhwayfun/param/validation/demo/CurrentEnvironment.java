package io.github.rhwayfun.param.validation.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
class CurrentEnvironment implements EnvironmentAware {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Environment environment;

	public Environment getEnvironment() {
		return environment;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		logger.info("CurrentEnvironment: {}", environment);
	}
}