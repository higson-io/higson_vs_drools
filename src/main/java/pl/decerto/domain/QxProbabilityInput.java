package pl.decerto.domain;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

public class QxProbabilityInput {

	private static final List<String> GENDERS = List.of("M", "F");

	private final String gender;
	private final int age;

	private QxProbabilityInput(String gender, int age) {
		this.gender = gender;
		this.age = age;
	}

	public static QxProbabilityInput random() {
		return new QxProbabilityInput(RandomListUtil.getRandomStringFor(GENDERS), RandomUtils.nextInt(0, 10));
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}
}
