package pl.decerto.jmh.hyperon;

import java.util.concurrent.TimeUnit;

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
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * @author Maciej Główka on 27.06.2019
 */
public class HyperonAverageTimeJmhBenchmark {

	@State(Scope.Benchmark)
	public static class HyperonTestState {
		HyperonCalculation hyperonCalculation;

		@Setup(Level.Trial)
		public void setup() {
			try {
				HyperonEngine engine = ConfigurationUtils.createEngine();
				hyperonCalculation = new HyperonCalculation(engine, 2);
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
		blackhole.consume(hyperonCalculation.runMassiveCalculations(1000));
	}
}
