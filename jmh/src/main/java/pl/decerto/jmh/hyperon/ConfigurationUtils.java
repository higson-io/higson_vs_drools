package pl.decerto.jmh.hyperon;

import java.sql.Driver;
import java.sql.DriverManager;

import org.apache.commons.dbcp2.BasicDataSource;

import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.core.HyperonEngineFactory;

/**
 * @author Maciej Główka on 01.07.2019
 */
class ConfigurationUtils {
	private ConfigurationUtils() {
	}

	static HyperonEngine createEngine() throws Exception {
		DriverManager.registerDriver((Driver) Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance());
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:h2:./db/hyperon;AUTO_SERVER=TRUE;MVCC=TRUE;IFEXISTS=TRUE");
		ds.setUsername("sa");
		ds.setPassword("sa");

		HyperonEngineFactory factory = new HyperonEngineFactory();

		factory.setDataSource(ds);

		return factory.create();
	}
}
