package com.deltadental.pcp.search.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.deltadental.pcp.search.repos" , entityManagerFactoryRef = "serviceDBEntityManagerFactory", transactionManagerRef = "serviceDBTransactionManager")
@EntityScan(basePackages = { "com.deltadental.pcp.search.entities" })
@EnableJpaAuditing
@Slf4j
public class ServiceDBConfig {

	@Value("${spring.jpa.properties.hibernate.dialect:org.hibernate.dialect.SQLServer2012Dialect}")
	private String serviceDBDialect;
	
	@Primary
	@Bean(name = "serviceDBDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource serviceDBDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "serviceDBEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean serviceDBEntityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("serviceDBDataSource") DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		HashMap<String, Object> properties = new HashMap<>();     
		properties.put("hibernate.hbm2ddl.auto", "none");
		properties.put("hibernate.dialect", serviceDBDialect);
		properties.put("spring.jpa.show-sql", "true");
		properties.put("spring.jpa.properties.hibernate.format_sql", "true");
		properties.put("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation", "true");
		log.info("Service DB Dialect {} ", serviceDBDialect);  
		LocalContainerEntityManagerFactoryBean emf = builder.dataSource(dataSource).packages("com.deltadental.pcp.search.entities").persistenceUnit("serviceDBPU").build();
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setJpaPropertyMap(properties);
		return emf;
	}

	@Primary
	@Bean(name = "serviceDBTransactionManager")
	public PlatformTransactionManager serviceDBTransactionManager(final @Qualifier("serviceDBEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}

	@Bean(name = "serviceDBJdbcTemplate")
	public JdbcTemplate serviceDBJdbcTemplate(@Qualifier("serviceDBDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
