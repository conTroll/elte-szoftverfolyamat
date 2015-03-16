package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.UserConnectionEntity;
import hu.szoftverfolyamat.entity.UserConnectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserConnectionsRepository extends
		JpaRepository<UserConnectionEntity, UserConnectionId> {

	@Query("select uce.credentialId2 from UserConnectionEntity uce where uce.credentialId1 = (:credentialId)")
	public List<Long> getFriendsIdByUserCredentialId(
			@Param("credentialId") Long credentialId);
}
