package pl.decerto.drools;

import java.math.BigDecimal;

import org.kie.api.runtime.KieSession;

import pl.decerto.algorithm.Calculable;
import pl.decerto.algorithm.CalculationAlgorithm;
import pl.decerto.domain.FactorInput;
import pl.decerto.domain.QxProbabilityInput;

/**
 * @author Maciej Główka on 26.06.2019
 */
public class DroolsCalculation implements Calculable {
	private final KieSession session;

	public DroolsCalculation(KieSession session) {
		this.session = session;
	}

	@Override
	public double runMassiveCalculations(int calculationsNumber) {
		long start = System.currentTimeMillis();

		for (int i = 0; i < calculationsNumber; i++) {
			RateAdjustmentDto ra = createRateAdjustmentDto();
			QxDto qx = createQxDto();

			session.insert(ra);
			session.insert(qx);
			session.fireAllRules();

			BigDecimal qxProbability = qx.getQx();
			BigDecimal factor = ra.getFactor();

			CalculationAlgorithm.calculate(qxProbability, factor);
		}

		long end = System.currentTimeMillis();

		return (end - start) / 1000d;
	}

	private RateAdjustmentDto createRateAdjustmentDto() {
		FactorInput raInput = FactorInput.random();
		return new RateAdjustmentDto(raInput.getProduct(), raInput.getCover(), raInput.getProfessionCode(), raInput.getYear());
	}

	private QxDto createQxDto() {
		QxProbabilityInput qxInput = QxProbabilityInput.random();
		return new QxDto(qxInput.getGender(), qxInput.getAge());
	}
}
