package codingdojo.parkingboy.common;

import java.util.HashSet;
import java.util.Set;

public class Sets {
	
	public static <T> Set<T> intersection(Set<T> s1, Set<T> s2) {
		Set<T> result = new HashSet<T>(s1);
		result.retainAll(s2);
		return result;
	}
}
