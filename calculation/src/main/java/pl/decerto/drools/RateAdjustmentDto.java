package pl.decerto.drools;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Maciej Główka on 26.06.2019
 */
public class RateAdjustmentDto {
	private final String product;
	private final String cover;
	private final int profession;
	private final Integer policyYear;
	private BigDecimal factor;

	public RateAdjustmentDto(String product, String cover, int profession, Integer year) {
		this.product = product;
		this.cover = cover;
		this.profession = profession;
		this.policyYear = year;
	}

	public String getProduct() {
		return product;
	}

	public String getCover() {
		return cover;
	}

	public int getProfession() {
		return profession;
	}

	public Integer getPolicyYear() {
		return policyYear;
	}

	public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = BigDecimal.valueOf(factor).setScale(6, RoundingMode.HALF_UP);
	}
}
