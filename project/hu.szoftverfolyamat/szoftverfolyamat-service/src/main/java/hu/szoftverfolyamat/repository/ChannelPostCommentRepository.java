package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.ChannelPostCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelPostCommentRepository extends JpaRepository<ChannelPostCommentEntity, Long> {
	
	// NOP

}
