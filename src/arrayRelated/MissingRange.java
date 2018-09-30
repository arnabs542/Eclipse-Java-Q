package arrayRelated;

import java.util.LinkedList;
import java.util.List;

public class MissingRange {
	
	public List<String> findMissingRanges(int[] num, int lower, int upper) {
		List<String> result = new LinkedList<>();
		if (num[0] != lower) {
		  if (num[0] - lower > 2) {
		    String str = lower + "->" + (num[0] - 1);
		    result.add(str);
		  } else {
		    result.add(String.valueOf(lower));
		  }
		} 
		
		if (num[num.length - 1] != upper) {
		  if (upper - num[num.length - 1] > 2) {
		    String str = num[num.length - 1] + 1 + "->" + upper;
		    result.add(str);
		  } else {
			  	result.add(String.valueOf(upper));
		  }
		} 
		
		for (int i = 0; i < num.length - 1; i++) {
		  if (num[i + 1] - num[i] == 2) {
		    result.add(String.valueOf(num[i] + 1));
		  } else if (num[i + 1] - num[i] > 2) {
		    String str = num[i] + 1 + "->" + (num[i + 1] - 1);
		    result.add(str);
		  }
		}
		return result;
	}

	
}
