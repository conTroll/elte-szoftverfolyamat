package hu.szoftverfolyamat.repository;

import java.util.List;

import hu.szoftverfolyamat.entity.ChannelProfileEntity;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.enums.SubscriberStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChannelRepository extends JpaRepository<ChannelProfileEntity, Long> {
	
	@Query("select s.id.channel from ChannelSubscriberEntity s where s.id.user = ?1 and s.status = ?2 order by s.id.channel.name")
	List<ChannelProfileEntity> getSubscriptionsByUserAndStatus(UserProfileData user, SubscriberStatus status);
	
	@Query("select s.id.channel from ChannelSubscriberEntity s where s.id.user = ?1 order by s.id.channel.name")
	List<ChannelProfileEntity> getSubscriptionsByUser(UserProfileData user);
	
	@Query("select s.id.user from ChannelSubscriberEntity s where s.id.channel = ?1 order by s.subscriptionDate")
	List<UserProfileData> getSubscribersByChannel(ChannelProfileEntity channel);
	
	@Query("select s.id.user from ChannelSubscriberEntity s where s.id.channel = ?1 and s.status = ?2 order by s.subscriptionDate")
	List<UserProfileData> getSubscribersByChannelAndStatus(ChannelProfileEntity channel, SubscriberStatus status);
	
	@Query("select p from ChannelProfileEntity p where p.name LIKE ?1 and p.open = true order by p.name")
	List<ChannelProfileEntity> searchOpenChannelsByName(String name);
	
	@Query("select p from ChannelProfileEntity p where (p.name LIKE ?1 or p.description LIKE ?1) and p.open = true order by p.name")
	List<ChannelProfileEntity> searchOpenChannelsByNameOrDescripton(String searchTerm);

    @Query("SELECT cpe FROM ChannelProfileEntity cpe WHERE :interestId IN cpe.interests")
    List<ChannelProfileEntity> findByInterest(final @Param("interestId") String interest);
}
