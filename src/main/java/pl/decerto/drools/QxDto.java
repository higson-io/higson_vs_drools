package pl.decerto.drools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author Maciej Główka on 25.06.2019
 */
public class QxDto {
	private final String gender;
	private final int age;
	private BigDecimal qx;

	public QxDto(String gender, int age) {
		this.gender = gender;
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public BigDecimal getQx() {
		return qx;
	}

	public void setQx(double qx) {
		this.qx = BigDecimal.valueOf(qx).setScale(6, RoundingMode.HALF_UP);
	}

	@Override public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		QxDto qxDto = (QxDto) o;
		return age == qxDto.age &&
			Objects.equals(gender, qxDto.gender) &&
			Objects.equals(qx, qxDto.qx);
	}

	@Override public int hashCode() {
		return Objects.hash(gender, age, qx);
	}
}
