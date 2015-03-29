package hu.szoftverfolyamat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szoftverfolyamat.dto.ChannelProfileDto;
import hu.szoftverfolyamat.entity.ChannelProfileEntity;
import hu.szoftverfolyamat.entity.ChannelSubscriberEntity;
import hu.szoftverfolyamat.entity.ChannelSubscriberEntityId;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.enums.MatchType;
import hu.szoftverfolyamat.enums.SubscriberStatus;
import hu.szoftverfolyamat.exception.ChannelServiceException;
import hu.szoftverfolyamat.repository.ChannelPostRepository;
import hu.szoftverfolyamat.repository.ChannelRepository;
import hu.szoftverfolyamat.repository.ChannelSubscriberRepository;
import hu.szoftverfolyamat.service.mapper.ChannelProfileMapper;

@Service
@Transactional
public class ChannelService {

	@Autowired
	private ChannelRepository channelRepo;

	@Autowired
	private ChannelPostRepository channelPostRepo;

	@Autowired
	private ChannelSubscriberRepository channelSubscriberRepository;

	@Autowired
	private UserProfileDataService userService;

	@Autowired
	private ChannelProfileMapper channelMapper;


	/**
	 * Csatorna létrehozása
	 * 
	 * @param leaderId
	 * 		létrehozó felhasználó azonosítója
	 * @param name
	 * 		létrehozandó csatorna neve
	 * @param description
	 * 		létrehozandó csatorna leírása
	 * @param open
	 * 		nyitva van-e a csatorna (lehet-e rá feliratkozni)
	 * @return
	 * 		a létrehozott csatorna azonosítója (<code>channelId</code>)
	 * 
	 * @throws ChannelServiceException
	 * 		ha a <code>name</code>, vagy <code>leaderId</code> nincs megadva, vagy érvénytelen
	 */
	public Long createChannel(Long leaderId, String name, String description, boolean open) throws ChannelServiceException {

		if(name == null || "".equals(name.trim())) {
			throw new ChannelServiceException("name field is mandatory");
		}

		if(leaderId == null) {
			throw new ChannelServiceException("leaderId argument is mandatory");
		}

		UserProfileData leader = this.userService.findByUserCredentialId(leaderId);

		if(leader == null) {
			throw new ChannelServiceException("invalid leaderId: no such user");
		}

		ChannelProfileEntity channel = new ChannelProfileEntity();
		channel.setCreationDate(new Date());
		channel.setDescription(description);
		channel.setLeader(leader);
		channel.setName(name);
		channel.setOpen(open);

		channel = this.channelRepo.save(channel);
		return channel.getId();

	}


	/**
	 * Csatorna törlése.
	 * 
	 * @param channelId
	 * 		a törlendő csatorna azonosítja
	 * @throws ChannelServiceException
	 * 		ha csatorna azonosítója nincs megadva, vagy nem érvényes
	 */
	public void deleteChannel(Long channelId) throws ChannelServiceException {

		if(channelId == null) {
			throw new ChannelServiceException("channelId argument is mandatory");
		}

		ChannelProfileEntity channel = this.channelRepo.findOne(channelId);

		if(channel == null) {
			throw new ChannelServiceException("invalid channelId: no such channel");
		}

		this.channelRepo.delete(channel);

	}

	/**
	 * Csatornák keresése.
	 * 
	 * @param searchTerm
	 * 		keresési kifejezés
	 * @param matchType
	 * 		a mintaillesztés típusa 
	 * @return
	 * 		a találatok listáját, lexikografikusan rendezve
	 * @see MatchType
	 */
	public List<ChannelProfileDto> searchByName(String searchTerm, MatchType matchType) {

		searchTerm = searchTerm.trim();

		if(matchType == MatchType.PREFIX) {
			searchTerm = searchTerm + "%";
		} else if (matchType == MatchType.SUBSTRING) {
			searchTerm = "%" + searchTerm + "%";
		}

		List<ChannelProfileEntity> entities = this.channelRepo.searchOpenChannelsByNameOrDescripton(searchTerm);
		return this.channelMapper.apply(entities);

	}

	/**
	 * Felhasználó által nyitott csatornák lekérdezése.
	 * 
	 * @param userId
	 * 		a felhasználó azonosítója
	 * @return
	 * 		a felhasználó által nyitott csatornák listája (azaz, amelyeknél a felhasználó a <code>leader</code>), abc sorrendben 
	 * 
	 * @throws ChannelServiceException
	 * 		ha a <code>userId</code> nincs megadva, vagy érvénytelen
	 * 		
	 */
	public List<ChannelProfileDto> getChannelsOwnedByUser(Long userId) throws ChannelServiceException {

		if(userId == null) {
			throw new ChannelServiceException("userId argument is mandatory");
		}

		UserProfileData leader = this.userService.findByUserCredentialId(userId);

		if(leader == null) {
			throw new ChannelServiceException("invalid userId: no such user");
		}

		List<ChannelProfileEntity> entities = leader.getOwnedChannels();
		return this.channelMapper.apply(entities);

	}

	/**
	 * Felhasználó aktív feliratkozásainak lekérdezése
	 * 
	 * @param userId
	 * 		a felhasználó azonosítója
	 * @return
	 * 		azon csatornák listája, amikre a felhasználó feliratkozott és a feliratkozása aktív
	 * @throws ChannelServiceException
	 * 		ha a <code>userId</code> nincs megadva, vagy érvénytelen
	 */
	public List<ChannelProfileDto> getActiveSubscriptionsByUser(Long userId) throws ChannelServiceException {

		if(userId == null) {
			throw new ChannelServiceException("userId argument is mandatory");
		}

		UserProfileData user = this.userService.findByUserCredentialId(userId);

		if(user == null) {
			throw new ChannelServiceException("invalid userId: no such user");
		}

		List<ChannelProfileEntity> entities = this.channelRepo.getSubscriptionsByUserAndStatus(user, SubscriberStatus.ACTIVE);
		return this.channelMapper.apply(entities);

	}

	/**
	 * Felhasználó függőben levő feliratkozásainak lekérdezése
	 * 
	 * @param userId
	 * 		a felhasználó azonosítója
	 * @return
	 * 		azon csatornák listája, amikre a felhasználó feliratkozott, de még a feliratkozását nem fogadták el
	 * @throws ChannelServiceException
	 * 		ha a <code>userId</code> nincs megadva, vagy érvénytelen
	 */
	public List<ChannelProfileDto> getPendingSubscriptionsByUser(Long userId) throws ChannelServiceException {

		if(userId == null) {
			throw new ChannelServiceException("userId argument is mandatory");
		}

		UserProfileData user = this.userService.findByUserCredentialId(userId);

		if(user == null) {
			throw new ChannelServiceException("invalid userId: no such user");
		}

		List<ChannelProfileEntity> entities = this.channelRepo.getSubscriptionsByUserAndStatus(user, SubscriberStatus.PENDING);
		return this.channelMapper.apply(entities);

	}

	/**
	 * Lezárható/megnyitható egy csatornára való feliratkozás
	 * 
	 * @param channelId
	 * 		módosítandó csatorna azonosítója
	 * @param open
	 * 		ha <code>true</code> akkor engedélyezi a feliratkozást a csatornára, egyébként letiltja
	 * @throws ChannelServiceException
	 * 		ha <code>channelId</code> nincs megadva, vagy érvénytelen
	 */
	public void changeOpenStatus(Long channelId, boolean open) throws ChannelServiceException {

		if(channelId == null) {
			throw new ChannelServiceException("channelId argument is mandatory");
		}

		ChannelProfileEntity channel = this.channelRepo.findOne(channelId);

		if(channel == null) {
			throw new ChannelServiceException("invalid channelId: no such channel");
		}

		channel.setOpen(open);

	}

	/**
	 * Felhasználó feliratkozását végzi el egy tetszőleges csatornára.
	 * 
	 * @param userId
	 * 		felhasználó azonosítója
	 * @param channelId
	 * 		csatorna azonosítója
	 */
	public void subscribeToChannel(Long userId, Long channelId) {

		UserProfileData user = this.userService.findByUserCredentialId(userId);
		ChannelProfileEntity channel = this.channelRepo.findOne(channelId);
		ChannelSubscriberEntityId id = new ChannelSubscriberEntityId();
		id.setChannel(channel);
		id.setUser(user);
		ChannelSubscriberEntity subscriberEntity = new ChannelSubscriberEntity();
		subscriberEntity.setId(id);
		subscriberEntity.setStatus(channel.isOpen() ? SubscriberStatus.ACTIVE : SubscriberStatus.PENDING);
		subscriberEntity.setSubscriptionDate(new Date());
		this.channelSubscriberRepository.save(subscriberEntity);

	}

}
