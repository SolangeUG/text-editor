package textgen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the MyLinkedList class
 * @author UC San Diego MOOC team
 * @author Solange U. Gasengayire
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10;

	private MyLinkedList<String> shortList;
	private MyLinkedList<Integer> emptyList;
	private MyLinkedList<Integer> longerList;
	private MyLinkedList<Integer> integers;
	
	/**
	 * State initializer method
	 */
	@BeforeEach
	void setUp() {
		// an empty list
		emptyList = new MyLinkedList<>();

		// a short list
		shortList = new MyLinkedList<>();
		shortList.add("A");
		shortList.add("B");

		// a long list
		longerList = new MyLinkedList<>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}

		// an arbitrary list
		integers = new MyLinkedList<>();
		integers.add(65);
		integers.add(21);
		integers.add(42);
	}

	/**
	 * Test the size of the list
	 */
	@Test
	@DisplayName("Test size of lists")
	void testSize() {
		testSizeOfEmptyList();
		testSizeOfShortList();
		testSizeOfLongList();
		testSizeOfLArbitraryList();
	}

	/**
	 * Test if the get method is working correctly.
	 * You should not need to add much to this method.
	 * We provide it as an example of a thorough test.
	 */
	@Test
	@DisplayName("Test get method of lists")
	void testGet() {
		testGetFromEmptyList();
		testGetFromShortList();
		testGetFromLongList();
		testGetFromArbitraryList();
	}

	/**
	 * Test setting an element in the list.
	 */
	@Test
	@DisplayName("Test set method of lists")
	void testSet() {
		testSetOfEmptyList();
		testSetOfShortList();
		testSetOfLongList();
		testSetOfArbitraryList();
	}

	/**
	 * Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.
	 */
	@Test
	@DisplayName("Test remove method of lists")
	void testRemove() {
		testRemoveFromEmptyList();
		testRemoveFromShortList();
		testRemoveFromLongList();
		testRemoveFromArbitraryList();
	}

	/**
	 * Test adding an element into the end of the list.
	 * Specifically: public boolean add(E element).
	 */
	@Test
	@DisplayName("Test add method of lists")
	void testAddEnd() {
		testAddEndOfEmptyList();
		testAddEndOfShortList();
		testAddEndOfLongList();
		testAddEndOfArbitraryList();
	}

	/**
	 * Test adding an element into the list at a specified index.
	 * Specifically: public void add(int index, E element).
	 */
	@Test
	@DisplayName("Test add at index method of lists")
	void testAddAtIndex() {
		testAddAtIndexOfEmptyList();
		testAddAtIndexOfShortList();
		testAddAtIndexOfLongList();
		testAddAtIndexOfArbitraryList();
	}


	/* ********************************************************************** *
	 * 		Shorter atomic tests for each method of our four lists.			  *
	 * ********************************************************************** */

	/* ******************** TESTING THE SIZE() METHOD *********************** */

	private void testSizeOfEmptyList() {
		String testName = "Size | Empty list | ";
		int size = emptyList.size();
		assertAll(testName + "Check size is correct",
				() -> assertEquals(0, size)
		);
	}

	private void testSizeOfShortList() {
		String testName = "Size | Short list | ";
		int size = shortList.size();
		assertAll(testName + "Check size is correct",
				() -> assertEquals(2, size));
	}

	private void testSizeOfLongList() {
		String testName = "Size | Long list | ";
		int size = longerList.size();
		assertAll(testName + "Check size is correct",
				() -> assertEquals( LONG_LIST_LENGTH, size));
	}

	private void testSizeOfLArbitraryList() {
		String testName = "Size | Arbitrary list | ";
		int size = integers.size();
		assertAll(testName + "Check size is correct ",
				() -> assertEquals( 3, size));
	}

	/* ************************** TESTING THE GET() METHOD ****************** */

	private void testGetFromEmptyList() {
		String testName = "Get | Empty list | ";
		try {
			emptyList.get(0);
			fail(testName + "Check index 0 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testGetFromEmptyList exception");
		}
	}

	private void testGetFromShortList() {
		String testName = "Get | Short list | ";
		assertAll(testName + "Check first element is correct",
				() -> assertEquals( "A", shortList.get(0)));
		assertAll(testName + "Check second element is correct",
				() -> assertEquals("B", shortList.get(1)));

		try {
			shortList.get(-1);
			fail(testName + "Check index -1 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testGetFromShortList exception");
		}

		try {
			shortList.get(2);
			fail(testName + "Check index 2 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testGetFromShortList exception");
		}
	}

	private void testGetFromLongList() {
		String testName = "Get | Long list | ";
		for(int i = 0; i < LONG_LIST_LENGTH; i++) {
			int finalI = i;
			assertAll(testName + "Check " + i + " element is correct",
					() -> assertEquals((Integer) finalI, longerList.get(finalI)));
		}

		try {
			longerList.get(-1);
			fail(testName + "Check index -1 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testGetFromLongList exception");
		}

		try {
			longerList.get(LONG_LIST_LENGTH);
			fail(testName + "Check index " + LONG_LIST_LENGTH + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testGetFromLongList exception");
		}
	}

	private void testGetFromArbitraryList() {
		String testName = "Get | Arbitrary list | ";

		assertAll(testName + "Check element 0 is correct",
				() -> assertEquals((Integer) 65, integers.get(0)));
		assertAll(testName + "Check element 1 is correct",
				() -> assertEquals((Integer) 21, integers.get(1)));
		assertAll(testName + "Check element 2 is correct",
				() -> assertEquals( (Integer) 42, integers.get(2)));

		try {
			integers.get(-1);
			fail(testName + "Check index -1 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testGetFromArbitraryList exception");
		}

		try {
			integers.get(3);
			fail(testName + "Check index 3 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testGetFromArbitraryList exception");
		}
	}

	/* ************************* TESTING THE SET() METHOD ******************* */

	private void testSetOfEmptyList() {
		String testName = "Set | Empty list | ";
		try {
			emptyList.set(0, 4);
			fail(testName + "Check index 0 out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testSetOfEmptyList exception");
		}
	}

	private void testSetOfShortList() {
		String testName = "Set | Short list | ";

		String element = shortList.set(1, "D");
		assertAll(testName + "Check element 0 is correct",
				() -> assertEquals( "A", shortList.get(0)));
		assertAll(testName + "Check element 1 is correct",
				() -> assertEquals( "B", element));
		assertAll(testName + "Check element 1 is correct",
				() -> assertEquals( "D", shortList.get(1)));
		assertAll(testName + "Check size is correct",
				() -> assertEquals( 2, shortList.size()));
		assertAll(testName + "Check expected content",
				() -> assertEquals( "[A|D]", shortList.toString()));

		try {
			shortList.set(2, "AB");
			fail(testName + "Check index 2 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testSetOfShortList exception");
		}
	}

	private void testSetOfLongList() {
		String testName = "Set | Long list | ";

		Integer element = longerList.set(5, 15);
		assertAll(testName + "Check element 5 is correct",
				() -> assertEquals((Integer) 5, element));
		assertAll(testName + "Check element 5 is correct",
				() -> assertEquals((Integer) 15, longerList.get(5)));
		assertAll(testName + "Check element 4 is correct",
				() -> assertEquals((Integer) 4, longerList.get(4)));
		assertAll(testName + "Check element 6 is correct",
				() -> assertEquals((Integer) 6, longerList.get(6)));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(LONG_LIST_LENGTH, longerList.size()));
		assertAll(testName + "Check expected content",
				() -> assertEquals( "[0|1|2|3|4|15|6|7|8|9]", longerList.toString()));

		try {
			longerList.set(LONG_LIST_LENGTH, 44);
			fail(testName + "Check index " + LONG_LIST_LENGTH + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testSetOfLongList exception");
		}
	}

	private void testSetOfArbitraryList() {
		String testName = "Set | Arbitrary list | ";

		Integer element = integers.set(2, 12);
		assertAll(testName + "Check element 2 is correct",
				() -> assertEquals((Integer) 42, element));
		assertAll(testName + "Check element 2 is correct",
				() -> assertEquals((Integer) 12, integers.get(2)));
		assertAll(testName + "Check element 0 is correct",
				() -> assertEquals((Integer) 65, integers.get(0)));
		assertAll(testName + "Check element 1 is correct",
				() -> assertEquals((Integer) 21, integers.get(1)));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(3, integers.size()));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[65|21|12]", integers.toString()));

		try {
			integers.set(integers.size(), 44);
			fail(testName + "Check index " + integers.size() + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testSetOfArbitraryList exception");
		}
	}

	/* ************************ TESTING THE REMOVE() METHOD ****************** */

	private void testRemoveFromEmptyList() {
		String testName = "Remove | Empty list | ";
		try {
			emptyList.remove(0);
			fail(testName + "Check index 0 is out of bounds");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("testRemoveFromEmptyList exception");
		}
	}

	private void testRemoveFromShortList() {
		String testName = "Remove | Short list | ";

		String first = shortList.remove(0);
		assertAll(testName + "Check removed element is correct",
				() -> assertEquals("A", first));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(1, shortList.size()));
		assertAll(testName + "Check element 0 is correct",
				() -> assertEquals("B", shortList.get(0)));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[B]", shortList.toString()));

		try {
			shortList.remove(-1);
			fail(testName + "Check index -1 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testRemoveFromShortList exception");
		}

		try {
			shortList.remove(shortList.size());
			fail(testName + "Check index " + shortList.size() + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testRemoveFromShortList exception");
		}

	}

	private void testRemoveFromLongList() {
		String testName = "Remove | Long list | ";

		int element = longerList.remove(0);
		assertAll(testName + "Check removed element is correct",
				() -> assertEquals(0, element));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(9, longerList.size()));
		assertAll(testName + "Check first element is correct",
				() -> assertEquals((Integer) 1, longerList.get(0)));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[1|2|3|4|5|6|7|8|9]", longerList.toString()));

		try {
			longerList.remove(-1);
			fail(testName + "Check index -1 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testRemoveFromLongList exception");
		}

		try {
			longerList.remove(longerList.size());
			fail(testName + "Check index " + longerList.size() + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testRemoveFromLongList exception");
		}

	}

	private void testRemoveFromArbitraryList() {
		String testName = "Remove | Arbitrary list | ";

		int a = integers.remove(0);
		assertAll(testName + "Check removed element is correct",
				() -> assertEquals(65, a));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(2, integers.size()));
		assertAll(testName + "Check element 0 is correct",
				() -> assertEquals((Integer) 21, integers.get(0)));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[21|42]", integers.toString()));

		try {
			integers.remove(-1);
			fail(testName + "Check index - 1 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testRemoveFromArbitraryList exception");
		}

		try {
			integers.remove(integers.size());
			fail(testName + "Check index " + integers.size() + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testRemoveFromArbitraryList exception");
		}
	}

	/* ************************** TESTING THE ADD() METHOD ****************** */

	private void testAddEndOfEmptyList() {
		String testName = "Add | Empty list | ";

		boolean added = emptyList.add(4);
		assertAll(testName + "Check add operation was successful",
				() -> assertTrue(added));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(1, emptyList.size()));
		assertAll(testName + "Check element 0 is correct",
				() -> assertEquals((Integer) 4, emptyList.get(0)));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[4]", emptyList.toString()));
	}

	private void testAddEndOfShortList() {
		String testName = "Add | Short list | ";

		boolean added = shortList.add("C");
		assertAll(testName + "Check add operation was successful",
				() -> assertTrue(added));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(3, shortList.size()));
		assertAll(testName + "Check element 0 is correct",
				() -> assertEquals("A", shortList.get(0)));
		assertAll(testName + "Check element 1 is correct",
				() -> assertEquals("B", shortList.get(1)));
		assertAll(testName + "Check last element is correct",
				() -> assertEquals("C", shortList.get(2)));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[A|B|C]", shortList.toString()));

		try {
			shortList.get(-1);
			fail(testName + "Check index -1 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testAddEndOfShortList exception");
		}

		try {
			shortList.get(3);
			fail(testName + "Check index 3 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testAddEndOfShortList exception");
		}

	}

	private void testAddEndOfLongList() {
		String testName = "Add | Long list | ";

		boolean added = longerList.add(10);
		assertAll(testName + "Check add operation was successful",
				() -> assertTrue(added));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(11, longerList.size()));
		assertAll(testName + "Check element 0 is correct",
				() -> assertEquals((Integer) 0, longerList.get(0)));
		assertAll(testName + "Check last element is correct",
				() -> assertEquals((Integer) 10, longerList.get(10)));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[0|1|2|3|4|5|6|7|8|9|10]", longerList.toString()));

		try {
			longerList.get(-1);
			fail(testName + "Check index -1 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testAddEndOfLongList exception");
		}

		try {
			longerList.get(longerList.size());
			fail(testName + "Check index " + longerList.size() + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testAddEndOfLongList exception");
		}
	}

	private void testAddEndOfArbitraryList() {
		String testName = "Add | Arbitrary list | ";

		boolean added = integers.add(44);
		assertAll(testName + "Check add operation was successful",
				() -> assertTrue(added));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(4, integers.size()));
		assertAll(testName + "Check element 0 is correct",
				() -> assertEquals((Integer) 65, integers.get(0)));
		assertAll(testName + "Check last element is correct",
				() -> assertEquals((Integer) 44, integers.get(3)));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[65|21|42|44]", integers.toString()));

		try {
			integers.get(-1);
			fail(testName + "Check index -1 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testAddEndOfArbitraryList exception");
		}

		try {
			integers.get(integers.size());
			fail(testName + "Check index " + integers.size() + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testAddEndOfArbitraryList exception");
		}
	}

	/* *********************** TESTING THE ADD(INDEX) METHOD **************** */

	private void testAddAtIndexOfEmptyList() {
		String testName = "AddAtIndex | Empty list | ";

		emptyList.add(0, 2);
		assertAll(testName + "Check element at index 0 is correct",
				() -> assertEquals((Integer) 2, emptyList.get(0)));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(1, emptyList.size()));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[2]", emptyList.toString()));

		try {
			emptyList.add(4, 2);
			fail(testName + "Check index 4 is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testAddAtIndexOfEmptyList exception");
		}

		try {
			emptyList.add(0, null);
			fail(testName + "Check NULL values are not allowed");
		} catch (NullPointerException e) {
			System.out.println("testAddAtIndexOfEmptyList exception");
		}
	}

	private void testAddAtIndexOfShortList() {
		String testName = "AddAtIndex | Short list | ";

		shortList.add(1, "D");
		assertAll(testName + "Check element at index 0 is correct",
				() -> assertEquals("A", shortList.get(0)));
		assertAll(testName + "Check element at index 1 is correct",
				() -> assertEquals("D", shortList.get(1)));
		assertAll(testName + "Check element at index 2 is correct",
				() -> assertEquals("B", shortList.get(2)));
		assertAll(testName + "Check size is correct ",
				() -> assertEquals(3, shortList.size()));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[A|D|B]", shortList.toString()));

		try {
			shortList.add(shortList.size(), "E");
			fail(testName + "Check index " + shortList.size() + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testAddAtIndexOfShortList exception");
		}

	}

	private void testAddAtIndexOfLongList() {
		String testName = "AddAtIndex | Long list | ";

		longerList.add(4, 84);
		assertAll(testName + "Check element at index 0 is correct",
				() -> assertEquals((Integer) 0, longerList.get(0)));
		assertAll(testName + "Check element at index 4 is correct",
				() -> assertEquals((Integer) 84, longerList.get(4)));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(11, longerList.size()));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[0|1|2|3|84|4|5|6|7|8|9]", longerList.toString()));

		try {
			longerList.add(longerList.size(), 2);
			fail(testName + "Check index" + longerList.size() + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testAddAtIndexOfLongList exception");
		}

		try {
			longerList.add(0, null);
			fail(testName + "Check NULL values are not allowed");
		} catch (NullPointerException e) {
			System.out.println("testAddAtIndexOfLongList exception");
		}
	}

	private void testAddAtIndexOfArbitraryList() {
		String testName = "AddAtIndex | Arbitrary list | ";

		integers.add(2, 24);
		assertAll(testName + "Check element at index 0 is correct",
				() -> assertEquals((Integer) 65, integers.get(0)));
		assertAll(testName + "Check element at index 1 is correct",
				() -> assertEquals((Integer) 21, integers.get(1)));
		assertAll(testName + "Check element at index 2 is correct",
				() -> assertEquals((Integer) 24, integers.get(2)));
		assertAll(testName + "Check element at index 3 is correct",
				() -> assertEquals((Integer) 42, integers.get(3)));
		assertAll(testName + "Check size is correct",
				() -> assertEquals(4, integers.size()));
		assertAll(testName + "Check expected content",
				() -> assertEquals("[65|21|24|42]", integers.toString()));

		try {
			integers.add(integers.size(), 2);
			fail(testName + "Check index" + integers.size() + " is out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("testAddAtIndexOfArbitraryList exception");
		}

		try {
			integers.add(0, null);
			fail(testName + "Check NULL values are not allowed");
		} catch (NullPointerException e) {
			System.out.println("testAddAtIndexOfArbitraryList exception");
		}
	}
	
}
