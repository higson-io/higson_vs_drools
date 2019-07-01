package pl.decerto.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import pl.decerto.jmh.drools.DroolsJmhBenchmark;
import pl.decerto.jmh.hyperon.HyperonJmhBenchmark;

/**
 * @author Maciej Główka on 19.09.2018
 */
public class MainRunner {
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
			.include(DroolsJmhBenchmark.class.getSimpleName())
			.include(HyperonJmhBenchmark.class.getSimpleName())
			.shouldFailOnError(true)
			.build();

		new Runner(opt).run();
	}
}
