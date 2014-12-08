package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.UserCredential;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserCredentialsRepository extends
		JpaRepository<UserCredential, Long> {

	@Query("select uc from UserCredential uc left join fetch uc.userRole ur where uc.username = (:username)")
	public UserCredential getUserByUsername(@Param("username") String username);

}
