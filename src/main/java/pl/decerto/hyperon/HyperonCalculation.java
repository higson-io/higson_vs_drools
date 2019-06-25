package pl.decerto.hyperon;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartparam.engine.core.output.ParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

@Service
public class HyperonCalculation {

	private static final Logger LOG = LoggerFactory.getLogger(HyperonCalculation.class);

	private static final List<String> GENDERS = List.of("M", "F");
	private static final List<String> PRODUCTS = List.of("ULA", "ULP", "ULS", "MISSING_IDENTIFIED");
	private static final List<String> COVERS = List.of("COV1", "COV2", "COV3", "COV4", "MISSING_COV");

	private final HyperonEngine engine;
	private final int calculationsNumber;

	@Autowired
	public HyperonCalculation(HyperonEngine engine, @Value("${calculations.number:100}") int calculationsNumber) {
		this.engine = engine;
		this.calculationsNumber = calculationsNumber;
	}

	public void massiveCalculations() {

		StopWatch stopWatch = new StopWatch();

		LOG.info("Start massive calculations");
		stopWatch.start();
		for (int i = 0; i < calculationsNumber; i++) {
			BigDecimal qxProbability = getQxProbability();
			BigDecimal factor = getFactor();

			BigDecimal result = qxProbability.multiply(factor);

			LOG.info("qxProbability: {}, factor: {}, result: {}", qxProbability, factor, result);
		}
		stopWatch.stop();
		LOG.info("End massive calculations with: {}", stopWatch);
	}

	private String getRandomStringFor(List<String> list) {
		return list.get(RandomUtils.nextInt(0, list.size()));
	}

	private BigDecimal getFactor() {
		String randomProduct = getRandomStringFor(PRODUCTS);
		String randomCover = getRandomStringFor(COVERS);
		int randomProfessionCode = RandomUtils.nextInt(0, 301);
		int randomYear = RandomUtils.nextInt(1, 5);

		HyperonContext context = new HyperonContext()
			.set("policy.productCode", randomProduct)
			.set("cover.code", randomCover)
			.set("policy.insured.professionCode", randomProfessionCode)
			.set("policy.year", randomYear);

		ParamValue multiValues = engine.get("acme.ul.rate_adjustment", context);
		return multiValues.getBigDecimal();
	}

	private BigDecimal getQxProbability() {
		String randomGender = getRandomStringFor(GENDERS);
		int randomAge = RandomUtils.nextInt(0, 10);

		HyperonContext context = new HyperonContext()
			.set("policy.insured.gender", randomGender)
			.set("policy.insured.age", randomAge);

		ParamValue multiValues = engine.get("acme.ul.lifetable.qx", context);
		return multiValues.getBigDecimal();
	}

}
