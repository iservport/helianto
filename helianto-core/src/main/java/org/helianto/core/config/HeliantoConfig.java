package org.helianto.core.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.helianto.core.data.FilterRepositoryFactoryBean;
import org.helianto.core.domain.IdentitySecurityConverter;
import org.helianto.core.filter.FilterNamingConventionStrategy;
import org.helianto.core.naming.internal.DefaultNamingConventionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Persistence and transaction definition.
 * 
 * @author mauriciofernandesdecastro
 */
@Configuration
@ComponentScan(
		basePackages = {"org.helianto.*.repository", "org.helianto.*.service"})
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages="org.helianto.*.repository"
		, repositoryFactoryBeanClass=FilterRepositoryFactoryBean.class)
@EnableScheduling
@ImportResource("classpath:META-INF/spring/security.xml")
@PropertySource("classpath:/META-INF/helianto.properties")
public class HeliantoConfig {

	@Autowired 
	private Environment env;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer propertySources = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[] { 
				  new ClassPathResource("META-INF/jdbc.properties")
				, new ClassPathResource("META-INF/helianto.properties") };
		propertySources.setLocations(resources);
		propertySources.setIgnoreUnresolvablePlaceholders(true);
		propertySources.setIgnoreResourceNotFound(true);
		return propertySources;
	}
	
	/**
	 * Data source.
	 */
	@Bean
	public DataSource dataSource() {
		try {
			ComboPooledDataSource ds = new ComboPooledDataSource();
			ds.setDriverClass(env.getProperty("helianto.jdbc.driverClassName", "org.hsqldb.jdbcDriver"));
			ds.setJdbcUrl(env.getProperty("helianto.jdbc.url", "jdbc:hsqldb:file:target/testdb/db2;shutdown=true"));
			ds.setUser(env.getProperty("helianto.jdbc.username", "sa"));
			ds.setPassword(env.getProperty("helianto.jdbc.password", ""));
			ds.setAcquireIncrement(5);
			ds.setIdleConnectionTestPeriod(60);
			ds.setMaxPoolSize(100);
			ds.setMaxStatements(50);
			ds.setMinPoolSize(10);
			return ds;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Bean 
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(env.getProperty("helianto.entityManager.packagesToScan", "org.helianto.*.domain").split(","));
		em.setJpaVendorAdapter(vendorAdapter());
		return em;
	}
	
	@Bean
	public HibernateJpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
		vendor.setShowSql(env.getProperty("helianto.sql.showSql", Boolean.class, Boolean.TRUE));
		vendor.setGenerateDdl(env.getProperty("helianto.sql.generateDdl", Boolean.class, Boolean.TRUE));
		vendor.setDatabasePlatform(env.getProperty("helianto.jpa.vendor", String.class, "org.hibernate.dialect.HSQLDialect"));
		return vendor;
	}
	
	@Bean 
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public IdentitySecurityConverter identitySecurityConverter() {
		return new IdentitySecurityConverter(passwordEncoder());
	}

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
