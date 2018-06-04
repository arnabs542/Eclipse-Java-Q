package myMain;

import java.util.ArrayList;

public class MainClass {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0, null);
		list.add(1, 1);
		list.add(2, 2);
		list.add(3, 3);
		list.add(4, null);	
		list.add(5, null);
		list.add(6, 6);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.print("\n");
		
		list.remove(1);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.print("\n");
		
		Integer[] arr = {null, null};
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("\n");
	}	    	 
}