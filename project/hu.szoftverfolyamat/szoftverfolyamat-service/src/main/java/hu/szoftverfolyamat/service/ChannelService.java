package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.ChannelPostDto;
import hu.szoftverfolyamat.dto.ChannelProfileDto;
import hu.szoftverfolyamat.entity.*;
import hu.szoftverfolyamat.enums.MatchType;
import hu.szoftverfolyamat.enums.SubscriberStatus;
import hu.szoftverfolyamat.exception.ChannelServiceException;
import hu.szoftverfolyamat.repository.ChannelPostCommentRepository;
import hu.szoftverfolyamat.repository.ChannelPostRepository;
import hu.szoftverfolyamat.repository.ChannelRepository;
import hu.szoftverfolyamat.repository.ChannelSubscriberRepository;
import hu.szoftverfolyamat.service.mapper.ChannelPostCommentMapper;
import hu.szoftverfolyamat.service.mapper.ChannelPostMapper;
import hu.szoftverfolyamat.service.mapper.ChannelProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChannelService {

	@Autowired
	private ChannelRepository channelRepo;

	@Autowired
	private ChannelPostRepository channelPostRepo;
	
	@Autowired
	private ChannelPostCommentRepository channelCommentRepo;

	@Autowired
	private ChannelSubscriberRepository channelSubscriberRepository;

	@Autowired
	private UserProfileDataService userService;

	@Autowired
	private ChannelProfileMapper channelMapper;
	
	@Autowired
	private ChannelPostMapper channelPostMapper;
	
	@Autowired
	private ChannelPostCommentMapper channelCommentMapper;


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
	 * Egy csatorna adatainak lekérdezése az azonosítója alapján.
	 * 
	 * @param channelId
	 * 		a csatorna azonosítója
	 * @return
	 * 		a csatorna adatlapján megjelenítendő adatok
	 * @throws ChannelServiceException
	 * 		ha a csatorna azonosítója nincs megadva, vagy érvénytelen
	 */
	public ChannelProfileDto getChannel(Long channelId) throws ChannelServiceException {
		
		if(channelId == null) {
			throw new ChannelServiceException("Invalid channelId: cannot be null.");
		}
		
		ChannelProfileEntity entity = this.channelRepo.findOne(channelId);
		
		if(entity == null) {
			throw new ChannelServiceException("Invalid channelId: no such channel.");
		}
		
		return this.channelMapper.apply(entity);
		
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
	 * @throws ChannelServiceException
	 * 		ha bármelyik azonosító nincs megadva, vagy érvénytelen
	 */
	public void subscribeToChannel(Long userId, Long channelId) throws ChannelServiceException {

		
		if(userId == null) {
			throw new ChannelServiceException("Invalid userId: cannot be null.");
		}
		
		if(channelId == null) {
			throw new ChannelServiceException("Invalid channelId: cannot be null.");
		}
		
		UserProfileData user = this.userService.findByUserCredentialId(userId);
		
		if(user == null) {
			throw new ChannelServiceException("Invalid userId: no such user.");
		}
		
		ChannelProfileEntity channel = this.channelRepo.findOne(channelId);
		
		if(channel == null) {
			throw new ChannelServiceException("Invalid channelId: no such channel.");
		}
		
		ChannelSubscriberEntityId id = new ChannelSubscriberEntityId();
		id.setChannel(channel);
		id.setUser(user);
		ChannelSubscriberEntity subscriberEntity = new ChannelSubscriberEntity();
		subscriberEntity.setId(id);
		subscriberEntity.setStatus(channel.isOpen() ? SubscriberStatus.ACTIVE : SubscriberStatus.PENDING);
		subscriberEntity.setSubscriptionDate(new Date());
		this.channelSubscriberRepository.save(subscriberEntity);

	}
	
	/**
	 * Poszt közzététele a csatornán.
	 * 
	 * @param channelId
	 * 		csatorna azonosítója
	 * @param content
	 * 		poszt tartalma
	 * @throws ChannelServiceException
	 * 		ha a channelId nincs megadva, vagy érvénytelen, vagy a poszt tartalma üres
	 */
	public void publishPost(Long channelId, String content) throws ChannelServiceException {
		
		if(channelId == null) {
			throw new ChannelServiceException("Invalid channelId: cannot be null.");
		}
		
		if(content == null || "".equals(content.trim())) {
			throw new ChannelServiceException("Invalid content: cannot be null or empty.");
		}
		
		ChannelProfileEntity channel = this.channelRepo.findOne(channelId);
		
		if(channel == null) {
			throw new ChannelServiceException("Invalid channelId: no such channel.");
		}
		
		ChannelPostEntity post = new ChannelPostEntity();
		post.setChannel(channel);
		post.setCreationDate(new Date());
		post.setText(content);
		this.channelPostRepo.save(post);
		
	}
	
	/**
	 * A csatornán közzétett posztok lekérdezése.
	 * @param channelId
	 * 		a csatornához tartozó azonosító
	 * @return
	 * 		a csatornához tartozó posztok listája
	 * @throws ChannelServiceException
	 * 		ha a channelId nincs megadva, vagy érvénytelen
	 */
	public List<ChannelPostDto> getChannelPosts(Long channelId) throws ChannelServiceException{
		
		if(channelId == null) {
			throw new ChannelServiceException("Invalid channelId: cannot be null.");
		}
		
		ChannelProfileEntity channel = this.channelRepo.findOne(channelId);
		
		if(channel == null) {
			throw new ChannelServiceException("Invalid channelId: no such channel.");
		}
		
		List<ChannelPostEntity> postEntities = channel.getPosts();
		return this.channelPostMapper.apply(postEntities);
		
	}
	
	/**
	 * Megjegyzés (komment) hozzáfűzése csatorna által kiírt bejegyzéshez (poszt). 
	 * @param userCredentialId
	 * 		a felhasználó azonosítója, aki a hozzászólást írja
	 * @param postId
	 * 		a bejegyzés azonosítója, amelyhez a hozzászólást írják
	 * @param content
	 * 		a komment tartalma
	 * @throws ChannelServiceException 
	 * 		ha bármely azonosító nincs megadva, vagy érvénytelen, vagy a komment tartalma üres
	 */
	public void addCommentToChannelPost(Long userCredentialId, Long postId, String content) throws ChannelServiceException {
		
		if(userCredentialId == null) {
			throw new ChannelServiceException("Invalid userCredentialId: cannot be null.");
		}
		
		if(postId == null) {
			throw new ChannelServiceException("Invalid postId: cannot be null.");
		}
		
		if(content == null || "".equals(content.trim())) {
			throw new ChannelServiceException("Invalid content: cannot be null or empty.");
		}
		
		UserProfileData user = this.userService.findByUserCredentialId(userCredentialId);
		
		if(user == null) {
			throw new ChannelServiceException("Invalid userCredentialId: no such user.");
		}
		
		ChannelPostEntity post = this.channelPostRepo.findOne(postId);
		
		if(post == null) {
			throw new ChannelServiceException("Invalid postId: no such post.");
		}
		
		ChannelPostCommentEntity comment = new ChannelPostCommentEntity();
		comment.setAuthor(user);
		comment.setCreationDate(new Date());
		comment.setPost(post);
		comment.setText(content);
		this.channelCommentRepo.save(comment);
		
	}

}
