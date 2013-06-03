package org.helianto.message.config;

import org.helianto.message.MessageSender;
import org.helianto.message.sender.TestingMailSender;
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
