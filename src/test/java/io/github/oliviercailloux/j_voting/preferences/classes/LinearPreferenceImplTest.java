package io.github.oliviercailloux.j_voting.preferences.classes;

import static io.github.oliviercailloux.j_voting.AlternativeHelper.a1;
import static io.github.oliviercailloux.j_voting.AlternativeHelper.a2;
import static io.github.oliviercailloux.j_voting.AlternativeHelper.a3;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;

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
    void equalsTest() throws Exception {
    	LinearPreference toTest = getListedAlternatives();
    	LinearPreference toTest2 = LinearPreferenceImpl.asLinearPreference(Voter.createVoter(3), ImmutableList.of(a1, a2, a3));
    	LinearPreference toTest3 = LinearPreferenceImpl.asLinearPreference(Voter.createVoter(1), ImmutableList.of(a1, a3, a2));
    	LinearPreference toTest4 = LinearPreferenceImpl.asLinearPreference(Voter.createVoter(3), ImmutableList.of(a1, a3, a2));
        assertNotEquals(toTest, toTest2);
        assertNotEquals(toTest, toTest3);
        assertEquals(toTest, toTest4);
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
}
