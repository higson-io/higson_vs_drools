package pl.decerto.hyperon;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.core.HyperonEngineFactory;
import pl.decerto.hyperon.runtime.sql.DialectRegistry;
import pl.decerto.hyperon.runtime.sql.DialectTemplate;

@Configuration
public class HyperonIntegrationConfiguration {
	@Bean
	HyperonEngineFactory hyperonEngineFactory(HikariDataSource dataSource) {
		HyperonEngineFactory hyperonEngineFactory = new HyperonEngineFactory();
		hyperonEngineFactory.setDataSource(dataSource);
		hyperonEngineFactory.setAutoStartWatchers(false);

		return hyperonEngineFactory;
	}

	@Bean HyperonEngine hyperonEngine(HyperonEngineFactory factory) {
		return factory.create();
	}

	@Bean(destroyMethod = "close")
	HikariDataSource getHyperonDataSource(DialectTemplate dialectTemplate,
		@Value("${hyperon.database.username:sa}") String userName,
		@Value("${hyperon.database.password:sa}") String password,
		@Value("${hyperon.database.url:h2}") String url) {

		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setJdbcUrl(url);
		dataSource.setDriverClassName(dialectTemplate.getJdbcDriverClassName());
		return dataSource;
	}

	@Bean
	DialectRegistry h2Registry(@Value("${hyperon.database.dialect}") String dialect) {
		DialectRegistry registry = new DialectRegistry();
		registry.setDialect(dialect);
		return registry;
	}

	@Bean
	DialectTemplate h2Dialect(DialectRegistry dialectRegistry) {
		return dialectRegistry.create();
	}

	@Bean
	HyperonCalculation hyperonCalculation(HyperonEngine hyperonEngine, @Value("${test.case:1}") int testCase) {
		return new HyperonCalculation(hyperonEngine, testCase);
	}
}
