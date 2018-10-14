package myMain;

import java.util.Scanner;

public class TecentProblem1 {
	
	public static int findOneNum(int k, String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++) {		
			for (int size = 1; size <= s.length() - i; size++) {
				int numOf1 = 0;
				for (int j = i; j - i < size; j++) {
					if (s.charAt(j) == '1') {
						numOf1++;
					}
				}
				if (numOf1 == k) {
					res++;
				}
			}
		}
		return res;
	}
	
    public static void main(String[] args) {      
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        //String s = Integer.toString(sc.nextInt()); // damn... didn't find this problem in the test
        String s = sc.next();
        int res = findOneNum(k, s);
        System.out.println(res);
    }
}
