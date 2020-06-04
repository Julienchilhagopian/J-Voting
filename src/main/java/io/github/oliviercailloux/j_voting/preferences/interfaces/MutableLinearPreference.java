package io.github.oliviercailloux.j_voting.preferences.interfaces;

import io.github.oliviercailloux.j_voting.Alternative;

/**
 * A mutable linear preference is a mutable antisymmetric complete preference. A mutable linear
 * preference represents a linear order, or equivalently an antisymmetric
 * complete order, or equivalently, the reduction of a weak-order.
 * In this preference, it is possible to add alternatives and reorder them
 */

public interface MutableLinearPreference extends Preference {
	
	/**
	 * Moves an existing alternative to the desired rank in the preference. All the intermediate alternatives shift. More precisely, the given alternative is swapped with its neighbor until it reaches the given rank. 
	 * 
	 * @param alternative that we're going to move in the preference
	 * @param rank is the new rank where the alternative will be. The first alternative is at the rank 1. 
	 */
	public void changeOrder(Alternative alternative, int rank);
	
	/**
	 * Removes the specified alternative from this preference if it is present.
	 *
	 * @param alternative to be removed from this preference, if present
	 * @return true if this set contained the specified alternative 
	 */
	public boolean removeAlternative(Alternative alternative);
	
	/**
	 * Adds the specified alternative at the last rank of this preference if it is not already present. 
	 *
	 * @param alternative to be added to this preference
	 * @return true if this preference did not already contain the specified alternative
	 */
	public boolean addAlternative(Alternative alternative);
	
	/**
	 * This method enables to swap 2 existing alternatives of the preference. (If the specified alternatives are equal, invoking this method leaves the preference unchanged.)
	 * 
	 * @param alternative1 that will change places with alternative2
	 * @param alternative2 that will change places with alternative1
	 * @return true if the alternatives are contained in the preference and if the preference has changed after this call.
	 */
	public boolean swap(Alternative alternative1, Alternative alternative2);
	
}
