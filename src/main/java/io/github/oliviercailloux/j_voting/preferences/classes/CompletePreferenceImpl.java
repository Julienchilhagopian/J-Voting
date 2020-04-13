package io.github.oliviercailloux.j_voting.preferences.classes;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableGraph;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.OldCompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.Voter;
import io.github.oliviercailloux.j_voting.exceptions.DuplicateValueException;
import io.github.oliviercailloux.j_voting.exceptions.EmptySetException;
import io.github.oliviercailloux.j_voting.preferences.interfaces.CompletePreference;

public class CompletePreferenceImpl implements CompletePreference {

    private ImmutableList<ImmutableSet<Alternative>> equivalenceClasses;
    private Voter voter;
    private ImmutableGraph<Alternative> graph;
    ImmutableSet<Alternative> alternatives;
    private static final Logger LOGGER = LoggerFactory
                    .getLogger(CompletePreferenceImpl.class.getName());

    /**
     * 
     * @param equivalenceClasses <code> not null </code> the best equivalence
     *                           class must be in first position. An alternative
     *                           must be unique
     * @param voter              <code> not null </code>
     * @return new CompletePreference
     * @throws DuplicateValueException if an Alternative is duplicate
     * @throws EmptySetException       if a Set is empty
     */
    public static CompletePreference asCompletePreference(Voter voter,
                    List<? extends Set<Alternative>> equivalenceClasses)
                    throws DuplicateValueException, EmptySetException {
        LOGGER.debug("Factory CompletePreferenceImpl");
        Preconditions.checkNotNull(equivalenceClasses);
        Preconditions.checkNotNull(voter);
        return new CompletePreferenceImpl(voter, equivalenceClasses);
    }

    /**
     * 
     * @param equivalenceClasses <code> not null </code>
     * @param voter              <code> not null </code>
     * @throws EmptySetException
     * @throws DuplicateValueException
     */
    protected CompletePreferenceImpl(Voter voter,
                    List<? extends Set<Alternative>> equivalenceClasses)
                    throws EmptySetException, DuplicateValueException {
        LOGGER.debug("Constructor CompletePreferenceImpl");
        this.voter = voter;
        List<ImmutableSet<Alternative>> listImmutableSets = Lists
                        .newArrayList();
        for (Set<Alternative> set : equivalenceClasses) {
            listImmutableSets.add(ImmutableSet.copyOf(set));
        }
        this.equivalenceClasses = ImmutableList.copyOf(listImmutableSets);
        this.graph = createGraph(equivalenceClasses);
        this.alternatives = ImmutableSet.copyOf(this.graph.nodes());
    }

    private ImmutableGraph<Alternative> createGraph(
                    List<? extends Set<Alternative>> equivalenceClasses1)
                    throws EmptySetException, DuplicateValueException {
        List<Alternative> listAlternatives = Lists.newArrayList();
        MutableGraph<Alternative> newGraph = GraphBuilder.directed()
                        .allowsSelfLoops(true).build();
        Alternative lastSetLinker = null;
        for (Set<Alternative> equivalenceClasse : equivalenceClasses1) {
            if (equivalenceClasse.isEmpty())
                throw new EmptySetException("A Set can't be empty");
            Alternative rememberAlternative = null;
            for (Alternative alternative : equivalenceClasse) {
                if (listAlternatives.contains(alternative))
                    throw new DuplicateValueException(
                                    "you can't duplicate Alternatives");
                listAlternatives.add(alternative);
                if (lastSetLinker != null) {
                    newGraph.putEdge(lastSetLinker, alternative);
                    lastSetLinker = null;
                }
                newGraph.putEdge(alternative, alternative);
                if (!Objects.isNull(rememberAlternative)) {
                    newGraph.putEdge(rememberAlternative, alternative);
                    newGraph.putEdge(alternative, rememberAlternative);
                }
                rememberAlternative = alternative;
            }
            lastSetLinker = rememberAlternative;
        }
        return ImmutableGraph.copyOf(Graphs.transitiveClosure(newGraph));
    }
    
    /**
     * @return the number of alternatives in
     *         the Preference
     */
    @Override
	public int alternativeNumber(ImmutableList<ImmutableSet<Alternative>> equivalenceClasses1) {
        int number = 0;
        for (ImmutableSet<Alternative> set : equivalenceClasses1) {
        	for(Alternative alt : set) {
        		number += 1;
        	}
        }
        return number;
    }
    
    /**
     * 
     * @return the size of a list of alternative sets (it means the number of Sets)
     */
    @Override
	public int size(ImmutableList<ImmutableSet<Alternative>> equivalenceClasses1) {
        Preconditions.checkNotNull(equivalenceClasses1);
        int size = 0;
        for (ImmutableSet<Alternative> set : equivalenceClasses1) {
            size += 1;
        }
        return size;
    }
    
    /**
     * 
     * @return true if the Preference is Strict (without several alternatives
     *         having the same rank)
     */
    @Override
    public boolean isStrict() {
        return (alternativeNumber(equivalenceClasses) == size(equivalenceClasses));
    }
    
    /**
     * 
     * @param preferences not <code> null </code> a list of sets of alternatives
     * @return a set of alternatives containing all the alternatives of the list
     *         of set of alternative given. If an alternative appears several
     *         times in the list of sets, it appears only once in the new set.
     */
    @Override
	public Set<Alternative> toAlternativeSet(ImmutableList<ImmutableSet<Alternative>> equivalenceClasses1) {
        Preconditions.checkNotNull(equivalenceClasses1);
        Set<Alternative> set = new HashSet<>();
        for (ImmutableSet<Alternative> sets : equivalenceClasses1) {
            for (Alternative alter : sets) {
                if (!set.contains(alter)) {
                    set.add(alter);
                }
            }
        }
        return set;
    }
    
    /**
     * @param alter <code>not null</code>
     * @return whether the preference contains the alternative given as
     *         parameter
     */
    @Override
	public boolean contains(Alternative alternative) {
        Preconditions.checkNotNull(alternative);
        return (toAlternativeSet(equivalenceClasses).contains(alternative));
    }
    
    /**
     * @param p <code>not null</code>
     * @return whether the parameter preference contains all the alternatives in
     *         the calling preference
     */
    @Override
	public boolean isIncludedIn(CompletePreferenceImpl completePreference) {
        Preconditions.checkNotNull(completePreference);
        for (Alternative alternative : toAlternativeSet(equivalenceClasses)) {
            if (!completePreference.contains(alternative)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * @param p <code>not null</code>
     * @return whether the preferences are about the same alternatives exactly
     *         (not necessarily in the same order).
     */
    @Override
	public boolean hasSameAlternatives(CompletePreferenceImpl completePreference) {
        Preconditions.checkNotNull(completePreference);
        return (this.isIncludedIn(completePreference) && completePreference.isIncludedIn(this));
    }
    
    
    
    
    
    
    

    @Override
    public ImmutableSet<Alternative> getAlternatives() {
        return this.alternatives;
    }

    @Override
    public Voter getVoter() {
        return this.voter;
    }

    @Override
    public int getRank(Alternative a) {
        Preconditions.checkNotNull(a);
        for (ImmutableSet<Alternative> equivalenceClass : equivalenceClasses) {
            if (equivalenceClass.contains(a))
                return equivalenceClasses.indexOf(equivalenceClass) + 1;
        }
        throw new NoSuchElementException("Alternative not found");
    }

    @Override
    public ImmutableSet<Alternative> getAlternatives(int rank) {
        return ImmutableSet.copyOf(equivalenceClasses.get(rank - 1));
    }

    @Override
    public ImmutableList<ImmutableSet<Alternative>> asEquivalenceClasses() {
        return equivalenceClasses;
    }

    @Override
    public ImmutableGraph<Alternative> asGraph() {
        return graph;
    }

    @Override
    public int hashCode() {
        return Objects.hash(equivalenceClasses, graph, voter);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CompletePreferenceImpl)) {
            return false;
        }
        CompletePreferenceImpl other = (CompletePreferenceImpl) obj;
        return Objects.equals(equivalenceClasses, other.equivalenceClasses)
                        && Objects.equals(graph, other.graph)
                        && Objects.equals(voter, other.voter);
    }
}
