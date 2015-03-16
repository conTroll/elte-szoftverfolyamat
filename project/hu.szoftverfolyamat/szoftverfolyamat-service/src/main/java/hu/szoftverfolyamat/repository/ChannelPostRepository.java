package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.ChannelPostEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelPostRepository extends JpaRepository<ChannelPostEntity, Long> {
	
	// NOP

}
