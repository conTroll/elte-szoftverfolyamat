package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.InterestEntity;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.enums.RecommendBase;
import hu.szoftverfolyamat.service.mapper.UserProfileDataMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecommendService {
	
	@Autowired
	private UserProfileDataService userService;
	
	@Autowired
	private UserProfileDataMapper userMapper;
	
	@Autowired
	private InterestService interestService;

	/**
	 * Ajánlott ismerősöket ad vissza egy felhasználó számára.
	 * @param userId
	 * 		a felhasználó azonosítója akinek az ajánlások készülnek
	 * @param basedOn
	 * 		mi alapján ajánljon ismerősöket.. több szempont is megadható
	 * @return
	 * 		egy <code>Map</code>-et, amelyben az egyes (kért) szempontok szerint csoportosítva találhatók meg az ajánlott ismerősök
	 */
	public Map<String, List<UserProfileDataDto>> recommendFriends(Long userId, Set<RecommendBase> basedOn){
		
		if (userId == null) {
			throw new IllegalArgumentException("userId is mandatory");
		}
		
		if (basedOn == null || basedOn.isEmpty()) {
			throw new IllegalArgumentException("basedOn is mandatory, and has to contain at least one element");
		}
		
		UserProfileData profile = this.userService.findByUserCredentialId(userId);
		
		if(profile == null) {
			throw new IllegalArgumentException("invalid userId: no such user");
		}
		
		Map<String, List<UserProfileDataDto>> result = new HashMap<>();
		
		if(basedOn.contains(RecommendBase.COMMON_INTERESTS)) {
			List<UserProfileDataDto> usersWithCommonInterests = new ArrayList<>(this.getUsersWithInterests(profile.getCredentialId(), profile.getInterests()));
			result.put(RecommendBase.COMMON_INTERESTS.name(), usersWithCommonInterests);
		}
		
		if(basedOn.contains(RecommendBase.COMMON_HABITAT) && profile.getHabitat() != null && !("".equals(profile.getHabitat().trim()))) {
			List<UserProfileDataDto> usersWithCommonHabitat = this.userService.findByHabitat(profile.getHabitat());
			if(!usersWithCommonHabitat.isEmpty()) {
				result.put(RecommendBase.COMMON_HABITAT.name(), usersWithCommonHabitat);
			}
		}
		
		if(basedOn.contains(RecommendBase.COMMON_JOB) && profile.getJob() != null && !("".equals(profile.getJob().trim()))) {
			List<UserProfileDataDto> usersWithCommonJob = this.userService.findByJob(profile.getJob());
			if(!usersWithCommonJob.isEmpty()) {
				result.put(RecommendBase.COMMON_JOB.name(), usersWithCommonJob);
			}
		}
		
		if(basedOn.contains(RecommendBase.COMMON_WORKPLACE) && profile.getWorkplace() != null && !("".equals(profile.getWorkplace().trim()))) {
			List<UserProfileDataDto> usersWithCommonWorkplace = this.userService.findByWorkplace(profile.getWorkplace());
			if(!usersWithCommonWorkplace.isEmpty()) {
				result.put(RecommendBase.COMMON_WORKPLACE.name(), usersWithCommonWorkplace);
			}
		}
		
		return result;
		
	}
	
	private Set<UserProfileDataDto> getUsersWithInterests(Long ownUserId, List<InterestEntity> interests){
		
		Set<UserProfileDataDto> result = new HashSet<>();
		
		for(InterestEntity interest : interests) {
			List<UserProfileDataDto> users = this.interestService.getUsersForInterest(ownUserId, interest.getId());
			if(users != null) {
				for(UserProfileDataDto user : users) {
					if(user.getCredentialId().longValue() != ownUserId.longValue()) {
						result.add(user);
					}
				}
			}
		}
		
		return result;
		
	}

}
