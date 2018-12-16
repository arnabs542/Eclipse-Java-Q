package practiceContent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculateMedianTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	// Should not has cross dependency between tests
	
	/* --< Test Cases>-- */
	
	// null
	// empty
	// sorted
	// unsorted
	// even length / odd length
	// negative
	// all duplicate
	// Integer.MIN_VALUE, Integer.MAX_VALUE
	
	@Test
	void test1() {
		CalculateMedian cm = new CalculateMedian();
		double res = cm.getMedian(null);
		assertEquals(0, res, 0.00001);
	}
	
	@Test
	void test2() {
		CalculateMedian cm = new CalculateMedian();
		double res = cm.getMedian(null);
		assertEquals(0, res, 0.00001);
	}
	
	@Test
	void test3() {
		CalculateMedian cm = new CalculateMedian();
		int[] array = new int[] {1, 2, 3};
		double res = cm.getMedian(array);
		assertEquals(2, res);
	}

	@Test
	void test4() {
		CalculateMedian cm = new CalculateMedian();
		int[] array = new int[] {4,  4, 3, 2};
		double res = cm.getMedian(array);
		assertEquals(3.5, res);
	}
}
