package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.ChannelSubscriberEntity;
import hu.szoftverfolyamat.entity.ChannelSubscriberEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelSubscriberRepository extends JpaRepository<ChannelSubscriberEntity, ChannelSubscriberEntityId> {
	
	//

}
