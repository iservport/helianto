package org.helianto.classic.config;

import org.helianto.core.MessageSender;
import org.helianto.core.test.TestingMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageTestConfig {

	@Bean
	public MessageSender messageSender() {
		return new TestingMailSender();
	}
}
