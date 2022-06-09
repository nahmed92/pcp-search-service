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
@EntityScan(basePackages = { "com.deltadental.pcp.search.entities" })
@EnableJpaRepositories(basePackages = "com.deltadental.pcp.search.pd.repos", 
	    entityManagerFactoryRef = "a3EntityManagerFactory", 
	    transactionManagerRef = "a3TransactionManager")
@Slf4j
public class A3DBConfig {

	@Value("${a3.datasource.hibernate.dialect:org.hibernate.dialect.Oracle12cDialect}")
	private String a3DBDialect;
	
	@Bean(name = "a3DataSource")
	@ConfigurationProperties(prefix = "a3.datasource")
	public DataSource a3DataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "a3EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean a3EntityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("a3DataSource") DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		HashMap<String, Object> properties = new HashMap<>();     
		properties.put("hibernate.hbm2ddl.auto", "none");
		properties.put("hibernate.dialect", a3DBDialect);
		properties.put("spring.jpa.show-sql", "true");
		properties.put("spring.jpa.properties.hibernate.format_sql", "true");
		properties.put("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation", "true");
		log.info("A3 DB Dialect {} ", a3DBDialect);  
		LocalContainerEntityManagerFactoryBean emf = builder.dataSource(dataSource).packages("com.deltadental.pcp.search.pd.entities").persistenceUnit("a3PU").build();
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setJpaPropertyMap(properties);
		return emf;
	}

	@Bean(name = "a3TransactionManager")
	public PlatformTransactionManager a3TransactionManager(final @Qualifier("a3EntityManagerFactory") LocalContainerEntityManagerFactoryBean a3EntityManagerFactoryBean) {
		return new JpaTransactionManager(a3EntityManagerFactoryBean.getObject());
	}
	
	@Bean(name = "a3JdbcTemplate")
	public JdbcTemplate a3JdbcTemplate(@Qualifier("a3DataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
