package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.enums.RecommendBase;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecommendService {

	/**
	 * Ajánlott ismerősöket ad vissza egy felhasználó számára.
	 * @param userId
	 * 		a felhasználó azonosítója akinek az ajánlások készülnek
	 * @param basedOn
	 * 		mi alapján ajánljon ismerősöket.. több szempont is megadható
	 * @return
	 * 		egy <code>Map</code>-et, amelyben az egyes (kért) szempontok szerint csoportosítva találhatók meg az ajánlott ismerősök
	 */
	public Map<RecommendBase, List<UserProfileDataDto>> recommendFriends(Long userId, Set<RecommendBase> basedOn){
		
		throw new UnsupportedOperationException();
		
	}

}
