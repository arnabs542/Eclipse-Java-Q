package practiceContent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Person {
	String name;
	Integer age;
	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class AgeComparator implements Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		return o1.age.compareTo(o2.age); // larger age with higher priority
		
		//return o2.age.compareTo(o1.age);  // smaller age with higher priority
	}
}

class ArrComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {
		return o1.compareTo(o2);
	}
}


public class RewriteSortCompare {
	  
	private static void print(List<Person> list) {
		for (Person p : list) {
			System.out.println(p.name);
		}
	}
	
	private static void print2DArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j  = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String[] args) {
		
		List<Person> list1 = new ArrayList<>();
		list1.add(new Person("Bell", 23));
		list1.add(new Person("Jinu", 27));
		list1.add(new Person("Mino", 25));
		list1.add(new Person("Hoony", 26));
		list1.add(new Person("Yoon", 24));
		
		/* -------------< Method 1: Override Comparator with new Comparator Class >----------------*/
		System.out.println("---< Override Comparator with new Comparator Class >---");
		
		Collections.sort(list1, new AgeComparator());
		print(list1);
		
		/* -------------< Method 2: Override Comparator with new Comparator instance >----------------*/
		System.out.println("---< Override Comparator with new Comparator instance >---");
		
		Collections.sort(list1, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {				
				return o2.age.compareTo(o1.age);  // smaller age with higher priority
			}
		});
		
		print(list1);
		
		/* -------------< Method 3: Override Comparator with lamda >----------------*/
		System.out.println("---< Override Comparator with lamda >---");
		
		Collections.sort(list1, (a, b) -> (a.age - b.age));
		print(list1);
		
		/* -------------< Override Comparator of Arrays.sort() for 2D array >----------------*/
		System.out.println("---< Override Comparator of Arrays.sort() >---");
		
        Integer[] arr2 = new Integer[] {5, 4, 5, 6, 3, 2};
        
		Arrays.sort(arr2, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		}); // but this Comparator doesn't work for int[]
		
		for (int item : arr2) {
			System.out.print(item + " ");
		}
		System.out.println();
		
		/* -------------< Override Comparator of Arrays.sort() for 2D array >----------------*/
		System.out.println("---< Override Comparator of Arrays.sort() for 2D array>---");
		
		int[][] arr1 = new int[][] {{1, 2, 3}, {2, 1, 6}};
		
		// compare arrays using the first element
		Arrays.sort(arr1, Comparator.comparingInt(arr -> arr[0])); 
		print2DArr(arr1);
		
		// compare arrays using the first element, but in reverse priority
		Arrays.sort(arr1, Comparator.comparing((int[] arr) -> arr[0]).reversed());
		print2DArr(arr1);
		
		// compare arrays using the third element
		Arrays.sort(arr1, Comparator.comparingInt(arr -> arr[2])); 
		print2DArr(arr1);
		
		// Or use new Comparator and override compare()
		Arrays.sort(arr1, new Comparator<int[]>() {
		    @Override
		    public int compare(int[] o1, int[] o2) {
		        return ((Integer) o1[0]).compareTo(o2[0]);
		    }
		});
		print2DArr(arr1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
	}
	
}
