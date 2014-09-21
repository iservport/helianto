package org.helianto.core.config;

import java.util.Properties;

import org.helianto.core.filter.FilterNamingConventionStrategy;
import org.helianto.core.naming.internal.DefaultNamingConventionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
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
	public FilterNamingConventionStrategy filterNamingConventionStrategy() {
		return new FilterNamingConventionStrategy();
	}
	
	/**
	 * Freemarker configuration factory.
	 */
	@Bean
	public FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactory() {
		FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactory = 
				new FreeMarkerConfigurationFactoryBean();
		freeMarkerConfigurationFactory.setPreferFileSystemAccess(false);
		freeMarkerConfigurationFactory.setTemplateLoaderPaths(
				new String[] {"classpath:freemarker/"
						,"/WEB-INF/classes/freemarker/"
						,"/WEB-INF/freemarker/"} );
		Properties settings = new Properties();
			settings.put("default_encoding", "ISO-8859-1");
			settings.put("number_format", "computer");
			settings.put("whitespace_stripping", "true");
		freeMarkerConfigurationFactory.setFreemarkerSettings(settings);
		return freeMarkerConfigurationFactory;
	}
	
	@Bean
	public RestTemplate restTamplate() {
		return new RestTemplate();
	}
	
}
