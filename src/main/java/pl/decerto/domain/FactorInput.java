package pl.decerto.domain;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

public class FactorInput {

	private static final List<String> PRODUCTS = List.of("ULA", "ULP", "ULS", "MISSING_IDENTIFIED");
	private static final List<String> COVERS = List.of("COV1", "COV2", "COV3", "COV4", "MISSING_COV");

	private final String product;
	private final String cover;
	private final int professionCode;
	private final int year;

	private FactorInput(String product, String cover, int professionCode, int year) {
		this.product = product;
		this.cover = cover;
		this.professionCode = professionCode;
		this.year = year;
	}

	public static FactorInput random() {
		return new FactorInput(
			RandomListUtil.getRandomStringFor(PRODUCTS),
			RandomListUtil.getRandomStringFor(COVERS),
			RandomUtils.nextInt(0, 301),
			RandomUtils.nextInt(1, 5));
	}

	public String getProduct() {
		return product;
	}

	public String getCover() {
		return cover;
	}

	public int getProfessionCode() {
		return professionCode;
	}

	public int getYear() {
		return year;
	}
}
