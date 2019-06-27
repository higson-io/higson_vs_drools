package pl.decerto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import pl.decerto.drools.DroolsCalculation;
import pl.decerto.hyperon.HyperonCalculation;

/**
 * @author Maciej Główka on 27.06.2019
 */
@Component
@Profile("!jmh")
public class CalculationLineRunner implements CommandLineRunner {
	private final HyperonCalculation hyperonCalculation;
	private final DroolsCalculation droolsCalculation;

	public CalculationLineRunner(HyperonCalculation hyperonCalculation, DroolsCalculation droolsCalculation) {
		this.hyperonCalculation = hyperonCalculation;
		this.droolsCalculation = droolsCalculation;
	}

	@Override public void run(String... args) {
		hyperonCalculation.runMassiveCalculations();

		droolsCalculation.runMassiveCalculations();
	}
}
