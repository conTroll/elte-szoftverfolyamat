package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

	@Query("select pe from PostEntity pe left join fetch pe.userProfileData upd where upd.credentialId = (:authorId)  order by pe.creationDate DESC")
	public List<PostEntity> findByAuthorId(@Param("authorId") long authorId);

	@Query("select pe from PostEntity pe left join fetch pe.userProfileData upd where upd.credentialId in (:authorIds) order by pe.creationDate DESC")
	public List<PostEntity> findByAuthorIds(@Param("authorIds") List<Long> authorIds);
}
