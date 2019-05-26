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

import org.junit.jupiter.api.Test;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.CompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.Voter;
import io.github.oliviercailloux.j_voting.profiles.ImmutableProfileI;
import io.github.oliviercailloux.j_voting.profiles.ImmutableStrictProfile;
import io.github.oliviercailloux.j_voting.profiles.ProfileI;
import io.github.oliviercailloux.j_voting.profiles.management.ProfileBuilder;

public class ImmutableProfileITest {

    public static ImmutableProfileI createIPIToTest() {
        Map<Voter, CompletePreferenceImpl> profile = new HashMap<>();
        Alternative a1 = Alternative.createAlternative(1);
        Alternative a2 = Alternative.createAlternative(2);
        Alternative a3 = Alternative.createAlternative(3);
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
        CompletePreferenceImpl pref1 = CompletePreferenceImpl.createCompletePreferenceImpl(list1);
        CompletePreferenceImpl pref2 = CompletePreferenceImpl.createCompletePreferenceImpl(list2);
        profile.put(v1, pref1);
        profile.put(v2, pref1);
        profile.put(v3, pref1);
        profile.put(v4, pref1);
        profile.put(v5, pref2);
        profile.put(v6, pref2);
        return ImmutableProfileI.createImmutableProfileI(profile);
    }

    @Test
    public void testGetMaxSizeOfPreference() {
        ImmutableProfileI ipi = createIPIToTest();
        Alternative a = Alternative.createAlternative(4);
        Alternative a1 = Alternative.createAlternative(5);
        Alternative a2 = Alternative.createAlternative(6);
        Alternative a3 = Alternative.createAlternative(7);
        List<Set<Alternative>> list = new ArrayList<>();
        Set<Alternative> s = new HashSet<>();
        s.add(a);
        s.add(a1);
        s.add(a2);
        s.add(a3);
        list.add(s);
        CompletePreferenceImpl pref = CompletePreferenceImpl.createCompletePreferenceImpl(list);
        ProfileBuilder pb = ProfileBuilder.createProfileBuilder(ipi);
        Voter v = ipi.getAllVoters().first();
        pb.addVote(v, pref);
        ipi = (ImmutableProfileI) pb.createProfileI();
        int max = ipi.getMaxSizeOfPreference();
        assertEquals(pref.size(), max);
    }

    @Test
    public void testGetPreference() {
        Alternative a1 = Alternative.createAlternative(1);
        Alternative a2 = Alternative.createAlternative(2);
        Alternative a3 = Alternative.createAlternative(3);
        Voter v1 = Voter.createVoter(1);
        List<Set<Alternative>> list1 = new ArrayList<>();
        Set<Alternative> s1 = new HashSet<>();
        Set<Alternative> s2 = new HashSet<>();
        s1.add(a1);
        s1.add(a2);
        s2.add(a3);
        list1.add(s1);
        list1.add(s2);
        CompletePreferenceImpl pref1 = CompletePreferenceImpl.createCompletePreferenceImpl(list1);
        assertEquals(pref1, createIPIToTest().getPreference(v1));
    }

    @Test
    public void testContains() {
        Voter v1 = Voter.createVoter(1);
        assertTrue(createIPIToTest().votes.containsKey(v1));
    }

    @Test
    public void testGetAllVoters() {
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
    public void testGetNbVoters() {
        assertEquals(createIPIToTest().getNbVoters(), 6);
    }

    @Test
    public void testGetSumVoteCount() {
        assertEquals(createIPIToTest().getSumVoteCount(), 6);
    }

    @Test
    public void testGetUniquePreferences() {
        Alternative a1 = Alternative.createAlternative(1);
        Alternative a2 = Alternative.createAlternative(2);
        Alternative a3 = Alternative.createAlternative(3);
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
        CompletePreferenceImpl pref1 = CompletePreferenceImpl.createCompletePreferenceImpl(list1);
        CompletePreferenceImpl pref2 = CompletePreferenceImpl.createCompletePreferenceImpl(list2);
        List<CompletePreferenceImpl> preferencelist = new ArrayList<>();
        for (CompletePreferenceImpl p : createIPIToTest().getUniquePreferences()) {
            preferencelist.add(p);
        }
        boolean case1 = preferencelist.get(0).equals(pref1)
                        && preferencelist.get(1).equals(pref2);
        boolean case2 = preferencelist.get(0).equals(pref2)
                        && preferencelist.get(1).equals(pref1);
        assertTrue(case1 || case2);
    }

    @Test
    public void testGetNbUniquePreferences() {
        assertEquals(createIPIToTest().getNbUniquePreferences(), 2);
    }

    @Test
    public void testIsComplete() {
        assertTrue(createIPIToTest().isComplete());
    }

    @Test
    public void testIsStrict() {
        assertTrue(!createIPIToTest().isStrict());
    }

    @Test
    public void testGetNbVoterByPreference() {
        Alternative a1 = Alternative.createAlternative(1);
        Alternative a2 = Alternative.createAlternative(2);
        Alternative a3 = Alternative.createAlternative(3);
        List<Set<Alternative>> list1 = new ArrayList<>();
        Set<Alternative> s1 = new HashSet<>();
        Set<Alternative> s2 = new HashSet<>();
        s1.add(a1);
        s1.add(a2);
        s2.add(a3);
        list1.add(s1);
        list1.add(s2);
        CompletePreferenceImpl pref1 = CompletePreferenceImpl.createCompletePreferenceImpl(list1);
        assertEquals(createIPIToTest().getNbVoterForPreference(pref1), 4);
    }

    @Test
    public void testEqualsObject() {
        Map<Voter, CompletePreferenceImpl> profile = new HashMap<>();
        Alternative a1 = Alternative.createAlternative(1);
        Alternative a2 = Alternative.createAlternative(2);
        Alternative a3 = Alternative.createAlternative(3);
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
        CompletePreferenceImpl pref1 = CompletePreferenceImpl.createCompletePreferenceImpl(list1);
        CompletePreferenceImpl pref2 = CompletePreferenceImpl.createCompletePreferenceImpl(list2);
        profile.put(v1, pref1);
        profile.put(v2, pref1);
        profile.put(v3, pref1);
        profile.put(v4, pref1);
        profile.put(v5, pref2);
        profile.put(v6, pref2);
        ImmutableProfileI prof = ImmutableProfileI.createImmutableProfileI(profile);
        assertEquals(prof, createIPIToTest());
    }

    @Test
    public void testRestrictProfile() {
        ProfileI prof = ImmutableStrictProfileTest.createISPToTest()
                        .restrictProfile();
        assertTrue(prof instanceof ImmutableStrictProfile);
    }
}
