package io.github.oliviercailloux.j_voting.preferences.classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.OldLinearPreferenceImpl;
import io.github.oliviercailloux.j_voting.Voter;
import io.github.oliviercailloux.j_voting.exceptions.DuplicateValueException;
import io.github.oliviercailloux.j_voting.exceptions.EmptySetException;
import io.github.oliviercailloux.j_voting.preferences.interfaces.LinearPreference;
import io.github.oliviercailloux.j_voting.preferences.interfaces.Preference;

public class LinearPreferenceImpl extends CompletePreferenceImpl
                implements LinearPreference {
    // Je crois que cet attribut peut etre private
    ImmutableList<Alternative> list;
    private static final Logger LOGGER = LoggerFactory
                    .getLogger(LinearPreferenceImpl.class.getName());

    /**
     * 
     * @param voter            <code> not null </code>
     * @param listAlternatives <code> not null </code>
     * @return new LinearPreference
     * @throws EmptySetException
     * @throws DuplicateValueException
     */
    public static LinearPreference asLinearPreference(Voter voter, List<Alternative> listAlternatives)
                    throws EmptySetException, DuplicateValueException {
        LOGGER.debug("LinearPreferenceImpl Factory with list of Alternatives");
        Preconditions.checkNotNull(voter);
        Preconditions.checkNotNull(listAlternatives);
        List<Set<Alternative>> equivalenceClasses = Lists.newArrayList();
        for (Alternative alternative : listAlternatives) {
            equivalenceClasses.add(ImmutableSet.of(alternative));
        }
        return new LinearPreferenceImpl(voter, equivalenceClasses);
    }
    
    public static LinearPreference asLinearPreference2(Voter voter, List<Alternative> listAlternatives, ImmutableList<ImmutableSet<Alternative>> equivalenceClasses)
            throws EmptySetException, DuplicateValueException {
		LOGGER.debug("LinearPreferenceImpl Factory with list of Alternatives");
		Preconditions.checkNotNull(voter);
		Preconditions.checkNotNull(listAlternatives);
		Preconditions.checkNotNull(equivalenceClasses);

		return new LinearPreferenceImpl(voter, listAlternatives, equivalenceClasses);
	}

    /**
     * 
     * @param voter              <code> not null </code>
     * @param equivalenceClasses <code> not null </code>
     * @throws EmptySetException
     * @throws DuplicateValueException
     */
    private LinearPreferenceImpl(Voter voter, List<Set<Alternative>> equivalenceClasses)
                    throws EmptySetException, DuplicateValueException {
        super(voter, equivalenceClasses);
        List<Alternative> tmpList = Lists.newArrayList();
        for (Set<Alternative> equivalenceClass : equivalenceClasses) {
            Alternative alternative = Iterables.getOnlyElement(equivalenceClass);
            tmpList.add(alternative);
        }
        this.list = ImmutableList.copyOf(tmpList);
    }
    
    
    private LinearPreferenceImpl(Voter voter, List<Alternative> listAlternatives, ImmutableList<ImmutableSet<Alternative>> equivalenceClasses)
            throws EmptySetException, DuplicateValueException {
		super(voter, equivalenceClasses);
		
		this.list = ImmutableList.copyOf(listAlternatives);
	}
    
    /**
     * @param list1 a list of alternatives not <code> null </code>
     * @return a list of set of alternatives. each set is composed of one
     *         alternative
     */
    // peut list1 on peut mettre alternativeList ou un truc dans le genre
	public static List<Set<Alternative>> listAlternativeToListSetAlternative(List<Alternative> list1) {
        Preconditions.checkNotNull(list1);
        List<Set<Alternative>> list = new ArrayList<>();
        for (Alternative a : list1) {
            Set<Alternative> set = new HashSet<>();
            set.add(a);
            list.add(set);
        }
        return list;
    }
        
    /**
     * Factory method for StrictCompletePreferenceImpl
     * 
     * @param preference <code> not null</code> and all different alternatives
     * @return a new StrictCompletePreferenceImpl
     * @throws DuplicateValueException 
     * @throws EmptySetException 
     */
    // Faut check la javadoc si vous voulez l'update ou ne pas la garder
	public static LinearPreferenceImpl createStrictCompletePreferenceImpl(Voter voter, ImmutableList<Alternative> preference) throws EmptySetException, DuplicateValueException {
        return new LinearPreferenceImpl(voter, listAlternativeToListSetAlternative(preference));
    }
	
	/**
     * 
     * @param ImmutableList<Alternative> list1
     * @return the length of the list
     */
	@Override
	public int sizeLinear(ImmutableList<Alternative> list1) {
		int size = 0;
		for(Alternative alternative : list1) {
			size += 1;
		}
		return size;
	}
	
    /**
     * 
     * @param position not <code>null</code>
     * @return the alternative at the position given in the strict preference
     */
    // juste position -> rank
    public Alternative getAlternative(Integer rank) {
        Preconditions.checkNotNull(rank);
        if (rank >= this.sizeLinear(this.list)) {
            throw new IndexOutOfBoundsException("This position doesn't exist in the Preference");
        }
        return list.get(rank);
    }
    
    @Override
    public ImmutableList<Alternative> asList() {
        return this.list;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.asGraph());
    }

    @Override
    public boolean equals(Object o2) {
        if (this == o2) {
            return true;
        }
        if (!super.equals(o2)) {
            return false;
        }
        if (!(o2 instanceof Preference)) {
            return false;
        }
        Preference other = (LinearPreferenceImpl) o2;
        return this.asGraph().equals(other.asGraph());
    }
}
