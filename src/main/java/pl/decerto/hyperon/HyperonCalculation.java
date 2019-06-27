package pl.decerto.hyperon;

import java.math.BigDecimal;

import org.smartparam.engine.core.output.ParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.decerto.algorithm.CalculationTemplate;
import pl.decerto.domain.FactorInput;
import pl.decerto.domain.QxProbabilityInput;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

@Service
public class HyperonCalculation extends CalculationTemplate {
	private final HyperonEngine engine;
	private final int testCase;

	@Autowired
	public HyperonCalculation(HyperonEngine engine, @Value("${calculations.number:100}") int calculationsNumber, @Value("${test.case:1}") int testCase) {
		super(calculationsNumber);
		this.engine = engine;
		this.testCase = testCase;
	}

	protected BigDecimal getFactor() {
		FactorInput input = FactorInput.random(testCase);

		HyperonContext context = new HyperonContext()
			.set("policy.productCode", input.getProduct())
			.set("cover.code", input.getCover())
			.set("policy.insured.professionCode", input.getProfessionCode())
			.set("policy.year", input.getYear());

		ParamValue multiValues = engine.get(testCase == 1 ? "acme.ul.rate_adjustment_no_asterisk" : "acme.ul.rate_adjustment", context);

		return multiValues.getBigDecimal();
	}

	protected BigDecimal getQxProbability() {
		QxProbabilityInput input = QxProbabilityInput.random();

		HyperonContext context = new HyperonContext()
			.set("policy.insured.gender", input.getGender())
			.set("policy.insured.age", input.getAge());

		ParamValue multiValues = engine.get("acme.ul.lifetable.qx", context);

		return multiValues.getBigDecimal();
	}
}
