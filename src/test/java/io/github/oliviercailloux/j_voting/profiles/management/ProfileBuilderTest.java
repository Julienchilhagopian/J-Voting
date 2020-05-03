package io.github.oliviercailloux.j_voting.profiles.management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.oliviercailloux.j_voting.exceptions.DuplicateValueException;
import io.github.oliviercailloux.j_voting.exceptions.EmptySetException;
import io.github.oliviercailloux.j_voting.preferences.classes.CompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.preferences.classes.LinearPreferenceImpl;
import org.junit.jupiter.api.Test;

import io.github.oliviercailloux.j_voting.Alternative;
import io.github.oliviercailloux.j_voting.OldCompletePreferenceImpl;
import io.github.oliviercailloux.j_voting.OldLinearPreferenceImpl;
import io.github.oliviercailloux.j_voting.Voter;
import io.github.oliviercailloux.j_voting.profiles.ImmutableProfile;
import io.github.oliviercailloux.j_voting.profiles.ImmutableProfileI;
import io.github.oliviercailloux.j_voting.profiles.ImmutableProfileITest;
import io.github.oliviercailloux.j_voting.profiles.ImmutableProfileTest;
import io.github.oliviercailloux.j_voting.profiles.ImmutableStrictProfile;
import io.github.oliviercailloux.j_voting.profiles.ImmutableStrictProfileI;
import io.github.oliviercailloux.j_voting.profiles.ImmutableStrictProfileITest;
import io.github.oliviercailloux.j_voting.profiles.ImmutableStrictProfileTest;
import io.github.oliviercailloux.j_voting.profiles.Profile;
import io.github.oliviercailloux.j_voting.profiles.ProfileI;
import io.github.oliviercailloux.j_voting.profiles.StrictProfile;
import io.github.oliviercailloux.j_voting.profiles.StrictProfileI;
import io.github.oliviercailloux.j_voting.profiles.management.ProfileBuilder;

public class ProfileBuilderTest {

    @Test
    public void testCreateProfileI() throws DuplicateValueException, EmptySetException {
        // ---beginning of creation of a ProfileI with ProfileBuilder
        ProfileBuilder profileBuilder = ProfileBuilder.createProfileBuilder();
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
        profileBuilder.addVote(v1, pref1V1);
        profileBuilder.addVote(v2, pref1V2);
        profileBuilder.addVote(v3, pref1V3);
        profileBuilder.addVote(v4, pref1V4);
        profileBuilder.addVote(v5, pref2V5);
        profileBuilder.addVote(v6, pref2V6);
        ProfileI immutableProfileI = profileBuilder.createProfileI();
        // ---end of creation of a ProfileI with ProfileBuilder
        ImmutableProfileI testProfileI = ImmutableProfileITest
                        .createIPIToTest();
        assertEquals(immutableProfileI, testProfileI);
    }

    @Test
    public void testCreateProfile() throws Exception {
        // ---beginning of creation of a Profile with ProfileBuilder
        ProfileBuilder profileBuilder = ProfileBuilder.createProfileBuilder();
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
        profileBuilder.addVote(v1, pref1V1);
        profileBuilder.addVote(v2, pref1V2);
        profileBuilder.addVote(v3, pref1V3);
        profileBuilder.addVote(v4, pref1V4);
        profileBuilder.addVote(v5, pref2V5);
        profileBuilder.addVote(v6, pref2V6);
        Profile immutableProfile = profileBuilder.createProfile();
        // ---end of creation of a Profile with ProfileBuilder
        ImmutableProfile testProfile = ImmutableProfileTest.createIPToTest();
        assertEquals(immutableProfile, testProfile);
    }

    @Test
    public void testCreateStrictProfileI() throws Exception {
        // ---beginning of creation of a StrictProfileI with ProfileBuilder
        ProfileBuilder profileBuilder = ProfileBuilder.createProfileBuilder();
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
        LinearPreferenceImpl pref2V5 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v5, list2);
        LinearPreferenceImpl pref2V6 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v6, list2);
        profileBuilder.addVote(v1, pref1V1);
        profileBuilder.addVote(v2, pref1V2);
        profileBuilder.addVote(v3, pref1V3);
        profileBuilder.addVote(v4, pref1V4);
        profileBuilder.addVote(v5, pref2V5);
        profileBuilder.addVote(v6, pref2V6);
        StrictProfileI immutableStrictProfileI = profileBuilder
                        .createStrictProfileI();
        // ---end of creation of a StrictProfileI with ProfileBuilder
        ImmutableStrictProfileI testStrictProfileI = ImmutableStrictProfileITest
                        .createISPIToTest();
        assertEquals(immutableStrictProfileI, testStrictProfileI);
    }

    @Test
    public void testCreateStrictProfile() throws Exception {
        // ---beginning of creation of a StrictProfile with ProfileBuilder
        ProfileBuilder profileBuilder = ProfileBuilder.createProfileBuilder();
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
        list2.add(a1);
        LinearPreferenceImpl pref1V1 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v1, list1);
        LinearPreferenceImpl pref1V2 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v2, list1);
        LinearPreferenceImpl pref1V3 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v3, list1);
        LinearPreferenceImpl pref1V4 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v4, list1);
        LinearPreferenceImpl pref2V5 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v5, list2);
        LinearPreferenceImpl pref2V6 = (LinearPreferenceImpl) LinearPreferenceImpl.asLinearPreference(v6, list2);
        profileBuilder.addVote(v1, pref1V1);
        profileBuilder.addVote(v2, pref1V2);
        profileBuilder.addVote(v3, pref1V3);
        profileBuilder.addVote(v4, pref1V4);
        profileBuilder.addVote(v5, pref2V5);
        profileBuilder.addVote(v6, pref2V6);
        StrictProfile immutableStrictProfile = profileBuilder
                        .createStrictProfile();
        // ---end of creation of a StrictProfile with ProfileBuilder
        ImmutableStrictProfile testStrictProfile = ImmutableStrictProfileTest
                        .createISPToTest();
        assertEquals(immutableStrictProfile, testStrictProfile);
    }
}
