package myMain;

public class Solution {
	
	public static void print(int[] terrain, int[] waters) {
		// find the highest height
		int maxHeight = 0;
		for (int i = 0; i < terrain.length; i++) {
			maxHeight = Math.max(terrain[i] + waters[i], maxHeight);
		}
		
		while (maxHeight >= 0) {
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
	
	public static void DumpWater(int[] terrain, int waterAmount, int dumpLocation) {
		
		int[] waters = new int[terrain.length];
		int leftM = 0;
		
		while (waterAmount > 0) {
			int positionToFall = dumpLocation;
			
			print(terrain, waters);
			// Try to find a location in the left side
			for (int temp = positionToFall - 1; temp >= 0; temp--) {
				leftM = Math.max(leftM, terrain[temp] + waters[temp]);
				if (terrain[temp] + waters[temp] > terrain[positionToFall] + waters[positionToFall]) {
					break;
				} else if (terrain[temp] + waters[temp] < leftM) { 
					positionToFall = temp;
				}
			}
			
			// Case1: find a location in the left side
			if (positionToFall != 0 && positionToFall != dumpLocation) {
				waters[positionToFall]++;
				waterAmount--;
				continue;
			}
			
			// Try to find a location in the right side
			positionToFall = dumpLocation;
			for (int temp = positionToFall + 1; temp < terrain.length; temp++) {
				if (terrain[temp] + waters[temp] > terrain[positionToFall] + waters[positionToFall]) {
					break;
				} else if (terrain[temp] + waters[temp] < terrain[positionToFall] + waters[positionToFall]) {
					positionToFall = temp;
				}
			}
			
			// Case2: find a location in the RIGHT side
			if (positionToFall != dumpLocation) {
				waters[positionToFall]++;
				waterAmount--;
				continue;
			}
			
			// Case3: over flow
			if (terrain[positionToFall] + waters[positionToFall] > leftM) {
				waterAmount--;
				continue;
			}
			
			waters[positionToFall]++;
			waterAmount--;
		}
		print(terrain, waters);
	}
	
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		int[] terrain = new int[] {-1, 5, 4, 2, 1, 2, 3, 2, 1, 0, 1, 2, 4};
//		int[] waters = new int[] {0, 0, 0, 0, 0, 0, 1, 2, 3, 2, 0, 0};
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		DumpWater(terrain, 15, 10);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int[] terrain1 = new int[] {-1, 1, 2, 3};
		DumpWater(terrain1, 5, 2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		int[] terrain2 = new int[] {-1, 3, 2, 1};
		DumpWater(terrain2, 5, 2);
	}
}
