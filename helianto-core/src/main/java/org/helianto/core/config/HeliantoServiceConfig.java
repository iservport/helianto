package org.helianto.core.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


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
		basePackages="org.helianto.*.repository")
@Import(HeliantoConfig.class)
public class HeliantoServiceConfig {

	@Autowired 
	private Environment env;
	
	@Autowired 
	private DataSource dataSource;
	
	@Bean 
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
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
