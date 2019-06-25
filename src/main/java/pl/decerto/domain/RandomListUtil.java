package pl.decerto.domain;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

class RandomListUtil {

	private RandomListUtil() {
		throw new UnsupportedOperationException();
	}

	static String getRandomStringFor(List<String> list) {
		return list.get(RandomUtils.nextInt(0, list.size()));
	}

}
