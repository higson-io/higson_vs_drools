package pl.decerto.jmh.hyperon;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

import org.apache.commons.dbcp2.BasicDataSource;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import pl.decerto.hyperon.HyperonCalculation;
import pl.decerto.hyperon.runtime.core.HyperonEngineFactory;

/**
 * @author Maciej Główka on 27.06.2019
 */
public class HyperonJmhBenchmark {

	@State(Scope.Benchmark)
	public static class HyperonTestState {
		HyperonCalculation hyperonCalculation;

		@Setup(Level.Trial)
		public void setup() {
			try {
				DriverManager.registerDriver((Driver) Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance());
				BasicDataSource ds = new BasicDataSource();
				ds.setUrl("jdbc:h2:./db/hyperon;AUTO_SERVER=TRUE;MVCC=TRUE;IFEXISTS=TRUE");
				ds.setUsername("sa");
				ds.setPassword("sa");

				HyperonEngineFactory factory = new HyperonEngineFactory();

				factory.setDataSource(ds);

				hyperonCalculation = new HyperonCalculation(factory.create(), 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Fork(2)
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.SECONDS)
	@Warmup(iterations = 10, time = 1)
	@Measurement(iterations = 20, time = 1)
	public void calculationTest(HyperonTestState state, Blackhole blackhole) {
		HyperonCalculation hyperonCalculation = state.hyperonCalculation;
		blackhole.consume(hyperonCalculation.runMassiveCalculations(100000));
	}
}
