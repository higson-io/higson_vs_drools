package pl.decerto.drools;

import java.math.BigDecimal;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.decerto.algorithm.CalculationTemplate;
import pl.decerto.domain.FactorInput;
import pl.decerto.domain.QxProbabilityInput;

/**
 * @author Maciej Główka on 26.06.2019
 */
@Service
public class DroolsCalculation extends CalculationTemplate {
	private KieSession session;
	private int testCase;

	@Autowired
	public DroolsCalculation(KieSession session, @Value("${calculations.number:100}") int calculationsNumber, @Value("${test.case:1}") int testCase) {
		super(calculationsNumber);
		this.session = session;
		this.testCase = testCase;
	}

	protected BigDecimal getFactor() {
		FactorInput input = FactorInput.random(testCase);

		RateAdjustmentDto ra = new RateAdjustmentDto(input.getProduct(), input.getCover(), input.getProfessionCode(), input.getYear());

		session.insert(ra);
		session.fireAllRules();

		return ra.getFactor();
	}

	protected BigDecimal getQxProbability() {
		QxProbabilityInput input = QxProbabilityInput.random();

		QxDto dto = new QxDto(input.getGender(), input.getAge());

		session.insert(dto);
		session.fireAllRules();

		return dto.getQx();
	}
}
