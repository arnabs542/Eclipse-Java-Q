package myMain;

import java.util.Scanner;

public class TecentProblem2 {
	
	public static int findOneNum(int[] arr) {
		for (int size = arr.length; size >= 1; size--) {
			for (int i = 0; i < arr.length; i++) {
				int v = 2;				
				while (true) {
					int num = 0;
					int max = Integer.MIN_VALUE;
					for (int j = i; j <= size; j++) {
						max = Math.max(max, arr[j]); 
						if (arr[j] % v == 0) {
							num++;
							if (num >= size / 2) {
								return size;
							}
						}
					}
					v++;
					if (v > max) {
						break;
					}
				}
			}
		}
		return 0;
	}
	
    public static void main(String[] args) {      
        Scanner sc = new Scanner(System.in);
        
        int[] arr = new int[sc.nextInt()];
        for (int i = 0; i < arr.length; i++) {
        		arr[i] = sc.nextInt();
        }
        int res = findOneNum(arr);
        System.out.println(res);
    }  
}
