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

	public UserProfileData createUserProfileData(final Long userCredentialId, final UserProfileDataDto userProfileDataDto)
            throws UserServiceException, ParseException {

		if (userProfileDataRepository.findOne(userCredentialId) != null) {
			throw new UserServiceException("Can not create user with userCredentialId: '" + userCredentialId + "', because user already exists!");
		}

		final UserProfileData userProfileData = new UserProfileData();
		userProfileData.setCredentialId(userCredentialId);
		userProfileData.setBirthday(new SimpleDateFormat("YYYY.MM.dd", Locale.ENGLISH).parse(userProfileDataDto.getBirthday()));
		userProfileData.setEmail(userProfileDataDto.getEmail());
		userProfileData.setFullName(userProfileDataDto.getFullName());
		userProfileData.setHabitat(userProfileDataDto.getHabitat());
		userProfileData.setJob(userProfileDataDto.getJob());
		userProfileData.setPublicBirthday(userProfileDataDto.isPublicBirthday());
		userProfileData.setPublicHabitat(userProfileDataDto.isPublicHabitat());
		userProfileData.setPublicJobAndWorkplace(userProfileDataDto.isPublicJobAndWorkplace());
		userProfileData.setShortName(userProfileDataDto.getShortName());
		userProfileData.setWorkplace(userProfileDataDto.getWorkplace());

		return userProfileDataRepository.saveAndFlush(userProfileData);
	}

	public UserProfileData findByUserCredentialId(final long id) {
		return userProfileDataRepository.findOne(id);
	}

	public List<UserProfileDataDto> getFriendsByUserId(final Long userCredentialId) {
        final List<Long> friendsId = userConnectionService.getFriendsIdByUserCredentialId(userCredentialId);
        final List<UserProfileDataDto> result = parseEntitiesToDtos(userProfileDataRepository.findAll(friendsId));

		for (final UserProfileDataDto dataDto : result) {
			dataDto.setFriend(true);
		}

		return result;
	}

	private List<UserProfileDataDto> parseEntitiesToDtos(final List<UserProfileData> entities) {
		final List<UserProfileDataDto> dtos = new ArrayList<UserProfileDataDto>();

		for (final UserProfileData entity : entities) {
			dtos.add(this.parseEntityToDto(entity));
		}

		return dtos;
	}

	public UserProfileDataDto parseEntityToDto(final UserProfileData entity) {
		final UserProfileDataDto dto  = new UserProfileDataDto();
		dto.setBirthday(new SimpleDateFormat("YYYY.MM.dd").format(entity.getBirthday()));
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
		dto.setFriendNumber((long) userConnectionService.getFriendsIdByUserCredentialId(entity.getCredentialId()).size());
		return dto;
	}

	public List<UserProfileDataDto> searchUserProfileDataDtos(final Long credentialId, final String emailAddress,
            final String fullName, final String place, final String job) {
        final List<Long> friendIds = userConnectionService.getFriendsIdByUserCredentialId(credentialId);
        final List<UserProfileDataDto> dataDtos = parseEntitiesToDtos(
                customUserProfileDataRepositoryImpl.searchUserProfileData(emailAddress, fullName, place, job));
        final List<UserProfileDataDto> result = new ArrayList<UserProfileDataDto>();

		for (final UserProfileDataDto dataDto : dataDtos) {
			if (dataDto.getCredentialId() != credentialId) {
				dataDtos.remove(dataDto);
                dataDto.setFriend(friendIds.contains(dataDto.getCredentialId()));
				result.add(dataDto);
			}
		}

		return result;
	}
}
