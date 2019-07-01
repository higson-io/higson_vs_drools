package pl.decerto.hyperon;

import java.math.BigDecimal;

import org.smartparam.engine.core.output.ParamValue;

import pl.decerto.algorithm.Calculable;
import pl.decerto.algorithm.CalculationAlgorithm;
import pl.decerto.domain.FactorInput;
import pl.decerto.domain.QxProbabilityInput;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

public class HyperonCalculation implements Calculable {
	private final HyperonEngine engine;
	private final int testCase;

	public HyperonCalculation(HyperonEngine engine, int testCase) {
		this.testCase = testCase;
		this.engine = engine;
	}

	@Override public double runMassiveCalculations(int calculationsNumber) {
		long start = System.currentTimeMillis();

		for (int i = 0; i < calculationsNumber; i++) {
			BigDecimal qxProbability = getQxProbability();
			BigDecimal factor = getFactor();

			CalculationAlgorithm.calculate(qxProbability, factor);
		}

		long end = System.currentTimeMillis();

		return (end - start) / 1000d;
	}

	private BigDecimal getFactor() {
		FactorInput input = FactorInput.random();

		HyperonContext context = new HyperonContext()
			.set("policy.productCode", input.getProduct())
			.set("cover.code", input.getCover())
			.set("policy.insured.professionCode", input.getProfessionCode())
			.set("policy.year", input.getYear());

		ParamValue multiValues = engine.get(testCase == 1 ? "acme.ul.rate_adjustment_no_asterisk" : "acme.ul.rate_adjustment", context);

		return multiValues.getBigDecimal();
	}

	private BigDecimal getQxProbability() {
		QxProbabilityInput input = QxProbabilityInput.random();

		HyperonContext context = new HyperonContext()
			.set("policy.insured.gender", input.getGender())
			.set("policy.insured.age", input.getAge());

		ParamValue multiValues = engine.get("acme.ul.lifetable.qx", context);

		return multiValues.getBigDecimal();
	}
}
