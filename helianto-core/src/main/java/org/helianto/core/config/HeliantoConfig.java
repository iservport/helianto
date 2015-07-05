package org.helianto.core.config;

import org.helianto.core.naming.internal.DefaultNamingConventionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


/**
 * Core definition.
 * 
 * @author mauriciofernandesdecastro
 */
@Configuration
@EnableScheduling
public class HeliantoConfig {

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("messages");
		return source;
	}
	
	@Bean
	public DefaultNamingConventionStrategy defaultNamingConventionStrategy() {
		return new DefaultNamingConventionStrategy();
	}
	
	@Bean
	public RestTemplate restTamplate() {
		return new RestTemplate();
	}
	
}
