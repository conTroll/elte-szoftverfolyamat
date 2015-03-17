package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	@Query("select ur from UserRole ur where ur.credentialId = (:credentialId)")
	public UserRole getByUserCredentialId(
			@Param("credentialId") Long credentialId);
}
