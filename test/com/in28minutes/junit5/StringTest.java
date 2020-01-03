package com.in28minutes.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
// @Disabled //disable all class
class StringTest {

	private String str;
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Initialize connection to database");	
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("Close connection to database");	
	}
	
	@BeforeEach //@Before
	void beforeEach(TestInfo info) {
		System.out.println("Initialize Tets data for each test BEFORE " + info.getDisplayName());
	}
	@AfterEach //@After
	void afterEach() {
		System.out.println("Initialize Tets data for each test AFTER");
	}
	
	@Test
	@Disabled //@Ignored //not tested because of @disabled
	void length_basic() {
		int actualLength = "ABCD".length();
		int expectedLength =5;
		assertEquals(expectedLength, actualLength);
//		Assert length == 4
//	 	Write test code
//      Invoke method square(4) => CUT
//	 	Checks in place -16 =>Assertions
//		absence or failure is success! you need to put assertions
	}
	
	@Test
	void toUpperCase_basic() {
		String str = "taha";
		String result = str.toUpperCase();
		assertNotNull(result);
		assertEquals("TAHA",result);
	}
	
	@Test
	@RepeatedTest(10) //when using random value you can check 10 times or checking threads
	void contains_basic() {
		String str = "yavuz";
		boolean result = str.contains("av");
	//	assertEquals(false,result); assertFalse(result); same assertions
	//	assertFalse(result);
	//	assertTrue(result);
		assertEquals(true,result); 
		
		assertFalse("yavuz".contains("ijk"));
	}
	
	@Test
	void split_basic() {
		String str = "abc def ghi";
		String actualResult[] =str.split(" ");
		String[] expectedResult = new String[] { "abc", "def", "ghi"};
//		String[] expectedResult = new String[] { "abc", "def", "ggg"};
//		String[] expectedResult = new String[] { "abc", "def"};
//		assertArrayEquals(expectedResult, actualResult); //first parameter expected second actual for assertArrayEquals()
//		assertArrayEquals(actualResult, expectedResult ); 
		assertArrayEquals(new String[] {"abc", "def", "ghi"},actualResult);
	}
	
	
	@Nested
	@DisplayName("For an empty string")
	class EmptyStringTests{
		
		@BeforeEach
		void setToEmpty(){
			str="";
		}
	
	
	@Test
	@DisplayName("Length should be zero")
	void lengthIsZero() {
		assertEquals(0, str.length());
	}
	
	@Test
	@DisplayName("Upper case is empty")
	void uppercaseIsEmpty() {
		assertEquals("",str.toUpperCase());
	}
	}
	
	@Nested
	@DisplayName("For large strings")
	class LargeStringTests{
		
		@BeforeEach
		void setToLargeString(){
			str="aýfýabdfýabfohsaoegfhoasujgbkjgaswgswaeggf";
		}
	
	
	@Test
//	@DisplayName("Length should be zero")
	void test() {
//		assertEquals(0, str.length());
	}

	}
	
	@Test
	void length_greater_then_zero() {
		assertTrue("ABCD".length()>0);
		assertTrue("ABC".length()>0);
		assertTrue("AB".length()>0);
		assertTrue("A".length()>0);
//		assertTrue("".length()>0);
	}
	
	@ParameterizedTest(name="{0} greater than 0")
	@ValueSource(strings= {"ABCD","ABC","AB","A"})
	void length_greater_then_zero_parameterized_test(String str) {
		assertTrue(str.length()>0);
	}

	@ParameterizedTest(name="{0} toUpperCase is {1}")
	@CsvSource(value= {"taha, TAHA","yavuz,YAVUZ", "abc,ABC", "'',''", "xyz,XYZ"})
	void upperCase(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
//		assertEquals("ABC","abc".toUpperCase());
//		assertEquals("","".toUpperCase());
//		assertEquals("ABCDEFG","abcdefg".toUpperCase());
	}
	
	@ParameterizedTest(name="{0} length is {1}")
	@CsvSource(value= {"taha, 4","yavuz,5", "abc,3", "'',0", "xyz,3"})
	void length(String word, int expectedLength) {
		assertEquals(expectedLength, word.length());
	}
	
	
	@Test()
	@DisplayName("When length is null, throw an exception")
	void lengthException() {
		String str=null;
		String str2="tahaYAVUZ";
		assertThrows(NullPointerException.class, 
				() ->  {
					str.length();
				}
		);
//		assertThrows(NullPointerException.class, 
//				() ->  { //lambda expression
//					str2.length(); //not null so give error
//				}
//		);
	}
	
	@Test()
	void performanceTest() { //check duration of method
		assertTimeout(Duration.ofSeconds(2), () -> { 
					for(int i=0;i<=100000;i++) {
						int j=i;
						System.out.println(j);
					}
				}
				);
	}
	
	
	
	
}
