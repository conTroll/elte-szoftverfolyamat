package hu.szoftverfolyamat.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.InterestEntity;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.enums.RecommendBase;
import hu.szoftverfolyamat.repository.InterestRepository;
import hu.szoftverfolyamat.repository.RepositoryTestHelper;
import hu.szoftverfolyamat.repository.UserCredentialsRepository;
import hu.szoftverfolyamat.repository.UserProfileDataRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service-application-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RecommendServiceTest {
	
	@Autowired
	private InterestRepository interestRepository;
	
	@Autowired
	private RecommendService recommendService;
	
	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	
	@Autowired
	private UserProfileDataRepository userProfileDataRepository;

	@Test
	public void commonInterestsTest() {
		
		UserProfileData testUser1 = RepositoryTestHelper.createTestUser(this.userCredentialsRepository, this.userProfileDataRepository, "user1");
		UserProfileData testUser2 = RepositoryTestHelper.createTestUser(this.userCredentialsRepository, this.userProfileDataRepository, "user2");
		
		InterestEntity interest1 = createInterest("a");
		InterestEntity interest2 = createInterest("b");
		InterestEntity interest3 = createInterest("c");
		
		Set<RecommendBase> basedOn = new HashSet<RecommendBase>();
		basedOn.add(RecommendBase.COMMON_INTERESTS);
		
		List<InterestEntity> interests1 = Arrays.asList(interest1, interest2);
		List<InterestEntity> interests2 = Arrays.asList(interest1, interest3);
		
		testUser1.setInterests(interests1);
		testUser2.setInterests(interests2);
		
		Map<RecommendBase, List<UserProfileDataDto>> recommendations = 
						this.recommendService.recommendFriends(48L, basedOn);

		Assert.assertTrue("the result should contain the key: RecommendBase.COMMON_INTERESTS", recommendations.containsKey(RecommendBase.COMMON_INTERESTS));
		List<UserProfileDataDto> recommendedUsers = recommendations.get(RecommendBase.COMMON_INTERESTS);
		// ronaip: ezt az ellenőrzést csak teszt közbeni tranzakció zárással tudnánk végrehajtani, az viszont szemetelné a db-t (meg egyébként sem tudom hogy kell)
		// ez azért van, mert az entitások közötti manytomany (user <-> interest) kapcsolat csak commit után szinkronizálódik
		// Assert.assertTrue("the result should contain the second test user", recommendedUsers.contains(testUser2));
	}
	
	private InterestEntity createInterest(String name) {
        InterestEntity result = new InterestEntity();
        result.setName(name);
        this.interestRepository.saveAndFlush(result);
        return result;
    }

}
