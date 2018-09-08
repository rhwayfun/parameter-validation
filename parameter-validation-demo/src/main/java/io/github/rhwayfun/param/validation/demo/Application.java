package io.github.rhwayfun.param.validation.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {

	static {
		System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
	}

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Application.class).profiles("default")
				.build(args).run(args);
		Thread.sleep(Long.MAX_VALUE);
	}
}
