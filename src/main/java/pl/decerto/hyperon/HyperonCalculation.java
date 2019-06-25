package pl.decerto.hyperon;

import java.math.BigDecimal;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartparam.engine.core.output.ParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.decerto.domain.FactorInput;
import pl.decerto.domain.QxProbabilityInput;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

@Service
public class HyperonCalculation {

	private static final Logger log = LoggerFactory.getLogger(HyperonCalculation.class);

	private final HyperonEngine engine;
	private final int calculationsNumber;

	@Autowired
	public HyperonCalculation(HyperonEngine engine, @Value("${calculations.number:100}") int calculationsNumber) {
		this.engine = engine;
		this.calculationsNumber = calculationsNumber;
	}

	public void massiveCalculations() {

		StopWatch stopWatch = new StopWatch();

		log.info("Start massive calculations");
		stopWatch.start();
		for (int i = 0; i < calculationsNumber; i++) {
			BigDecimal qxProbability = getQxProbability();
			BigDecimal factor = getFactor();

			BigDecimal result = qxProbability.multiply(factor);

			log.info("qxProbability: {}, factor: {}, result: {}", qxProbability, factor, result);
		}
		stopWatch.stop();
		log.info("End massive calculations with: {}", stopWatch);
	}

	private BigDecimal getFactor() {

		FactorInput input = FactorInput.random();

		HyperonContext context = new HyperonContext()
			.set("policy.productCode", input.getProduct())
			.set("cover.code", input.getCover())
			.set("policy.insured.professionCode", input.getProfessionCode())
			.set("policy.year", input.getYear());

		ParamValue multiValues = engine.get("acme.ul.rate_adjustment", context);
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
