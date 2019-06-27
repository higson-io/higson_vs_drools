package pl.decerto.algorithm;

import java.math.BigDecimal;

/**
 * @author Maciej Główka on 25.06.2019
 */
public class CalculationAlgorithm {

	public static BigDecimal calculate(BigDecimal qx, BigDecimal factor) {
		return qx.multiply(factor);
	}
}
