package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.repository.CustomUserProfileDataRepositoryImpl;
import hu.szoftverfolyamat.repository.UserProfileDataRepository;
import hu.szoftverfolyamat.service.mapper.UserProfileDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class UserProfileDataService {

    @Autowired
    private UserProfileDataMapper userProfileDataMapper;

	@Autowired
	private UserProfileDataRepository userProfileDataRepository;

	@Autowired
	private UserConnectionService userConnectionService;

	@Autowired
	private CustomUserProfileDataRepositoryImpl customUserProfileDataRepositoryImpl;
	
	public UserProfileData updateUserProfileData(final Long userCredentialId, final UserProfileDataDto userProfileDataDto) throws ParseException, UserServiceException {
		UserProfileData userProfileData;
		
		userProfileData = this.userProfileDataRepository.findOne(userCredentialId);
		if(userProfileData == null) {
			throw new UserServiceException("Can not update user with userCredentialId: '" + userCredentialId + "', because user doues not exists!");
		}
		
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
	
	public void updateAvatarId(Long userCredentialId, Long avatarId) {
		UserProfileData userProfileData;
		
		userProfileData = this.userProfileDataRepository.findOne(userCredentialId);
		if(userProfileData != null) {
			userProfileData.setAvatarId(avatarId);
			this.userProfileDataRepository.saveAndFlush(userProfileData);
		}
	}

	public List<UserProfileDataDto> getFriendsByUserId(final Long userCredentialId) {
        final List<Long> friendsId = userConnectionService.getFriendsIdByUserCredentialId(userCredentialId);
        final List<UserProfileDataDto> result = userProfileDataMapper.apply(userProfileDataRepository.findAll(friendsId));

		for (final UserProfileDataDto dataDto : result) {
			dataDto.setFriend(true);
		}

		return result;
	}

	public List<UserProfileDataDto> searchUserProfileDataDtos(final Long credentialId, final String emailAddress,
            final String fullName, final String place, final String job) {
        final List<Long> friendIds = userConnectionService.getFriendsIdByUserCredentialId(credentialId);
        final List<UserProfileDataDto> dataDtoList = userProfileDataMapper.apply(customUserProfileDataRepositoryImpl.searchUserProfileData(emailAddress, fullName, place, job));
        final List<UserProfileDataDto> result = new ArrayList<UserProfileDataDto>();

		for (final UserProfileDataDto dataDto : dataDtoList) {
			if (dataDto.getCredentialId() != credentialId) {
                dataDto.setFriend(friendIds.contains(dataDto.getCredentialId()));
				result.add(dataDto);
			}
		}

		return result;
	}
}
