package pl.decerto.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import pl.decerto.jmh.drools.DroolsAverageTimeJmhBenchmark;
import pl.decerto.jmh.drools.DroolsThroughputJmhBenchmark;
import pl.decerto.jmh.hyperon.HyperonAverageTimeJmhBenchmark;
import pl.decerto.jmh.hyperon.HyperonThroughputJmhBenchmark;

/**
 * @author Maciej Główka on 19.09.2018
 */
public class MainRunner {
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
			.include(DroolsAverageTimeJmhBenchmark.class.getSimpleName())
			.include(DroolsThroughputJmhBenchmark.class.getSimpleName())
			.include(HyperonAverageTimeJmhBenchmark.class.getSimpleName())
			.include(HyperonThroughputJmhBenchmark.class.getSimpleName())
			.shouldFailOnError(true)
			.build();

		new Runner(opt).run();
	}
}
