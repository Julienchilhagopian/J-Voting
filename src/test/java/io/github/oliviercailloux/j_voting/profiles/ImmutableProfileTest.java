package io.github.oliviercailloux.j_voting.profiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.oliviercailloux.j_voting.exceptions.DuplicateValueException;
import io.github.oliviercailloux.j_voting.exceptions.EmptySetException;
import io.github.oliviercailloux.j_voting.preferences.classes.CompletePreferenceImpl;
import org.junit.jupiter.api.Test;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.OldCompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.Voter;
import io.github.oliviercailloux.j_voting.profiles.ImmutableProfile;

public class ImmutableProfileTest {

    public static ImmutableProfile createIPToTest() throws EmptySetException, DuplicateValueException {
        Map<Voter, CompletePreferenceImpl> profile = new HashMap<>();
        Alternative a1 = Alternative.withId(1);
        Alternative a2 = Alternative.withId(2);
        Alternative a3 = Alternative.withId(3);
        Voter v1 = Voter.createVoter(1);
        Voter v2 = Voter.createVoter(2);
        Voter v3 = Voter.createVoter(3);
        Voter v4 = Voter.createVoter(4);
        Voter v5 = Voter.createVoter(5);
        Voter v6 = Voter.createVoter(6);
        List<Set<Alternative>> list1 = new ArrayList<>();
        List<Set<Alternative>> list2 = new ArrayList<>();
        Set<Alternative> s1 = new HashSet<>();
        Set<Alternative> s2 = new HashSet<>();
        Set<Alternative> s3 = new HashSet<>();
        Set<Alternative> s4 = new HashSet<>();
        s1.add(a1);
        s1.add(a2);
        s2.add(a3);
        s3.add(a2);
        s4.add(a1);
        s4.add(a3);
        list1.add(s1);
        list1.add(s2);
        list2.add(s3);
        list2.add(s4);
        CompletePreferenceImpl pref1V1 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v1, list1);
        CompletePreferenceImpl pref1V2 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v2, list1);
        CompletePreferenceImpl pref1V3 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v3, list1);
        CompletePreferenceImpl pref1V4 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v4, list1);
        CompletePreferenceImpl pref2V5 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v5, list2);
        CompletePreferenceImpl pref2V6 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v6, list2);
        profile.put(v1, pref1V1);
        profile.put(v2, pref1V2);
        profile.put(v3, pref1V3);
        profile.put(v4, pref1V4);
        profile.put(v5, pref2V5);
        profile.put(v6, pref2V6);
        return ImmutableProfile.createImmutableProfile(profile);
    }

    @Test
    public void testGetNbAlternatives() throws Exception {
        assertEquals(createIPToTest().getNbAlternatives(), 3);
    }

    @Test
    public void testGetAlternatives() throws Exception {
        Set<Alternative> alters = new HashSet<>();
        Alternative a1 = Alternative.withId(1);
        Alternative a2 = Alternative.withId(2);
        Alternative a3 = Alternative.withId(3);
        alters.add(a1);
        alters.add(a2);
        alters.add(a3);
        assertEquals(alters, createIPToTest().getAlternatives());
    }
}
