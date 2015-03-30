package hu.szoftverfolyamat.web.parser;

import hu.szoftverfolyamat.dto.UserCredentialDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.web.requestobject.ProfileFormRequest;

public class ProfileRequestMapper {

	public UserCredentialDto decode(ProfileFormRequest profileFormRequest) {
		UserCredentialDto userCredentialDto;
		UserProfileDataDto userProfileData;
		
		userProfileData = new UserProfileDataDto();
		userProfileData.setBirthday(profileFormRequest.getBirthday());
		userProfileData.setCredentialId(profileFormRequest.getCredentialId());
		userProfileData.setEmail(profileFormRequest.getEmail());
		userProfileData.setFullName(profileFormRequest.getFullName());
		userProfileData.setHabitat(profileFormRequest.getHabitat());
		userProfileData.setJob(profileFormRequest.getJob());
		userProfileData.setPublicBirthday(profileFormRequest.getPublicBirthday() != null && !profileFormRequest.getPublicBirthday().equals("off"));
		userProfileData.setPublicHabitat(profileFormRequest.getPublicHabitat() != null && !profileFormRequest.getPublicHabitat().equals("off"));
		userProfileData.setPublicJobAndWorkplace(profileFormRequest.getPublicJobAndWorkplace() != null && !profileFormRequest.getPublicJobAndWorkplace().equals("off"));
		userProfileData.setShortName(profileFormRequest.getShortName());
		userProfileData.setWorkplace(profileFormRequest.getWorkplace());
		
		userCredentialDto = new UserCredentialDto();
		userCredentialDto.setCredentialId(profileFormRequest.getCredentialId());
		userCredentialDto.setPassword(profileFormRequest.getPassword());
		userCredentialDto.setUsername(profileFormRequest.getUsername());
		userCredentialDto.setUserProfileDataDto(userProfileData);
		
		return userCredentialDto;
	}
	
	public ProfileFormRequest encode(UserCredentialDto userCredentialDto) {
		ProfileFormRequest profileFormRequest;
		
		profileFormRequest = new ProfileFormRequest();
		profileFormRequest.setBirthday(userCredentialDto.getUserProfileDataDto().getBirthday());
		profileFormRequest.setCredentialId(userCredentialDto.getCredentialId());
		profileFormRequest.setEmail(userCredentialDto.getUserProfileDataDto().getEmail());
		profileFormRequest.setFullName(userCredentialDto.getUserProfileDataDto().getFullName());
		profileFormRequest.setHabitat(userCredentialDto.getUserProfileDataDto().getHabitat());
		profileFormRequest.setJob(userCredentialDto.getUserProfileDataDto().getJob());
		profileFormRequest.setPassword(userCredentialDto.getPassword());
		profileFormRequest.setPublicBirthday(userCredentialDto.getUserProfileDataDto().isPublicBirthday() ? "on" : "off");
		profileFormRequest.setPublicHabitat(userCredentialDto.getUserProfileDataDto().isPublicHabitat() ? "on" : "off");
		profileFormRequest.setPublicJobAndWorkplace(userCredentialDto.getUserProfileDataDto().isPublicJobAndWorkplace() ? "on" : "off");
		profileFormRequest.setShortName(userCredentialDto.getUserProfileDataDto().getShortName());
		profileFormRequest.setUsername(userCredentialDto.getUsername());
		profileFormRequest.setWorkplace(userCredentialDto.getUserProfileDataDto().getWorkplace());
		
		return profileFormRequest;
	}
}
