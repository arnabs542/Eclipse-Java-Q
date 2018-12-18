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
		return o2.compareTo(o1); 
	}
}



public class RewriteSortCompare {

	private static void print(List<Person> list) {
		for (Person p : list) {
			System.out.println(p.name);
		}
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
		
		/* -------------< Override Comparator of Arrays.sort() >----------------*/
		System.out.println("---< Override Comparator of Arrays.sort() >---");
		
		int[] arr1 = new int[] {5, 4, 5, 6, 3, 2};
		
//       Arrays.sort(arr1, Comparator.comparing((int[] arr) -> arr[0]).reversed());
        
//		Arrays.sort(arr1, new Comparator<int[]>() {
//		    @Override
//		    public int compare(int[] o1, int[] o2) {
//		        return ((Integer) o2[0]).compareTo(o1[0]);
//		    }
//		});
		
//		Arrays.sort(arr1, (int[] o1, int[] o2) -> o2[0] - o1[0]);
		
		for (int item : arr1) {
			System.out.println(item);
		}
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
	}
	
}
