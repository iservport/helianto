package org.helianto.message.test;

import org.helianto.core.MessageSender;
import org.helianto.core.test.TestingMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author mauriciofernandesdecastro
 */
@Configuration
public class MessageTestConfig {
	
	@Bean
	public MessageSender messageSender() {
		return new TestingMailSender();
	}

}
