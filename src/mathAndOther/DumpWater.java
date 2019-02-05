/*
 * == Created Date ==
 * Jan 24, 2019
 * 
 * == Question - Pour Water ==
 *   
 * == Smilar Question == 
 * LeetCode 755* (M), pour water without printing and border issue
 * 
 * Airbnb onsite problem
 * A lot of cases should be disscussed with interviewer and 
 * considered before writing code
 * 
 */

package mathAndOther;

public class DumpWater {
	
	public static void print(int[] terrain, int[] waters) {
		// find the highest height
		int maxHeight = 0;
		for (int i = 0; i < terrain.length; i++) {
			maxHeight = Math.max(terrain[i] + waters[i], maxHeight);
		}
		
		while (maxHeight > 0) {
			for (int i = 1; i < terrain.length; i++) {
				if (terrain[i] >= maxHeight) {
					System.out.print('+');
				} else if (terrain[i]+ waters[i] >= maxHeight){
					System.out.print('@');
				} else {
					System.out.print(' ');
				}
			}
			System.out.println();
			maxHeight--;
		}
		System.out.println();
	}
	
	public static void DumpingWater(int[] terrain, int waterAmount, int dumpLocation) {
		
		int[] waters = new int[terrain.length];
		int leftM = 0;
		
		while (waterAmount > 0) {
			print(terrain, waters);
			
			int positionToFall = dumpLocation;
			// Try to find a location in the left side
			for (int temp = positionToFall - 1; temp >= 0; temp--) {
				int tempH = terrain[temp] + waters[temp];
				int fallH = terrain[positionToFall] + waters[positionToFall];
				
				leftM = Math.max(leftM, tempH);
				
				if (tempH > fallH) { 
					break;
				} else { 
					// this is diffent than find dumping place in the right side
					// if one place has the same hight as the current terrain, the water will fall to left
					positionToFall = temp;
				} 
			}
			
			// Case1: if the candidate position is not out of border(index 0), 
			//        and the index doesn't stay at dumpLocation, we find a location in the left side
			if (positionToFall != 0 && positionToFall != dumpLocation) {
				waters[positionToFall]++;
				waterAmount--;
				continue;
			}
			
			// Try to find a location in the right side
			positionToFall = dumpLocation;
			for (int temp = positionToFall + 1; temp < terrain.length; temp++) {
				int tempH = terrain[temp] + waters[temp];
				int fallH = terrain[positionToFall] + waters[positionToFall];
				
				if (tempH >= fallH) {
					// if one terrain in the right has the same hight as the current terrain, 
					// the water will not fall to right, so when tempH >= fallH, should break 
					break;
				} else if (tempH < fallH) {
					positionToFall = temp;
				}
			}
			
			// Case2: find a location in the right side
			if (positionToFall != dumpLocation) {
				waters[positionToFall]++;
				waterAmount--;
				continue;
			}
			
			// Case3: if cannot find a place to fall in both side,
			//        and the height of the dumping location is higher than the border of left,
			//        over flow
			if (terrain[dumpLocation] + waters[dumpLocation] >= leftM) {
				waterAmount--;
				continue;
			}
			
			// Case4: water increase in the dumpLocation
			waters[dumpLocation]++;
			waterAmount--;
		}
		print(terrain, waters);
	}
	
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		int[] terrain = new int[] {-1, 5, 4, 2, 1, 2, 3, 2, 1, 0, 1, 2, 4};
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		DumpingWater(terrain, 15, 10);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int[] terrain1 = new int[] {-1, 1, 2, 3};
		DumpingWater(terrain1, 3, 2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		int[] terrain2 = new int[] {-1, 3, 2, 1};
		DumpingWater(terrain2, 5, 2);
		
		System.out.println("Finished");
	}
}
