package hu.szoftverfolyamat.repository;

import java.util.List;

import hu.szoftverfolyamat.entity.UserProfileData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileDataRepository extends JpaRepository<UserProfileData, Long> {
	
	@Query("select up from UserProfileData up where up.habitat = ?1 and up.publicHabitat = true")
	List<UserProfileData> findByHabitat(String habitat);
	
	@Query("select up from UserProfileData up where up.workplace = ?1 and up.publicJobAndWorkplace = true")
	List<UserProfileData> findByWorkplace(String workplace);
	
	@Query("select up from UserProfileData up where up.job = ?1 and up.publicJobAndWorkplace = true")
	List<UserProfileData> findByJob(String job);
	
}
