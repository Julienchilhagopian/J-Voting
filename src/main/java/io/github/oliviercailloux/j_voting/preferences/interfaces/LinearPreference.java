package io.github.oliviercailloux.j_voting.preferences.interfaces;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.Voter;
import io.github.oliviercailloux.j_voting.exceptions.DuplicateValueException;
import io.github.oliviercailloux.j_voting.exceptions.EmptySetException;
import io.github.oliviercailloux.j_voting.preferences.classes.LinearPreferenceImpl;

/**
 * A linear preference is an antisymmetric complete preference. A linear
 * preference represents a linear order, or equivalently an antisymmetric
 * complete order, or equivalently, the reduction of a weak-order.
 * 
 */
public interface LinearPreference extends CompletePreference {

    /**
     * Best alternatives coming first in the list.
     *
     * @return a sorted list of alternatives corresponding to the preference.
     *
     */
    public ImmutableList<Alternative> asList();
    
    /**
     * 
     * @param  alternativeList an ImmutableList of Alternative
     * @return the length of the list
     */
	public int sizeLinear(ImmutableList<Alternative> alternativeList);
    
}
