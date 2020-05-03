package io.github.oliviercailloux.j_voting.profiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.Voter;
import io.github.oliviercailloux.j_voting.preferences.classes.CompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.preferences.classes.LinearPreferenceImpl;
import io.github.oliviercailloux.j_voting.profiles.ImmutableStrictProfileI;

public class ImmutableStrictProfileITest {

    /**
     * 
     * @return an ImmutableStrictProfileI to test
     * @throws DuplicateValueException 
     * @throws EmptySetException 
     */
    public static ImmutableStrictProfileI createISPIToTest() throws Exception {
        Map<Voter, LinearPreferenceImpl> profile = new HashMap<>();
        Alternative a1 = Alternative.withId(1);
        Alternative a2 = Alternative.withId(2);
        Alternative a3 = Alternative.withId(3);
        Voter v1 = Voter.createVoter(1);
        Voter v2 = Voter.createVoter(2);
        Voter v3 = Voter.createVoter(3);
        Voter v4 = Voter.createVoter(4);
        Voter v5 = Voter.createVoter(5);
        Voter v6 = Voter.createVoter(6);
        ArrayList<Alternative> list1 = new ArrayList<>();
        ArrayList<Alternative> list2 = new ArrayList<>();
        list1.add(a1);
        list1.add(a2);
        list1.add(a3);
        list2.add(a3);
        list2.add(a2);
        LinearPreferenceImpl pref1V1 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v1, list1);
        LinearPreferenceImpl pref1V2 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v2, list1);
        LinearPreferenceImpl pref1V3 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v3, list1);
        LinearPreferenceImpl pref1V4 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v4, list1);
        LinearPreferenceImpl pref2V5 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v6, list2);
        LinearPreferenceImpl pref2V6 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v6, list2);
        profile.put(v1, pref1V1);
        profile.put(v2, pref1V2);
        profile.put(v3, pref1V3);
        profile.put(v4, pref1V4);
        profile.put(v5, pref2V5);
        profile.put(v6, pref2V6);
        return ImmutableStrictProfileI.createImmutableStrictProfileI(profile);
    }

    /**
     * 
     * @return a map of Voter and Preference to test
     * @throws EmptySetException 
     * @throws DuplicateValueException 
     */
    public static Map<Voter, CompletePreferenceImpl> createNonStrictMap() throws Exception {
        Map<Voter, CompletePreferenceImpl> map = new HashMap<>();
        Alternative a1 = Alternative.withId(1);
        Alternative a2 = Alternative.withId(2);
        Alternative a3 = Alternative.withId(3);
        Voter v1 = Voter.createVoter(1);
        Voter v2 = Voter.createVoter(2);
        Voter v3 = Voter.createVoter(3);
        Set<Alternative> s1 = new HashSet<>();
        Set<Alternative> s2 = new HashSet<>();
        Set<Alternative> s3 = new HashSet<>();
        s1.add(a1);
        s2.add(a2);
        s3.add(a3);
        List<Set<Alternative>> list1 = new ArrayList<>();
        List<Set<Alternative>> list2 = new ArrayList<>();
        list1.add(s1);
        list1.add(s2);
        list1.add(s3);
        list2.add(s3);
        list2.add(s2);
        list2.add(s1);
        CompletePreferenceImpl p1V1 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v1, list1);
        CompletePreferenceImpl p1V2 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v2, list1);
        CompletePreferenceImpl p2V3 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v3, list1);
        map.put(v1, p1V1);
        map.put(v2, p1V2);
        map.put(v3, p2V3);
        return map;
    }

    /**
     * 
     * @return a map of Voter and StrictPreference to test
     * @throws DuplicateValueException 
     * @throws EmptySetException 
     */
    public static Map<Voter, LinearPreferenceImpl> createStrictMap() throws Exception {
        Map<Voter, LinearPreferenceImpl> map = new HashMap<>();
        Alternative a1 = Alternative.withId(1);
        Alternative a2 = Alternative.withId(2);
        Alternative a3 = Alternative.withId(3);
        Voter v1 = Voter.createVoter(1);
        Voter v2 = Voter.createVoter(2);
        Voter v3 = Voter.createVoter(3);
        List<Alternative> list1 = new ArrayList<>();
        List<Alternative> list2 = new ArrayList<>();
        list1.add(a1);
        list1.add(a2);
        list1.add(a3);
        list2.add(a3);
        list2.add(a2);
        list2.add(a1);
        LinearPreferenceImpl p1V1 = (LinearPreferenceImpl)LinearPreferenceImpl.asLinearPreference(v1, list1);
        LinearPreferenceImpl p1V2 = (LinearPreferenceImpl)LinearPreferenceImpl.asLinearPreference(v2, list1);
        LinearPreferenceImpl p2V3 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v3, list1);
        map.put(v1, p1V1);
        map.put(v2, p1V2);
        map.put(v3, p2V3);
        return map;
    }

    @Test
    public void testGetPreferenceVoter() throws Exception {
        Alternative a1 = Alternative.withId(1);
        Alternative a2 = Alternative.withId(2);
        Alternative a3 = Alternative.withId(3);
        Voter v1 = Voter.createVoter(1);
        ArrayList<Alternative> list1 = new ArrayList<>();
        list1.add(a1);
        list1.add(a2);
        list1.add(a3);
        LinearPreferenceImpl pref1 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v1, list1);
        assertEquals(pref1, createISPIToTest().getPreference(v1));
    }
}
