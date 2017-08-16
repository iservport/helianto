package org.helianto.core.test;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Test configuration.
 * 
 * @author mauriciofernandesdecastro
 */
@Configuration
@ComponentScan(
		basePackages = {"org.helianto.*.repository", "org.helianto.*.service"})
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages="org.helianto.*.repository")
public class TestDataSourceConfig {

	@Autowired 
	private Environment env;

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("messages");
		return source;
	}

//	@Bean
//	public DefaultNamingConventionStrategy defaultNamingConventionStrategy() {
//		return new DefaultNamingConventionStrategy();
//	}

	@Bean
	public RestTemplate restTamplate() {
		return new RestTemplate();
	}

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
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan(env.getProperty("helianto.entityManager.packagesToScan", "org.helianto.*.domain").split(","));
		bean.setJpaVendorAdapter(vendorAdapter());
		bean.setPersistenceProvider(new HibernatePersistence());
		bean.afterPropertiesSet();
		return bean.getObject();
	}

	@Bean
	public HibernateJpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
//		vendor.setShowSql(env.getProperty("helianto.sql.showSql", Boolean.class, Boolean.TRUE));
		vendor.setGenerateDdl(env.getProperty("helianto.sql.generateDdl", Boolean.class, Boolean.TRUE));
//		vendor.setDatabasePlatform(env.getProperty("helianto.jpa.vendor", String.class, "org.hibernate.dialect.HSQLDialect"));
		return vendor;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory());
		return transactionManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

}
