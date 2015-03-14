package hu.szoftverfolyamat.repository;

import java.util.List;

import hu.szoftverfolyamat.entity.ChannelProfileEntity;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.enums.SubscriberStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChannelRepository extends JpaRepository<ChannelProfileEntity, Long> {
	
	@Query("select s.channel from ChannelSubscriberEntity s where s.user = :user and s.status = :status order by s.channel.name")
	List<ChannelProfileEntity> getSubscriptionsByUserAndStatus(UserProfileData user, SubscriberStatus status);
	
	@Query("select s.channel from ChannelSubscriberEntity s where s.user = :user order by s.channel.name")
	List<ChannelProfileEntity> getSubscriptionsByUser(UserProfileData user);
	
	@Query("select s.user from ChannelSubscriberEntity s where s.channel = :channel order by s.subscriptionDate")
	List<UserProfileData> getSubscribersByChannel(ChannelProfileEntity channel);
	
	@Query("select s.user from ChannelSubscriberEntity s where s.channel = :channel and s.status = :status order by s.subscriptionDate")
	List<UserProfileData> getSubscribersByChannelAndStatus(ChannelProfileEntity channel, SubscriberStatus status);
	
	@Query("select p from ChannelProfileEntity p where p.name LIKE :name order by p.name")
	List<ChannelProfileEntity> searchChannelsByName(String name);

}
