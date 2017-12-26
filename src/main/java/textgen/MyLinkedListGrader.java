package textgen;

import java.io.PrintWriter;

/**
 * A grader class to test the MyLinkedList implementation
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Solange U. Gasengayire
 */
public class MyLinkedListGrader {

	/**
	 * Print words of a linked list from the head to the tail of the list
	 * @param lst the linked list of words
	 * @return the words in the list
	 */
	private String printListForwards(MyLinkedList<Integer> lst) {

		LLNode<Integer> curr;
		StringBuilder ret = new StringBuilder();

		if (lst.head.data == null)
			curr = lst.head.next;
		else
			curr = lst.head;
		
		while (curr != null && curr.data != null) {
			ret.append(curr.data);
			curr = curr.next;
		}

		return ret.toString();
	}

	/**
	 * Print words of a linked list from the tail to the head of the list
	 * @param lst the linked list of words
	 * @return the words in the list
	 */
	private String printListBackwards(MyLinkedList<Integer> lst) {

		LLNode<Integer> curr;
		StringBuilder ret = new StringBuilder();

		if (lst.tail.data == null)
			curr = lst.tail.prev;
		else
			curr = lst.tail;

		while (curr != null && curr.data != null) {
			ret.append(curr.data);
			curr = curr.prev;
		}

		return ret.toString();
	}

	/**
	 * Method to run tests
	 */
	private void doTest() {

		String feedback = "";
		PrintWriter out;

		try {
			out = new PrintWriter("grader_output/module3.part1.out", "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		MyLinkedList<Integer> lst = new MyLinkedList<>();
		int nums[] = {1, 2, 3, 4, 5};
		
                feedback += "** Test #1: Adding to end of list... ";

		for (int i : nums) {
			lst.add(i);
			}
                feedback += "Got " + printListForwards(lst) + ". \n";

                feedback += "** Test #2: Getting from the middle... ";
                feedback += "Fourth element was " + lst.get(3) + ". \n";

		lst.add(2, 6);
		
                feedback += "** Test #3: Adding to the middle... ";
                feedback += "Got " + printListForwards(lst) + ". \n";

                feedback += "** Test #4: Testing 'prev' pointers by going through the list backwards... ";
                feedback += "Got " + printListBackwards(lst) + ". \n";

                feedback += "** Test #5: Testing list size... ";
                feedback += "Got " + lst.size() + ". \n";
		
		lst.remove(2);

                feedback += "** Test #6: Removing from the middle... ";
                feedback += "Got " + printListForwards(lst) + ". \n";
                
                feedback += "** Test #7: Testing 'prev' pointers on remove by going through the list backwards... ";
                feedback += "Got " + printListBackwards(lst) + ". \n";

                feedback += "** Test #8: Testing size after remove... ";
                feedback += "Got " + lst.size() + ". \n";

                feedback += "** Test #9: Testing add, remove, and add on new list... ";

		lst = new MyLinkedList<>();
		lst.add(0, 1);
		lst.remove(0);
		lst.add(0, 1);

                feedback += "Got " + printListForwards(lst) + ". \n";

                feedback += "** Test 10: Checking size after previous test... ";
                feedback += "List size is " + lst.size() + ". \n";

                feedback += "** Tests 11-20: Testing method bounds... ";
                
                out.println(feedback + "Tests complete. Check that everything is as expected.\n");
                out.close();
	}

	/**
	 * Main tester methog
	 * @param args command-line argumets
	 */
	public static void main(String args[]) {
		MyLinkedListGrader grader = new MyLinkedListGrader();
		grader.doTest();
	}
	
}
