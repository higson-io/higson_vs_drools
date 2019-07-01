package pl.decerto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.decerto.drools.DroolsCalculation;
import pl.decerto.hyperon.HyperonCalculation;

/**
 * @author Maciej Główka on 27.06.2019
 */
@Component
public class CalculationLineRunner implements CommandLineRunner {
	private static Logger log = LoggerFactory.getLogger(CalculationLineRunner.class);

	private final HyperonCalculation hyperonCalculation;
	private final DroolsCalculation droolsCalculation;
	private final int calculationsNumber;

	@Autowired
	public CalculationLineRunner(HyperonCalculation hyperonCalculation, DroolsCalculation droolsCalculation,
		@Value("${calculations.number:100}") int calculationsNumber) {
		this.hyperonCalculation = hyperonCalculation;
		this.droolsCalculation = droolsCalculation;
		this.calculationsNumber = calculationsNumber;
	}

	@Override public void run(String... args) {
		log.info("starting hyperon calculations");
		double hyperonTime = hyperonCalculation.runMassiveCalculations(calculationsNumber);
		log.info("hyperon calculations ended, time:{} seconds", hyperonTime);

		log.info("starting drools calculations");
		double droolsTime = droolsCalculation.runMassiveCalculations(calculationsNumber);
		log.info("drools calculations ended, time:{} seconds", droolsTime);
	}
}
