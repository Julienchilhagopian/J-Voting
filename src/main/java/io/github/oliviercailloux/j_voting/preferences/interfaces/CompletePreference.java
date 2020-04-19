package io.github.oliviercailloux.j_voting.preferences.interfaces;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.exceptions.DuplicateValueException;
import io.github.oliviercailloux.j_voting.exceptions.EmptySetException;
import io.github.oliviercailloux.j_voting.preferences.classes.CompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.preferences.classes.LinearPreferenceImpl;

/**
 * A Complete Preference is is an immutable preference. A complete preference
 * represents a complete pre-order, also called a weak order. for each couple of
 * alternatives (a,b) we can find an order a>=b or b>=a.
 */
public interface CompletePreference extends ImmutablePreference {

    /**
     * The rank of an alternative is one plus the number of alternatives
     * strictly preferred to it.
     *
     * @param a is an <code>Alternative</code>
     * @return the rank of this alternative (a number between 1 and <i>n</i>)
     *         where <i>n</i> is the total number of <code>Alternative</code>
     *         instances.
     *
     * @throws IllegalArgumentException if a is not contained in this preference
     */
    public int getRank(Alternative a);
    
    /**
     * 
     * @param eqClasses is a List of Set of Alternatives (=preference) <code>not null</code>
     * @return the size of a list of alternative sets (it means the number of Sets)
     */
    public int size(ImmutableList<ImmutableSet<Alternative>> eqClasses);
    
    /**
     * 
     * @param eqClasses is a List of Set of Alternatives (=preference) <code>not null</code>
     * @return the number of alternatives in the Preference
     */
    public int alternativeNumber(ImmutableList<ImmutableSet<Alternative>> eqClasses);

    /**
     * 
     * @return true if the Preference is Strict (without several alternatives
     *         having the same rank)
     */
    public boolean isStrict();
    
    /**
     * 
     * @param eqClasses not <code> null </code> a list of sets of alternatives
     * @return a set of alternatives containing all the alternatives of the list
     *         of set of alternative given. If an alternative appears several
     *         times in the list of sets, it appears only once in the new set.
     */
    public Set<Alternative> toAlternativeSet(ImmutableList<ImmutableSet<Alternative>> eqClasses);

    /**
     * @param alternative <code>not null</code>
     * @return whether the preference contains the alternative given as
     *         parameter
     */
    public boolean contains(Alternative alternative);
    
    /**
     *
     * @param rank is a rank. Must be > 0.
     * @return the <code>Aternative</code> set at this rank. Empty set id there
     *         is no alternative at this rank.
     * @throws IllegalArgumentException if <code>n < 1</code>.
     */
    public ImmutableSet<Alternative> getAlternatives(int rank);

    /**
     *
     * @return Same data but in an Immutable list object A set of alternative is
     *         strictly prefered to next sets.
     *
     *         All the alternatives in a set are considered ex-aequo.
     */
    public ImmutableList<ImmutableSet<Alternative>> asEquivalenceClasses();
    
    /**
     * @param completePreference <code>not null</code>
     * @return whether the parameter preference contains all the alternatives in
     *         the calling preference
     */
    public boolean isIncludedIn(CompletePreferenceImpl completePreference);
    
    /**
     * @param completePreference <code>not null</code>
     * @return whether the preferences are about the same alternatives exactly
     *         (not necessarily in the same order).
     */
    public boolean hasSameAlternatives(CompletePreferenceImpl completePreference);
    
    /**
     * 
     * @return the StrictPreference built from the preference if the preference
     *         is strict. If the preference is not strict it throws an
     *         IllegalArgumentException.
     * @throws DuplicateValueException 
     * @throws EmptySetException 
     */
    public LinearPreferenceImpl toStrictPreference() throws EmptySetException, DuplicateValueException;
    
}
