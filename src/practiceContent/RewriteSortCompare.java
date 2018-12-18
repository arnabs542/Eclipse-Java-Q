package practiceContent;

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
		return o1.age.compareTo(o2.age);
	}
}

public class RewriteSortCompare {

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		List<Person> list1 = new ArrayList<>();
		list1.add(new Person("Bell", 23));
		list1.add(new Person("Jinu", 27));
		list1.add(new Person("Mino", 25));
		list1.add(new Person("Hoony", 26));
		list1.add(new Person("Yoon", 24));
	
		Collections.sort(list1, new AgeComparator());
		
		for (Person p : list1) {
			System.out.println(p.name);
		}
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
	}
	
}
