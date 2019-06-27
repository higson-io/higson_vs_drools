package pl.decerto.drools;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Maciej Główka on 26.06.2019
 */
public class RateAdjustmentDto {
	private String product;
	private String cover;
	private int profession;
	private Integer policyYear;
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

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public int getProfession() {
		return profession;
	}

	public void setProfession(int profession) {
		this.profession = profession;
	}

	public Integer getPolicyYear() {
		return policyYear;
	}

	public void setPolicyYear(Integer policyYear) {
		this.policyYear = policyYear;
	}

	public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = BigDecimal.valueOf(factor).setScale(6, RoundingMode.HALF_UP);
	}
}
