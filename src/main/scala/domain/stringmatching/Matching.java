package domain.stringmatching;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Matching {
	
	private String pattern;
	
	private List<Integer> validShifts;
	
	public Matching(String pattern, List<Integer> validShifts) {
		this.pattern = pattern;
		this.validShifts = validShifts;
	}
	
	public Collection<Integer> getValidShifts() {
		return Collections.unmodifiableCollection(validShifts);
	}
	
	public String getPattern() {
		return pattern;
	}

}
