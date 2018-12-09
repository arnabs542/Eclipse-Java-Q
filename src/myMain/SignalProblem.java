package myMain;

import java.util.Scanner;

public class SignalProblem {
	
	private static int min = Integer.MAX_VALUE;
	public static int findSignal(int[] signals, int[] prices) {
		for (int i = 0 ; i < signals.length; i++) {
			dfs(signals, prices, i, 3, 0);
		}	
		return min != Integer.MAX_VALUE ? min : -1;
	}
	
	private static void dfs(int[] signals, int[] prices, int index, int numLeft, int money) {
		money += prices[index];
		if (numLeft == 1) {
			min = Math.min(min, money);
			return;
		}
		if (index == signals.length) {
			return;
		}
		for (int i = index + 1; i < signals.length; i++) {
			if (signals[i] > signals[index]) {
				dfs(signals, prices, i, numLeft - 1, money);
			}
		}
	}
	
    public static void main(String[] args) {      
        Scanner sc = new Scanner(System.in);
        int houses = sc.nextInt();
        int[] signals = new int[houses];
        int[] prices = new int[houses];
        for (int i = 0; i < houses; i++) {
        		signals[i] = sc.nextInt();
        }
        
        for (int i = 0; i < houses; i++) {
        		prices[i] = sc.nextInt();
        }
        int res = findSignal(signals, prices);
        if (res > -1) {
        		System.out.println(res);
        } else {
        		System.out.println("Oh no!");
        }
    }
}
