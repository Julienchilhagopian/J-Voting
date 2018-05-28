package io.github.oliviercailloux.y2018.j_voting;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import com.google.common.collect.*;

public class BordaTest {

	public static ImmutableProfileI createIPIToTest() {
		Map<Voter,Preference> profile = new HashMap<>();
		Alternative a1 = new Alternative(1);
		Alternative a2 = new Alternative(2);
		Alternative a3 = new Alternative(3);
		Voter v1 = new Voter(1);
		Voter v2 = new Voter(2);
		Voter v3 = new Voter(3);
		Voter v4 = new Voter(4);
		Voter v5 = new Voter(5);
		Voter v6 = new Voter(6);
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
		Preference pref1 = new Preference(list1);
		Preference pref2 = new Preference(list2);
		profile.put(v1, pref1);
		profile.put(v2, pref1);
		profile.put(v3, pref1);
		profile.put(v4, pref1);
		profile.put(v5, pref2);
		profile.put(v6, pref2);
		return new ImmutableProfileI(profile);
	}
	@Test
	public void testgetSocietyPreference(){
		ProfileI prof = createIPIToTest();
		Alternative a1 = new Alternative(1);
		Alternative a2 = new Alternative(2);
		Alternative a3 = new Alternative(3);
		List<Set<Alternative>> list1 = new ArrayList<>();
		Set<Alternative> s1 = new HashSet<>();
		Set<Alternative> s2 = new HashSet<>();
		Set<Alternative> s3 = new HashSet<>();
		s1.add(a1);
		s2.add(a2);
		s3.add(a3);
		list1.add(s2);
		list1.add(s1);
		list1.add(s3);		
		Preference pref1 = new Preference(list1);
		assertEquals(new Borda().getSocietyPreference(prof),pref1);
	}
	
	@Test
	public void testSetScoresPref() {
		Borda b = new Borda();
		Alternative a1 = new Alternative(1);
		Alternative a2 = new Alternative(2);
		Alternative a3 = new Alternative(3);
		List<Set<Alternative>> list1 = new ArrayList<>();
		Set<Alternative> s1 = new HashSet<>();
		Set<Alternative> s2 = new HashSet<>();
		s1.add(a1);
		s1.add(a2);
		s2.add(a3);
		list1.add(s1);
		list1.add(s2);		
		Preference pref1 = new Preference(list1);
		b.setScores(pref1);
		Multiset<Alternative> m = b.getMultiSet();
		assertTrue(m.count(a1) == 2 && m.count(a2) == 2 && m.count(a3) == 1);
	}
	
	@Test
	public void testSetScoresProfile() {
		Borda b = new Borda();
		ProfileI p = createIPIToTest();
		Alternative a1 = new Alternative(1);
		Alternative a2 = new Alternative(2);
		Alternative a3 = new Alternative(3);
		b.setScores(p);
		Multiset<Alternative> m = b.getMultiSet();
		assertTrue(m.count(a1) == 10 && m.count(a2) == 12 && m.count(a3) == 6);
	}
	
	@Test
	public void testgetMax(){
		HashMultiset<Alternative> listScores = HashMultiset.create();
		Alternative a1 = new Alternative(1);
		Alternative a2 = new Alternative(2);
		Alternative a3 = new Alternative(3);
		listScores.add(a1,7);
		listScores.add(a2,5);
		listScores.add(a3,7);
		Set<Alternative> set = new HashSet<>();
		set.add(a1);
		set.add(a3);
		assertTrue(Preference.alternativeSetEqual(new Borda().getMax(listScores),set));
	}
	
	@Test
	public void testEquals(){
		HashMultiset<Alternative> mset1 = HashMultiset.create();
		HashMultiset<Alternative> mset2 = HashMultiset.create();
		HashMultiset<Alternative> mset3 = HashMultiset.create();
		Alternative a1 = new Alternative(1);
		Alternative a2 = new Alternative(2);
		Alternative a3 = new Alternative(3);
		mset1.add(a1,2);
		mset1.add(a2,1);
		mset1.add(a3,5);
		mset2.add(a1,2);
		mset2.add(a2,1);
		mset2.add(a3,5);
		mset3.add(a1,1);
		mset3.add(a2,2);
		mset3.add(a3,3);
		Borda b1 = new Borda(mset1);
		Borda b2 = new Borda(mset2);
		Borda b3 = new Borda(mset3);
		assertTrue(b1.equals(b2));
		assertTrue(!(b1.equals(b3)));
	}
}

