package pl.decerto.jmh.drools;

import java.util.concurrent.TimeUnit;

import org.kie.api.runtime.KieSession;
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

import pl.decerto.drools.DroolsCalculation;

/**
 * @author Maciej Główka on 27.06.2019
 */
public class DroolsAverageTimeJmhBenchmark {

	@State(Scope.Benchmark)
	public static class DroolsTestState {
		DroolsCalculation droolsCalculation;

		@Setup(Level.Trial)
		public void setup() {
			try {
				KieSession session = ConfigurationUtils.createSession();
				droolsCalculation = new DroolsCalculation(session);
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
	public void calculationTest(DroolsTestState state, Blackhole blackhole) {
		DroolsCalculation droolsCalculation = state.droolsCalculation;
		blackhole.consume(droolsCalculation.runMassiveCalculations(1000));
	}
}
