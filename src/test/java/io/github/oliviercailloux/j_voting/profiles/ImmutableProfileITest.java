package io.github.oliviercailloux.j_voting.profiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;


import io.github.oliviercailloux.j_voting.preferences.classes.CompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.preferences.interfaces.CompletePreference;
import io.github.oliviercailloux.j_voting.preferences.interfaces.ImmutablePreference;
import org.junit.jupiter.api.Test;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.OldCompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.Voter;
import io.github.oliviercailloux.j_voting.profiles.ImmutableProfileI;
import io.github.oliviercailloux.j_voting.profiles.ImmutableStrictProfile;
import io.github.oliviercailloux.j_voting.profiles.ProfileI;
import io.github.oliviercailloux.j_voting.profiles.management.ProfileBuilder;

public class ImmutableProfileITest {

    public static ImmutableProfileI createIPIToTest() throws Exception {
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
        return ImmutableProfileI.createImmutableProfileI(profile);
    }

    @Test
    public void testGetMaxSizeOfPreference() throws Exception {
        ImmutableProfileI ipi = createIPIToTest();
        Alternative a = Alternative.withId(4);
        Alternative a1 = Alternative.withId(5);
        Alternative a2 = Alternative.withId(6);
        Alternative a3 = Alternative.withId(7);
        List<Set<Alternative>> list = new ArrayList<>();
        Set<Alternative> s = new HashSet<>();
        s.add(a);
        s.add(a1);
        s.add(a2);
        s.add(a3);
        list.add(s);
        Voter v = ipi.getAllVoters().first();
        CompletePreferenceImpl pref = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v, list);
        ProfileBuilder pb = ProfileBuilder.createProfileBuilder(ipi);
        pb.addVote(v, pref);
        ipi = (ImmutableProfileI) pb.createProfileI();
        int max = ipi.getMaxSizeOfPreference();
        assertEquals(pref.getAlternatives().size(), max);
    }

    @Test
    public void testGetPreference() throws Exception {
        Alternative a1 = Alternative.withId(1);
        Alternative a2 = Alternative.withId(2);
        Alternative a3 = Alternative.withId(3);
        Voter v1 = Voter.createVoter(1);
        List<Set<Alternative>> list1 = new ArrayList<>();
        Set<Alternative> s1 = new HashSet<>();
        Set<Alternative> s2 = new HashSet<>();
        s1.add(a1);
        s1.add(a2);
        s2.add(a3);
        list1.add(s1);
        list1.add(s2);
        CompletePreferenceImpl prefToTest = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v1, list1);
        assertEquals(prefToTest, createIPIToTest().getPreference(v1));
    }

    @Test
    public void testContains() throws Exception {
        Voter v1 = Voter.createVoter(1);
        assertTrue(createIPIToTest().votes.containsKey(v1));
    }

    @Test
    public void testGetAllVoters() throws Exception {
        Voter v1 = Voter.createVoter(1);
        Voter v2 = Voter.createVoter(2);
        Voter v3 = Voter.createVoter(3);
        Voter v4 = Voter.createVoter(4);
        Voter v5 = Voter.createVoter(5);
        Voter v6 = Voter.createVoter(6);
        NavigableSet<Voter> set = new TreeSet<>();
        set.add(v1);
        set.add(v2);
        set.add(v3);
        set.add(v4);
        set.add(v5);
        set.add(v6);
        assertEquals(set, createIPIToTest().getAllVoters());
    }

    @Test
    public void testGetNbVoters() throws Exception {
        assertEquals(createIPIToTest().getNbVoters(), 6);
    }

    @Test
    public void testGetSumVoteCount() throws Exception {
        assertEquals(createIPIToTest().getSumVoteCount(), 6);
    }

    @Test
    public void testGetUniquePreferences() throws Exception {
        Alternative a1 = Alternative.withId(1);
        Alternative a2 = Alternative.withId(2);
        Alternative a3 = Alternative.withId(3);
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
        // TODO check
        Voter voterTest = Voter.createVoter(1);
        CompletePreferenceImpl pref1V1 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(voterTest, list1);
        CompletePreferenceImpl pref2V1 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(voterTest, list2);
        List<CompletePreferenceImpl> preferencelist = new ArrayList<>();
        for (CompletePreferenceImpl p : createIPIToTest().getUniquePreferences()) {
            preferencelist.add(p);
        }
        boolean case1 = preferencelist.get(0).asEquivalenceClasses().equals(pref1V1.asEquivalenceClasses())
                        && preferencelist.get(1).asEquivalenceClasses().equals(pref2V1.asEquivalenceClasses());
        boolean case2 = preferencelist.get(0).asEquivalenceClasses().equals(pref2V1.asEquivalenceClasses())
                        && preferencelist.get(1).asEquivalenceClasses().equals(pref1V1.asEquivalenceClasses());
        assertTrue(case1 || case2);
    }

    @Test
    public void testGetNbUniquePreferences() throws Exception {
        assertEquals(createIPIToTest().getNbUniquePreferences(), 2);
    }

    @Test
    public void testIsComplete() throws Exception {
        assertTrue(createIPIToTest().isComplete());
    }

    @Test
    public void testIsStrict() throws Exception {
        assertTrue(!createIPIToTest().isStrict());
    }

    @Test
    public void testGetNbVoterByPreference() throws Exception {
        Alternative a1 = Alternative.withId(1);
        Alternative a2 = Alternative.withId(2);
        Alternative a3 = Alternative.withId(3);
        List<Set<Alternative>> list1 = new ArrayList<>();
        Set<Alternative> s1 = new HashSet<>();
        Set<Alternative> s2 = new HashSet<>();
        s1.add(a1);
        s1.add(a2);
        s2.add(a3);
        list1.add(s1);
        list1.add(s2);
        Voter voterTest = Voter.createVoter(1);
        CompletePreferenceImpl pref1 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(voterTest, list1);
        assertEquals(createIPIToTest().getNbVoterForPreference(pref1), 4);
    }

    @Test
    public void testEqualsObject() throws Exception {
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
        ImmutableProfileI prof = ImmutableProfileI.createImmutableProfileI(profile);
        assertEquals(prof, createIPIToTest());
    }

    @Test
    public void testRestrictProfile() throws Exception {
        ProfileI prof = ImmutableStrictProfileTest.createISPToTest()
                        .restrictProfile();
        assertTrue(prof instanceof ImmutableStrictProfile);
    }
}
