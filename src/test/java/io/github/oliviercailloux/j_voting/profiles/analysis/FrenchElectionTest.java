package io.github.oliviercailloux.j_voting.profiles.analysis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.OldCompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.OldLinearPreferenceImpl;
import io.github.oliviercailloux.j_voting.Voter;
import io.github.oliviercailloux.j_voting.preferences.classes.CompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.preferences.classes.LinearPreferenceImpl;
import io.github.oliviercailloux.j_voting.profiles.ImmutableStrictProfileI;
import io.github.oliviercailloux.j_voting.profiles.analysis.OldFrenchElection;
import io.github.oliviercailloux.j_voting.profiles.management.StrictProfileBuilder;

public class FrenchElectionTest {

    @Test
    public void testGetSocietyPreference() throws Exception {
        Alternative a1 = Alternative.withId(1);
        Alternative a2 = Alternative.withId(2);
        Alternative a3 = Alternative.withId(3);
        Alternative a4 = Alternative.withId(4);
        List<Set<Alternative>> l1 = new ArrayList<>();
        Set<Alternative> s1 = new HashSet<>();
        Set<Alternative> s2 = new HashSet<>();
        Set<Alternative> s3 = new HashSet<>();
        s1.add(a1);
        s2.add(a2);
        s3.add(a3);
        l1.add(s1);
        l1.add(s2);
        l1.add(s3);
        List<Alternative> list1 = new ArrayList<>();
        List<Alternative> list2 = new ArrayList<>();
        List<Alternative> list3 = new ArrayList<>();
        list1.add(a1);
        list1.add(a2);
        list1.add(a3);
        list2.add(a2);
        list2.add(a1);
        list2.add(a3);
        list2.add(a4);
        list3.add(a3);
        list3.add(a2);
        list3.add(a4);
        Voter v1 = Voter.createVoter(1);
        Voter v2 = Voter.createVoter(2);
        Voter v3 = Voter.createVoter(3);
        Voter v4 = Voter.createVoter(4);
        Voter v5 = Voter.createVoter(5);
        Voter v6 = Voter.createVoter(6);
        LinearPreferenceImpl p1V1 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v1, list1);
        LinearPreferenceImpl p1V2 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v2, list1);
        LinearPreferenceImpl p1V3 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v3, list1);
        LinearPreferenceImpl p2V4 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v4, list2);
        LinearPreferenceImpl p2V5 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v5, list2);
        LinearPreferenceImpl p3V6 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v6, list3);
        CompletePreferenceImpl pref1 = (CompletePreferenceImpl) CompletePreferenceImpl.asCompletePreference(v1, l1);
        StrictProfileBuilder profBuild = StrictProfileBuilder.createStrictProfileBuilder();
        profBuild.addVote(v1, p1V1);
        profBuild.addVote(v2, p1V2);
        profBuild.addVote(v3, p1V3);
        profBuild.addVote(v4, p2V4);
        profBuild.addVote(v5, p2V5);
        profBuild.addVote(v6, p3V6);
        ImmutableStrictProfileI resultProf = (ImmutableStrictProfileI) profBuild
                        .createStrictProfileI();
        assertEquals(pref1,
                        new OldFrenchElection().getSocietyPreference(resultProf));
    }
}
