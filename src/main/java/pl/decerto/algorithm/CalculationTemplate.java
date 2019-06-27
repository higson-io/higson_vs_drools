package pl.decerto.algorithm;

import java.math.BigDecimal;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maciej Główka on 27.06.2019
 */
public abstract class CalculationTemplate {
	protected Logger log = LoggerFactory.getLogger(getClass());

	private final int calculationsNumber;

	protected CalculationTemplate(int calculationsNumber) {
		this.calculationsNumber = calculationsNumber;
	}

	public void runMassiveCalculations() {
		StopWatch stopWatch = new StopWatch();
		log.info("Start massive calculations");
		stopWatch.start();

		for (int i = 0; i < calculationsNumber; i++) {
			BigDecimal qxProbability = getQxProbability();
			BigDecimal factor = getFactor();

			CalculationAlgorithm.calculate(qxProbability, factor);
		}
		stopWatch.stop();
		log.info("End massive calculations with: {}", stopWatch);
	}

	protected abstract BigDecimal getFactor();

	protected abstract BigDecimal getQxProbability();
}
