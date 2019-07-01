package pl.decerto.algorithm;

/**
 * @author Maciej Główka on 27.06.2019
 */
public interface Calculable {
	double runMassiveCalculations(int calculationsNumber);

	default double runMassiveCalculations() {
		return runMassiveCalculations(1);
	}

//	public double runMassiveCalculations(int calculationsNumber) {
//		long start = System.currentTimeMillis();
//
//		for (int i = 0; i < calculationsNumber; i++) {
//			BigDecimal qxProbability = getQxProbability();
//			BigDecimal factor = getFactor();
//
//			CalculationAlgorithm.calculate(qxProbability, factor);
//		}
//
//		long end = System.currentTimeMillis();
//
//		return (end - start) / 1000d;
//	}
}
