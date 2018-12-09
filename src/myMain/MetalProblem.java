package myMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Metal {
	int cost;
	String m;
	Metal(int cost, String meterial){
		this.cost = cost;
		this.m = meterial;
	}
	private void find() {
		for  (int i = 0; i < m.length(); i++) {
			if (m.charAt(i) == 'A' || m.charAt(i) == 'B' || m.charAt(i) == 'C') {
				
			}
		}
	}
}

public class MetalProblem {

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
        int num = sc.nextInt();
        List<Metal> list = new ArrayList<>();
        while (sc.hasNext()) {
        		int cost = sc.nextInt();
        		String m = sc.next();
        		Metal metal = new Metal(cost, m);
        		list.add(metal);
        }
    }
}
