package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.repository.UserProfileDataRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserProfileDataService {

	@Autowired
	private UserProfileDataRepository userProfileDataRepository;

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
