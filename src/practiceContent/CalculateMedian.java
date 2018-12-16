package practiceContent;

import java.util.Arrays;

public class CalculateMedian {
	public double getMedian(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		Arrays.sort(array);
		if (n % 2 == 1) {
			return array[n / 2];
		} else {
			return (array[n / 2] + array[n / 2 - 1]) / 2.0;
		}
	}
}
