package io.github.oliviercailloux.j_voting.preferences.classes;

import static io.github.oliviercailloux.j_voting.AlternativeHelper.a1;
import static io.github.oliviercailloux.j_voting.AlternativeHelper.a2;
import static io.github.oliviercailloux.j_voting.AlternativeHelper.a3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.Voter;
import io.github.oliviercailloux.j_voting.exceptions.DuplicateValueException;
import io.github.oliviercailloux.j_voting.exceptions.EmptySetException;
import io.github.oliviercailloux.j_voting.preferences.interfaces.LinearPreference;

class LinearPreferenceImplTest {

    private static Voter v1 = Voter.createVoter(1);

    private static LinearPreference getListedAlternatives()
                    throws EmptySetException, DuplicateValueException {
        return LinearPreferenceImpl.asLinearPreference(Voter.createVoter(3),
                        ImmutableList.of(a1, a3, a2));
    }

    @Test
    void testlistToLinearPreference()
                    throws EmptySetException, DuplicateValueException {
        LinearPreference toTest = getListedAlternatives();
        assertEquals(ImmutableList.of(a1, a3, a2), toTest.asList());
    }

    @Test
    void testAsList() throws EmptySetException, DuplicateValueException {
        LinearPreference toTest = getListedAlternatives();
        assertEquals(ImmutableList.of(a1, a3, a2), toTest.asList());
    }

    @Test
    void throwsTest() {
        assertThrows(Exception.class, () -> LinearPreferenceImpl
                        .asLinearPreference(null, ImmutableList.of(null)));
        assertThrows(Exception.class, () -> LinearPreferenceImpl
                        .asLinearPreference(v1, ImmutableList.of(null)));
        assertThrows(Exception.class, () -> LinearPreferenceImpl
                        .asLinearPreference(v1, null));
        assertThrows(Exception.class,
                        () -> LinearPreferenceImpl.asLinearPreference(
                                        Voter.createVoter(1),
                                        ImmutableList.of(null)));
        assertThrows(Exception.class, () -> LinearPreferenceImpl
                        .asLinearPreference(v1, null));
        
    }

    @Test
    void sizeLinearTest() throws EmptySetException, DuplicateValueException {
        LinearPreference toTest = getListedAlternatives();
        assertEquals(3, toTest.sizeLinear(toTest.asList()));
    }
    
    @Test
    void createStrictCompletePreferenceTest() throws EmptySetException, DuplicateValueException {
        LinearPreferenceImpl toTest = (LinearPreferenceImpl) getListedAlternatives();
        Voter v = Voter.createVoter(3);
        assertEquals(toTest, LinearPreferenceImpl.createStrictCompletePreferenceImpl(v,toTest.asList()));
    }
    
    @Test
    void getAlternativeTest() throws EmptySetException, DuplicateValueException { 
    	LinearPreferenceImpl toTest = (LinearPreferenceImpl) getListedAlternatives();
    	assertEquals(a2,toTest.getAlternative(2));
    }
    
    @Test 
    void listAlternativeToListSetAlternativeTest() throws EmptySetException, DuplicateValueException { 
    	ImmutableList<ImmutableSet<Alternative>> toTest = ImmutableList.of(ImmutableSet.of(a1), ImmutableSet.of(a3),ImmutableSet.of(a2));
    	LinearPreferenceImpl toTest2 = (LinearPreferenceImpl) getListedAlternatives();
    	assertEquals(toTest,LinearPreferenceImpl.listAlternativeToListSetAlternative(toTest2.asList()));
    }
    
}
