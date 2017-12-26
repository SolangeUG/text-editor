package spelling;

import java.util.List;

/**
 * AutoComplete Interface
 * @author Christine Alvarado
 *
 */
public interface AutoComplete {

	/**
	 * Return a list of predicted words from a dictionary
	 * @param prefix prefix from which predictions are made
	 * @param numCompletions number of predictions requested
	 * @return a list of predictions with a give prefix
	 */
	List<String> predictCompletions(String prefix, int numCompletions);
}
