package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.repository.CustomUserProfileDataRepositoryImpl;
import hu.szoftverfolyamat.repository.UserProfileDataRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserProfileDataService {

	@Autowired
	private UserProfileDataRepository userProfileDataRepository;

	@Autowired
	private UserConnectionService userConnectionService;

	@Autowired
	private CustomUserProfileDataRepositoryImpl customUserProfileDataRepositoryImpl;

	public UserProfileData createUserProfileData(Long userCredentialId,
			UserProfileDataDto userProfileDataDto) throws UserServiceException,
			ParseException {
		UserProfileData userProfileData;

		userProfileData = this.userProfileDataRepository
				.findOne(userCredentialId);
		if (userProfileData != null) {
			throw new UserServiceException(
					"Can not create user with userCredentialId: '"
							+ userCredentialId
							+ "', because user already exists!");
		}
		userProfileData = new UserProfileData();
		userProfileData.setCredentialId(userCredentialId);
		userProfileData.setBirthday(new SimpleDateFormat("YYYY.MM.dd",
				Locale.ENGLISH).parse(userProfileDataDto.getBirthday()));
		userProfileData.setEmail(userProfileDataDto.getEmail());
		userProfileData.setFullName(userProfileDataDto.getFullName());
		userProfileData.setHabitat(userProfileDataDto.getHabitat());
		userProfileData.setJob(userProfileDataDto.getJob());
		userProfileData.setPublicBirthday(userProfileDataDto
				.getPublicBirthday());
		userProfileData.setPublicHabitat(userProfileDataDto.getPublicHabitat());
		userProfileData.setPublicJobAndWorkplace(userProfileDataDto
				.getPublicJobAndWorkplace());
		userProfileData.setShortName(userProfileDataDto.getShortName());
		userProfileData.setWorkplace(userProfileDataDto.getWorkplace());

		return this.userProfileDataRepository.saveAndFlush(userProfileData);
	}

	public UserProfileData findByUserCredentialId(long id) {
		return this.userProfileDataRepository.findOne(id);
	}

	public List<UserProfileDataDto> getFriendsByUserId(Long userCredentialId) {
		List<Long> friendsId;
		List<UserProfileDataDto> result;

		friendsId = this.userConnectionService
				.getFriendsIdByUserCredentialId(userCredentialId);
		result = this.parseEntitiesToDtos(this.userProfileDataRepository
				.findAll(friendsId));
		for (UserProfileDataDto dataDto : result) {
			dataDto.setFriend(true);
		}
		return result;
	}

	public List<UserProfileDataDto> parseEntitiesToDtos(
			List<UserProfileData> entities) {
		List<UserProfileDataDto> dtos;

		dtos = new ArrayList<UserProfileDataDto>();
		for (UserProfileData entity : entities) {
			dtos.add(this.parseEntityToDto(entity));
		}
		return dtos;
	}

	public UserProfileDataDto parseEntityToDto(UserProfileData entity) {
		UserProfileDataDto dto;

		dto = new UserProfileDataDto();
		dto.setBirthday(new SimpleDateFormat("YYYY.MM.dd").format(entity
				.getBirthday()));
		dto.setCredentialId(entity.getCredentialId());
		dto.setEmail(entity.getEmail());
		dto.setFullName(entity.getFullName());
		dto.setHabitat(entity.getHabitat());
		dto.setJob(entity.getJob());
		dto.setPublicBirthday(entity.getPublicBirthday());
		dto.setPublicHabitat(entity.getPublicHabitat());
		dto.setPublicJobAndWorkplace(entity.getPublicJobAndWorkplace());
		dto.setShortName(entity.getShortName());
		dto.setWorkplace(entity.getWorkplace());
		dto.setFriendNumber((long) this.userConnectionService
				.getFriendsIdByUserCredentialId(entity.getCredentialId())
				.size());
		return dto;
	}

	public List<UserProfileDataDto> searchUserProfileDataDtos(
			Long credentialId, String emailAddress, String fullName,
			String place, String job) {
		List<UserProfileDataDto> dataDtos;
		List<Long> friendIds;
		List<UserProfileDataDto> result;

		friendIds = this.userConnectionService
				.getFriendsIdByUserCredentialId(credentialId);
		dataDtos = this
				.parseEntitiesToDtos(this.customUserProfileDataRepositoryImpl
						.searchUserProfileData(emailAddress, fullName, place,
								job));

		result = new ArrayList<UserProfileDataDto>();
		for (UserProfileDataDto dataDto : dataDtos) {
			if (dataDto.getCredentialId() != credentialId) {
				dataDtos.remove(dataDto);
				if (friendIds.contains(dataDto.getCredentialId())) {
					dataDto.setFriend(true);
				} else {
					dataDto.setFriend(false);
				}
				result.add(dataDto);
			}
		}
		return result;
	}

	public UserProfileData updateUserProfileData(Long userCredentialId,
			UserProfileDataDto userProfileDataDto) throws UserServiceException,
			ParseException {
		UserProfileData userProfileData;

		userProfileData = this.userProfileDataRepository
				.findOne(userCredentialId);
		if (userProfileData == null) {
			throw new UserServiceException(
					"Can not update user with userCredentialId: '"
							+ userCredentialId
							+ "', because user is not exist!");
		}
		userProfileData = new UserProfileData();
		userProfileData.setBirthday(new SimpleDateFormat("YYYY.MM.dd",
				Locale.ENGLISH).parse(userProfileDataDto.getBirthday()));
		userProfileData.setEmail(userProfileDataDto.getEmail());
		userProfileData.setFullName(userProfileDataDto.getFullName());
		userProfileData.setHabitat(userProfileDataDto.getHabitat());
		userProfileData.setJob(userProfileDataDto.getJob());
		userProfileData.setPublicBirthday(userProfileDataDto
				.getPublicBirthday());
		userProfileData.setPublicHabitat(userProfileDataDto.getPublicHabitat());
		userProfileData.setPublicJobAndWorkplace(userProfileDataDto
				.getPublicJobAndWorkplace());
		userProfileData.setShortName(userProfileDataDto.getShortName());
		userProfileData.setWorkplace(userProfileDataDto.getWorkplace());

		return this.userProfileDataRepository.saveAndFlush(userProfileData);
	}
}
